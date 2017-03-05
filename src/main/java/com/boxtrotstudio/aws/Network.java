package com.boxtrotstudio.aws;

import com.boxtrotstudio.aws.common.GameLiftError;
import com.boxtrotstudio.aws.common.GameLiftErrorType;
import com.boxtrotstudio.aws.common.GenericOutcome;
import com.boxtrotstudio.aws.utils.P2Runnable;
import io.socket.client.Ack;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Network {
    private Socket socket;
    private ServerState handler;
    private boolean connected = false;
    private final Logger logger = LogManager.getLogger(Network.class);

    public Network(Socket socket, ServerState handler) {
        this.socket = socket;
        this.handler = handler;

        setupCallbacks();
    }

    public synchronized GenericOutcome connect() {
        socket.connect();

        try {
            super.wait(5000);
        } catch (InterruptedException ignored) { }

        if (!connected)
            return new GenericOutcome(new GameLiftError(GameLiftErrorType.LOCAL_CONNECTION_FAILED));

        return new GenericOutcome();
    }

    public GenericOutcome disconnect() {
        socket.disconnect();
        return new GenericOutcome();
    }

    private void setupCallbacks() {
        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... objects) {
                logger.debug("Socket.io event triggered: EVENT_CONNECT");

                socket.on("StartGameSession", new ServerStateListener(new P2Runnable() {
                    @Override
                    public void run(Object arg1, Object arg2) {
                        handler.onStartGameSession((String)arg1, (Ack)arg2);
                    }
                }));

                socket.on("TerminateProcess", new Emitter.Listener() {
                    @Override
                    public void call(Object... objects) {
                        handler.onTerminateProcess();
                    }
                });

                _notifyConnected();
            }
        });

        socket.on(Socket.EVENT_CONNECT_ERROR, new Emitter.Listener() {
            @Override
            public void call(Object... objects) {
                logger.debug("Socket.io event triggered: EVENT_CONNECT_ERROR, with error: " + objects[0]);
            }
        });

        socket.on(Socket.EVENT_ERROR, new Emitter.Listener() {
            @Override
            public void call(Object... objects) {
                logger.debug("Socket.io event triggered: EVENT_ERROR, with error: " + objects[0]);
            }
        });

        socket.on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... objects) {
                logger.debug("Socket.io event triggered: EVENT_DISCONNECT");
            }
        });

        socket.on(Socket.EVENT_CONNECT_TIMEOUT, new Emitter.Listener() {
            @Override
            public void call(Object... objects) {
                logger.debug("Socket.io event triggered: EVENT_CONNECT_TIMEOUT");
            }
        });

        socket.on(Socket.EVENT_MESSAGE, new Emitter.Listener() {
            @Override
            public void call(Object... objects) {
                logger.debug("Socket.io event triggered: EVENT_MESSAGE, with error: " + objects[0]);
            }
        });
    }

    private synchronized void _notifyConnected() {
        connected = true;
        super.notifyAll();
    }



    private static class ServerStateListener implements Emitter.Listener {
        private static int nextID = 0;
        private int Id;
        private final P2Runnable callback;

        ServerStateListener(P2Runnable callback) {
            this.callback = callback;
            this.Id = nextID++;
        }

        public int getId() {
            return Id;
        }

        @Override
        public void call(Object... objects) {
            Object arg1 = objects.length > 0 ? objects[0] : null;
            Object arg2 = objects.length > 1 ? objects[1] : null;

            callback.run(arg1, arg2);
        }
    }
}
