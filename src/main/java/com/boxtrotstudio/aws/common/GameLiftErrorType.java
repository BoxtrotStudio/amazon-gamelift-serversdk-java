package com.boxtrotstudio.aws.common;

public enum GameLiftErrorType {
    SERVICE_CALL_FAILED("Service call failed.", "An AWS service call has failed. See the root cause error for more information."),
    LOCAL_CONNECTION_FAILED("Local connection failed.", "Connection to local agent could not be established."),
    NETWORK_NOT_INITIALIZED("Network not initialized.", "Local network was not initialized. Have you called InitSDK()?"),
    GAMESESSION_ID_NOT_SET("GameSession id is not set.", "No game sessions are bound to this process."),
    UNKNOWN("Unknown Error", "An unexpected error has occurred.");

    String message, title;

    GameLiftErrorType(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getTitle() {
        return title;
    }
}
