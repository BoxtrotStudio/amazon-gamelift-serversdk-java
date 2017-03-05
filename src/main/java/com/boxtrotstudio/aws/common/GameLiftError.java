package com.boxtrotstudio.aws.common;

public class GameLiftError extends Exception {
    private GameLiftErrorType errorType;

    public GameLiftError() {
        super();
    }

    public GameLiftError(GameLiftErrorType type) {
        super(type.message);

        this.errorType = type;
    }

    public GameLiftError(GameLiftErrorType type, Throwable cause) {
        super(type.message, cause);

        this.errorType = type;
    }

    public GameLiftErrorType getErrorType() {
        return errorType;
    }

    public String getErrorMessage() {
        return errorType.message;
    }

    public String getErrorName() {
        return errorType.title;
    }
}
