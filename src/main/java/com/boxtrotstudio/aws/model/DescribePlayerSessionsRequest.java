package com.boxtrotstudio.aws.model;

public class DescribePlayerSessionsRequest {
    private String GameSessionId;
    private String PlayerId;
    private String PlayerSessionId;
    private String PlayerSessionStatusFilter;
    private String NextToken;
    private int Limit;

    public String getGameSessionId() {
        return GameSessionId;
    }

    public void setGameSessionId(String gameSessionId) {
        GameSessionId = gameSessionId;
    }

    public String getPlayerId() {
        return PlayerId;
    }

    public void setPlayerId(String playerId) {
        PlayerId = playerId;
    }

    public String getPlayerSessionId() {
        return PlayerSessionId;
    }

    public void setPlayerSessionId(String playerSessionId) {
        PlayerSessionId = playerSessionId;
    }

    public String getPlayerSessionStatusFilter() {
        return PlayerSessionStatusFilter;
    }

    public void setPlayerSessionStatusFilter(String playerSessionStatusFilter) {
        PlayerSessionStatusFilter = playerSessionStatusFilter;
    }

    public String getNextToken() {
        return NextToken;
    }

    public void setNextToken(String nextToken) {
        NextToken = nextToken;
    }

    public int getLimit() {
        return Limit;
    }

    public void setLimit(int limit) {
        Limit = limit;
    }
}
