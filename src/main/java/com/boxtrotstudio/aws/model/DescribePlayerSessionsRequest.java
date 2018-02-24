package com.boxtrotstudio.aws.model;

import com.amazon.whitewater.auxproxy.pbuffer.Sdk;

public class DescribePlayerSessionsRequest {
    private String gameSessionId;
    private String playerId;
    private String playerSessionId;
    private String playerSessionStatusFilter;
    private String nextToken;
    private int limit;

    public DescribePlayerSessionsRequest(int limit) {
        this.limit = limit;
    }

    public void setGameSessionId(String gameSessionId) {
        this.gameSessionId = gameSessionId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public void setPlayerSessionId(String playerSessionId) {
        this.playerSessionId = playerSessionId;
    }

    public void setPlayerSessionStatusFilter(String playerSessionStatusFilter) {
        this.playerSessionStatusFilter = playerSessionStatusFilter;
    }

    public void setNextToken(String nextToken) {
        this.nextToken = nextToken;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

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

    public Sdk.DescribePlayerSessionsRequest createRequest() {
        Sdk.DescribePlayerSessionsRequest.Builder builder = Sdk.DescribePlayerSessionsRequest.newBuilder();

        if (gameSessionId != null)
        {
            builder.setGameSessionId(gameSessionId);
        }
        if(playerId != null)
        {
            builder.setPlayerId(playerId);
        }
        if(playerSessionId != null)
        {
            builder.setPlayerSessionId(playerSessionId);
        }
        if(playerSessionStatusFilter != null)
        {
            builder.setPlayerSessionStatusFilter(playerSessionStatusFilter);
        }
        if (nextToken != null)
        {
            builder.setNextToken(nextToken);
        }

        builder.setLimit(limit);

        return builder.build();
    }
}
