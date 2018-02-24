package com.boxtrotstudio.aws.model;

import com.amazon.whitewater.auxproxy.pbuffer.Sdk;

public class StopMatchBackfillRequest {
    private String ticketId;
    private String gameSessionArn;
    private String matchmakingConfigurationArn;

    public StopMatchBackfillRequest(String ticketId, String gameSessionArn, String matchmakingConfigurationArn) {
        this.ticketId = ticketId;
        this.gameSessionArn = gameSessionArn;
        this.matchmakingConfigurationArn = matchmakingConfigurationArn;
    }

    public StopMatchBackfillRequest() {
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getGameSessionArn() {
        return gameSessionArn;
    }

    public void setGameSessionArn(String gameSessionArn) {
        this.gameSessionArn = gameSessionArn;
    }

    public String getMatchmakingConfigurationArn() {
        return matchmakingConfigurationArn;
    }

    public void setMatchmakingConfigurationArn(String matchmakingConfigurationArn) {
        this.matchmakingConfigurationArn = matchmakingConfigurationArn;
    }

    public Sdk.StopMatchmakingRequest createRequest() {
        Sdk.StopMatchmakingRequest.Builder builder = Sdk.StopMatchmakingRequest.newBuilder();

        return builder.setTicketId(ticketId)
                .setGameSessionArn(gameSessionArn)
                .setMatchmakingConfigurationArn(matchmakingConfigurationArn)
                .build();
    }
}
