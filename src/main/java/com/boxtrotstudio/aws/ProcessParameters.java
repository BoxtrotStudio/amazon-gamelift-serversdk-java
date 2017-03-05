package com.boxtrotstudio.aws;

import com.boxtrotstudio.aws.model.GameSession;
import com.boxtrotstudio.aws.utils.PRunnable;

import java.io.File;
import java.util.List;
import java.util.concurrent.Callable;

public class ProcessParameters {
    private LogParameters logParameters;
    private int port;

    private Callable<Boolean> healthCheck;
    private Runnable processTerminate;
    private PRunnable<GameSession> gameSessionStart;

    public ProcessParameters(int port, LogParameters parameters) {
        this.port = port;
        this.logParameters = parameters;
    }

    public ProcessParameters(int port) {
        this(port, new LogParameters());
    }

    public ProcessParameters(int port, File... files) {
        this(port, new LogParameters(files));
    }

    public ProcessParameters(int port, List<String> files) {
        this(port, new LogParameters(files));
    }

    public LogParameters getLogParameters() {
        return logParameters;
    }

    public int getPort() {
        return port;
    }

    public ProcessParameters whenHealthCheck(Callable<Boolean> runnable) {
        this.healthCheck = runnable;
        return this;
    }

    public ProcessParameters whenProcessTerminate(Runnable runnable) {
        this.processTerminate = runnable;
        return this;
    }

    public ProcessParameters whenGameSessionStarts(PRunnable<GameSession> runnable) {
        this.gameSessionStart = runnable;
        return this;
    }

    boolean healthCheck() {
        if (healthCheck != null) {
            try {
                return healthCheck.call();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        return false; //Maybe default to true?
    }

    void processTerminated() {
        if (processTerminate != null) {
            processTerminate.run();
        }
    }

    void gameSessionStarted(GameSession gameSession) {
        if (gameSessionStart != null) {
            gameSessionStart.run(gameSession);
        }
    }
}
