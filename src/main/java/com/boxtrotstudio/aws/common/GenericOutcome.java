package com.boxtrotstudio.aws.common;

public class GenericOutcome {
    private GameLiftError error;
    private boolean success;

    public GenericOutcome() {
        success = true;
    }

    public GenericOutcome(GameLiftError error) {
        this.error = error;
        this.success = false;
    }

    public GameLiftError getError() {
        return error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setError(GameLiftError error) {
        this.error = error;
    }
}
