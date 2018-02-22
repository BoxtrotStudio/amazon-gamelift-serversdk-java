package com.boxtrotstudio.aws.model;

public class DescribePlayerSessionsRequest {
    private String gameSessionId;
    private String playerId;
    private String playerSessionId;
    private String playerSessionStatusFilter;
    private String nextToken;
    private int limit;

    public String getGameSessionId() {
        return gameSessionId;
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getPlayerSessionId() {
        return playerSessionId;
    }

    public String getPlayerSessionStatusFilter() {
        return playerSessionStatusFilter;
    }

    public String getNextToken() {
        return nextToken;
    }

    public int getLimit() {
        return limit;
    }
}
