package com.boxtrotstudio.aws;

import com.boxtrotstudio.aws.common.AuxProxyMessageSender;
import com.boxtrotstudio.aws.common.GameLiftError;
import com.boxtrotstudio.aws.common.GameLiftErrorType;
import com.boxtrotstudio.aws.common.GenericOutcome;
import com.boxtrotstudio.aws.utils.Async;
import com.google.common.graph.Network;
import org.apache.logging.log4j.Logger;

public class ServerState extends Async {
    static final String HOSTNAME = "127.0.0.1";
    static final String PORT = "5757";
    static final String PID_KEY = "pID";
    static final String SDK_VERSION_KEY = "sdkVersion";
    static final String FLAVOR_KEY = "sdkLanguage";
    static final String FLAVOR = "CSharp";
    static final long HEALTHCHECK_TIMEOUT_SECONDS = 60 * 1000;

    AuxProxyMessageSender sender;
    Network network;

    ProcessParameters processParameters;
    boolean processReady = false;
    String gameSessionId;
    Logger log;

    static volatile boolean networkInitialized = false;
    public static final ServerState INSTANCE = new ServerState();

    ServerState() { }

    private void debug(String message) {
        if (log != null) {
            log.debug(message);
        }
        System.err.println(message);
    }

    public GenericOutcome processReady(ProcessParameters parameters) {
        processReady = true;
        processParameters = parameters;

        if (!networkInitialized) {
            return new GenericOutcome(new GameLiftError(GameLiftErrorType.NETWORK_NOT_INITIALIZED));
        }

        GenericOutcome result = sender.processReady(parameters.getPort(), parameters.getLogParameters().getPaths());

        runAsync(() -> {
            while (processReady) {
                runAsync(this::reportHealth);
                try {
                    Thread.sleep(HEALTHCHECK_TIMEOUT_SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
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

    public void reportHealth() {

    }
}
