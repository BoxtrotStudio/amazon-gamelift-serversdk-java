package com.boxtrotstudio.aws;

import com.amazon.whitewater.auxproxy.pbuffer.Sdk;
import com.boxtrotstudio.aws.common.*;
import com.boxtrotstudio.aws.model.*;
import com.boxtrotstudio.aws.utils.Async;
import com.google.gson.Gson;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import io.socket.client.Ack;
import io.socket.client.IO;
import io.socket.client.Socket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.management.ManagementFactory;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.concurrent.*;

public class ServerState extends Async {
    private static final String HOSTNAME = "127.0.0.1";
    private static final String PORT = "5757";
    private static final String PID_KEY = "pID";
    private static final String SDK_VERSION_KEY = "sdkVersion";
    private static final String FLAVOR_KEY = "sdkLanguage";
    private static final String FLAVOR = "Java";
    private static final long HEALTHCHECK_TIMEOUT_SECONDS = 60 * 1000;
    private static final Gson JSON = new Gson();

    private AuxProxyMessageSender sender;
    private Network network;

    private ProcessParameters processParameters;
    private boolean processReady = false;
    private String gameSessionId;
    private Logger logger = LogManager.getLogger(ServerState.class);
    private long terminationTime;

    private volatile boolean networkInitialized = false;


    public static final ServerState DEFULT_INSTANCE = new ServerState();

    public ServerState() { }

