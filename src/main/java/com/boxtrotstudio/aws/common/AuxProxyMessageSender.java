package com.boxtrotstudio.aws.common;

import com.amazon.whitewater.auxproxy.pbuffer.Sdk;
import com.boxtrotstudio.aws.model.*;
import com.google.common.util.concurrent.SettableFuture;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.util.JsonFormat;
import io.socket.client.Ack;
import io.socket.client.Socket;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AuxProxyMessageSender {
    private static final GenericOutcome GENERIC_ERROR = new GenericOutcome(new GameLiftError(GameLiftErrorType.LOCAL_CONNECTION_FAILED));
    private static final DescribePlayerSessionsOutcome DESCRIBE_PLAYER_SESSION_ERROR = new DescribePlayerSessionsOutcome(new GameLiftError(GameLiftErrorType.SERVICE_CALL_FAILED));
    private static final StartMatchBackfillOutcome START_MATCH_BACKFILL_ERROR = new StartMatchBackfillOutcome(new GameLiftError(GameLiftErrorType.SERVICE_CALL_FAILED));
    private static final GenericOutcome STOP_MATCH_BACKFILL_ERROR = new GenericOutcome(new GameLiftError(GameLiftErrorType.SERVICE_CALL_FAILED));

    private Socket socket;

    public AuxProxyMessageSender(Socket socket) {
        this.socket = socket;
    }

    private Ack _createAckFunction(final SettableFuture<DescribePlayerSessionsOutcome> future) {
        return new Ack() {
            @Override
            public void call(Object... objects) {
                if (objects.length == 0 || objects[0] == null) {
                    future.set(new DescribePlayerSessionsOutcome(new GameLiftError(GameLiftErrorType.SERVICE_CALL_FAILED)));
                    return;
                }

                boolean value = (boolean) objects[0];

                if (value) {
                    try {
                        Sdk.DescribePlayerSessionsResponse.Builder builder = Sdk.DescribePlayerSessionsResponse.newBuilder();
                        JsonFormat.parser().merge((String)objects[1], builder);

                        Sdk.DescribePlayerSessionsResponse response = builder.build();

                        DescribePlayerSessionsResult result = new DescribePlayerSessionsResult(response);

                        future.set(new DescribePlayerSessionsOutcome(result));

                    } catch (InvalidProtocolBufferException e) {
                        future.set(new DescribePlayerSessionsOutcome(new GameLiftError(GameLiftErrorType.SERVICE_CALL_FAILED, e)));
                    }
                } else {
                    future.set(new DescribePlayerSessionsOutcome(new GameLiftError(GameLiftErrorType.SERVICE_CALL_FAILED)));
                }
            }
        };
    }

    private Ack __createAckFunction(final SettableFuture<StartMatchBackfillOutcome> future) {
        return new Ack() {
            @Override
            public void call(Object... objects) {
                if (objects.length == 0 || objects[0] == null) {
                    future.set(new StartMatchBackfillOutcome(new GameLiftError(GameLiftErrorType.SERVICE_CALL_FAILED)));
                    return;
                }

                boolean value = (boolean) objects[0];

                if (value) {
                    try {
                        Sdk.BackfillMatchmakingResponse.Builder builder = Sdk.BackfillMatchmakingResponse.newBuilder();
                        JsonFormat.parser().merge((String) objects[1], builder);

                        Sdk.BackfillMatchmakingResponse response = builder.build();

                        StartMatchBackfillResult result = new StartMatchBackfillResult(response);

                        future.set(new StartMatchBackfillOutcome(result));
                    } catch (InvalidProtocolBufferException e) {
                        future.set(new StartMatchBackfillOutcome(new GameLiftError(GameLiftErrorType.SERVICE_CALL_FAILED, e)));
                    }
                } else {
                    future.set(new StartMatchBackfillOutcome(new GameLiftError(GameLiftErrorType.SERVICE_CALL_FAILED)));
                }
            }
        };
    }

    private Ack createAckFunction(final SettableFuture<GenericOutcome> future) {
        return new Ack() {
            @Override
            public void call(Object... objects) {
                if (objects.length == 0 || objects[0] == null) {
                    future.set(new GenericOutcome(new GameLiftError(GameLiftErrorType.SERVICE_CALL_FAILED)));
                }

                boolean value = (boolean) objects[0];

                if (value) {
                    future.set(new GenericOutcome());
                } else {
                    future.set(new GenericOutcome(new GameLiftError(GameLiftErrorType.SERVICE_CALL_FAILED)));
                }
            }
        };
    }

    public GenericOutcome processReady(int port, List<String> filesToUpload) {
        Sdk.ProcessReady.Builder builder = Sdk.ProcessReady.newBuilder();
        builder.setPort(port);

        for (int i = 0; i < filesToUpload.size(); i++) {
            builder.addLogPathsToUpload(filesToUpload.get(i));
        }

        return emitEvent(builder.build());
    }

    public GenericOutcome processEnding() {
        return emitEvent(Sdk.ProcessEnding.newBuilder().build());
    }

    public GenericOutcome activateGameSession(String gameSessionId) {
        Sdk.GameSessionActivate.Builder builder = Sdk.GameSessionActivate.newBuilder();
        builder.setGameSessionId(gameSessionId);

        return emitEvent(builder.build());
    }

    public GenericOutcome terminateGameSession(String gameSessionId) {
        Sdk.GameSessionTerminate.Builder builder = Sdk.GameSessionTerminate.newBuilder();
        builder.setGameSessionId(gameSessionId);

        return emitEvent(builder.build());
    }

    public GenericOutcome updatePlayerSessionCreationPolicy(String gameSessionId, PlayerSessionCreationPolicy playerSessionPolicy) {
        Sdk.UpdatePlayerSessionCreationPolicy.Builder builder = Sdk.UpdatePlayerSessionCreationPolicy.newBuilder();
        builder.setGameSessionId(gameSessionId)
                .setNewPlayerSessionCreationPolicy(playerSessionPolicy.name());

        return emitEvent(builder.build());
    }

    public GenericOutcome acceptPlayerSession(String playerSessionID, String gameSessionID) {
        Sdk.AcceptPlayerSession.Builder builder = Sdk.AcceptPlayerSession.newBuilder();
        builder.setGameSessionId(gameSessionID)
                .setPlayerSessionId(playerSessionID);

        return emitEvent(builder.build());
    }

    public GenericOutcome removePlayerSession(String playerSessionID, String gameSessionID) {
        Sdk.RemovePlayerSession.Builder builder = Sdk.RemovePlayerSession.newBuilder();
        builder.setPlayerSessionId(playerSessionID)
                .setGameSessionId(gameSessionID);

        return emitEvent(builder.build());
    }

    public DescribePlayerSessionsOutcome describePlayerSessions(DescribePlayerSessionsRequest request) {

        Sdk.DescribePlayerSessionsRequest message = request.createRequest();

        SettableFuture<DescribePlayerSessionsOutcome> future = SettableFuture.create();
        Ack ack = _createAckFunction(future);
        return emitEvent(message, ack, future, DESCRIBE_PLAYER_SESSION_ERROR);
    }

    public StartMatchBackfillOutcome backfillMatchmaking(StartMatchBackfillRequest request) {
        Sdk.BackfillMatchmakingRequest message = request.createRequest();

        SettableFuture<StartMatchBackfillOutcome> future = SettableFuture.create();
        Ack ack = __createAckFunction(future);

        return emitEvent(message, ack, future, START_MATCH_BACKFILL_ERROR);
    }

    public GenericOutcome stopMatchmaking(StopMatchBackfillRequest request) {
        Sdk.StopMatchmakingRequest message = request.createRequest();

        SettableFuture<GenericOutcome> future = SettableFuture.create();

        Ack ack = createAckFunction(future);

        return emitEvent(message, ack, future, STOP_MATCH_BACKFILL_ERROR);
    }

    public GenericOutcome reportHealth(boolean health) {
        Sdk.ReportHealth health1 = Sdk.ReportHealth.newBuilder().setHealthStatus(health).build();
        return emitEvent(health1);
    }

    public GenericOutcome emitEvent(Message message) {
        SettableFuture<GenericOutcome> future = SettableFuture.create();
        Ack ack = createAckFunction(future);
        return emitEvent(message, ack, future, GENERIC_ERROR);
    }

    public synchronized <T extends GenericOutcome> T emitEvent(Message message,
                                                  Ack ack,
                                                  SettableFuture<T> future,
                                                  T genericError) {

        socket.emit(message.getDescriptorForType().getFullName(), message.toByteArray(), ack);
        try {
            return future.get(30, TimeUnit.SECONDS);
        } catch (Throwable e) {
            genericError.setError(new GameLiftError(genericError.getError().getErrorType(), e));
            return genericError;
        }
    }
}