    private void debug(String message) {
        if (logger != null) {
            logger.debug(message);
        }
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public GenericOutcome processReady(ProcessParameters parameters) {
        processReady = true;
        processParameters = parameters;

        if (!networkInitialized) {
            return new GenericOutcome(new GameLiftError(GameLiftErrorType.NETWORK_NOT_INITIALIZED));
        }

        GenericOutcome result = sender.processReady(parameters.getPort(), parameters.getLogParameters().getPaths());

        runAsync(new Runnable() {
            @Override
            public void run() {
                while (processReady) {
                    runAsync(new Runnable() {
                        @Override
                        public void run() {
                            reportHealth();
                        }
                    });
                    try {
                        Thread.sleep(HEALTHCHECK_TIMEOUT_SECONDS);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        });

        return result;
    }

    public GenericOutcome processEnding() {
        if (!networkInitialized) {
            return new GenericOutcome(new GameLiftError(GameLiftErrorType.NETWORK_NOT_INITIALIZED));
        }

        return sender.processEnding();
    }

    public GenericOutcome activateGameSession() {
        if (!networkInitialized) {
            return new GenericOutcome(new GameLiftError(GameLiftErrorType.NETWORK_NOT_INITIALIZED));
        }
        if (gameSessionId == null || gameSessionId.equals("")) {
            return new GenericOutcome(new GameLiftError(GameLiftErrorType.GAMESESSION_ID_NOT_SET));
        }
        return sender.activateGameSession(gameSessionId);
    }

    public GenericOutcome terminateGameSession() {
        if (!networkInitialized) {
            return new GenericOutcome(new GameLiftError(GameLiftErrorType.NETWORK_NOT_INITIALIZED));
        }
        if (gameSessionId == null || gameSessionId.equals("")) {
            return new GenericOutcome(new GameLiftError(GameLiftErrorType.GAMESESSION_ID_NOT_SET));
        }

        return sender.terminateGameSession(gameSessionId);
    }

    public StringOutcome getGameSessionId() {
        if (gameSessionId == null || gameSessionId.equals("")) {
            return new StringOutcome(new GameLiftError(GameLiftErrorType.GAMESESSION_ID_NOT_SET));
        }

        return new StringOutcome(gameSessionId);
    }

    public GenericOutcome updatePlayerSessionCreationPolicy(PlayerSessionCreationPolicy policy) {
        if (!networkInitialized) {
            return new GenericOutcome(new GameLiftError(GameLiftErrorType.NETWORK_NOT_INITIALIZED));
        }
        if (gameSessionId == null || gameSessionId.equals("")) {
            return new GenericOutcome(new GameLiftError(GameLiftErrorType.GAMESESSION_ID_NOT_SET));
        }

        return sender.updatePlayerSessionCreationPolicy(gameSessionId, policy);
    }

    public GenericOutcome acceptPlayerSession(String playerSessionId) {
        if (!networkInitialized) {
            return new GenericOutcome(new GameLiftError(GameLiftErrorType.NETWORK_NOT_INITIALIZED));
        }
        if (gameSessionId == null || gameSessionId.equals("")) {
            return new GenericOutcome(new GameLiftError(GameLiftErrorType.GAMESESSION_ID_NOT_SET));
        }

        return sender.acceptPlayerSession(playerSessionId, gameSessionId);
    }

    public GenericOutcome removePlayerSeession(String playerSessionId) {
        if (!networkInitialized) {
            return new GenericOutcome(new GameLiftError(GameLiftErrorType.NETWORK_NOT_INITIALIZED));
        }
        if (gameSessionId == null || gameSessionId.equals("")) {
            return new GenericOutcome(new GameLiftError(GameLiftErrorType.GAMESESSION_ID_NOT_SET));
        }

        return sender.removePlayerSession(playerSessionId, gameSessionId);
    }

    public DescribePlayerSessionsOutcome describePlayerSessions(DescribePlayerSessionsRequest request) {
        debug("Describing player sessions for playerSessionId " + request.getPlayerSessionId());

        if (!networkInitialized) {
            return new DescribePlayerSessionsOutcome(new GameLiftError(GameLiftErrorType.NETWORK_NOT_INITIALIZED));
        }

        return sender.describePlayerSessions(request);
    }

    public StartMatchBackfillOutcome BackfillMatchmaking(StartMatchBackfillRequest request)
    {
        if (!networkInitialized)
        {
            return new StartMatchBackfillOutcome(new GameLiftError(GameLiftErrorType.NETWORK_NOT_INITIALIZED));
        }
        else
        {
            return sender.BackfillMatchmaking(request);
        }
    }

    public GenericOutcome StopMatchmaking(StopMatchBackfillRequest request)
    {
        if (!networkInitialized)
        {
            return new GenericOutcome(new GameLiftError(GameLiftErrorType.NETWORK_NOT_INITIALIZED));
        }
        else
        {
            return sender.StopMatchmaking(request);
        }
    }

    private void reportHealth() {
        debug("Reporting health using the OnHealthCheck callback.");
        Future<Boolean> future = runAsync(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return processParameters.healthCheck();
            }
        });

        try {
            boolean result = future.get(HEALTHCHECK_TIMEOUT_SECONDS, TimeUnit.SECONDS);
            debug("Received health response from the server process: " + result);

            sender.reportHealth(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
            debug("Interrupted waiting for health response from the server process. Reporting as unhealthy.");
            sender.reportHealth(false);
        } catch (ExecutionException e) {
            e.printStackTrace();
            debug("Errored waiting for health response from the server process. Reporting as unhealthy.");
            sender.reportHealth(false);
        } catch (TimeoutException e) {
            debug("Timed out waiting for health response from the server process. Reporting as unhealthy.");
            sender.reportHealth(false);
        }
    }

    GenericOutcome initNetworking() {
        if (!networkInitialized) {
            String url = "http://" + HOSTNAME + ":" + PORT;
            String queryString = PID_KEY + "=" + getProcessID() + "&" +
                                SDK_VERSION_KEY + "=" + GameLiftServerAPI.SDK_VERSION + "&" +
                                FLAVOR_KEY + "=" + FLAVOR;

            try {
                Socket socketToAuxProxy = IO.socket(url, createDefaults(queryString));
                Socket socketFromAuxProxy = IO.socket(url, createDefaults(queryString));
                sender = new AuxProxyMessageSender(socketToAuxProxy);
                network = new Network(socketFromAuxProxy, socketToAuxProxy, this);
                GenericOutcome result = network.connect();
                networkInitialized = result.isSuccessful();
                return result;
            } catch (URISyntaxException e) {
                return new GenericOutcome(new GameLiftError(GameLiftErrorType.LOCAL_CONNECTION_FAILED, e));
            }
        }

        return new GenericOutcome();
    }

    private IO.Options createDefaults(String queryString) {
        IO.Options options = new IO.Options();
        options.query = queryString;
        options.reconnection = false;
        options.forceNew = true;
        options.transports = new String[] { "websocket" };

        return options;
    }

    public String getProcessID() {
        return ManagementFactory.getRuntimeMXBean().getName().split("@")[0]; // --> 742912@localhost
    }

    void onStartGameSession(final String rawGameSession, Ack ack) {
        debug("ServerState got the startGameSession signal. rawGameSession : " + rawGameSession);

        if (!processReady) {
            debug("Got a game session on inactive process. Sending false ack.");
            ack.call(false);
            return;
        }

        debug("Sending true ack.");
        ack.call(true);

        runAsync(new Runnable() {
            @Override
            public void run() {
                try {
                    //Parse message
                    Sdk.ActivateGameSession.Builder builder = Sdk.ActivateGameSession.newBuilder();
                    JsonFormat.parser().merge(rawGameSession, builder);
                    Sdk.GameSession parsed = builder.build().getGameSession();

                    //Convert to model
                    GameSession session = new GameSession(parsed);

                    session.setGameProperties(new HashMap<String, String>());
                    for (int i = 0; i < parsed.getGamePropertiesCount(); i++) {
                        Sdk.GameProperty property = parsed.getGameProperties(i);

                        session.addGameProperty(property.getKey(), property.getValue());
                    }

                    //Save and invoke onGameSessionStarted
                    gameSessionId = session.getGameSessionId();
                    processParameters.gameSessionStarted(session);

                } catch (InvalidProtocolBufferException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void onUpdateGameSession(String rawUpdateGameSession, Ack ack)
    {
        debug("ServerState got the updateGameSession signal. rawGameSession : " + rawUpdateGameSession);

        if (!processReady)
        {
            debug("Got an updated game session on inactive process. Sending false ack.");
            ack.call(false);
            return;
        }
        debug("Sending true ack.");
        ack.call(true);

        runAsync(new Runnable() {
            @Override
            public void run() {
                Sdk.UpdateGameSession updateGameSession = new Gson().fromJson(rawUpdateGameSession, Sdk.UpdateGameSession.class);
                GameSession session = new GameSession(updateGameSession.getGameSession());
                gameSessionId = session.getGameSessionId();

                UpdateReason updateReason = UpdateReason.getUpdateReasonForName(updateGameSession.getUpdateReason());

                processParameters.gameSessionUpdated(
                        new UpdateGameSession(session, updateReason, updateGameSession.getBackfillTicketId()));

            }
        });
    }

    void onTerminateProcess(String rawTerminationTime) {
        debug("Handler got the terminateProcess signal. rawTerminationTime time " + rawTerminationTime);
        runAsync(new Runnable() {
            @Override
            public void run() {
                Sdk.TerminateProcess process = null;
                try {
                    process = JSON.fromJson(rawTerminationTime, Sdk.TerminateProcess.class);
                } catch (Throwable ignored) { }

                if (process == null) {
                    //If termination time isn't sent from AuxProxy use now plus 5 minutes.
                    terminationTime = System.currentTimeMillis() + (60000 * 5);
                } else {
                    /* TerminationTime coming from AuxProxy is seconds that have elapsed since Unix epoch time begins (00:00:00 UTC Jan 1 1970).
                     */
                    terminationTime = process.getTerminationTime();
                }

                processParameters.processTerminated();
            }
        });
    }

    public void shutdown() {
        networkInitialized = false;
        network.disconnect();
        processReady = false;
    }
}
