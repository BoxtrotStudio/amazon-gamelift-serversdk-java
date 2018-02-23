package com.boxtrotstudio.aws.model;

public class StartMatchBackfillRequest {
    private String ticketId;
    private String gameSessionArn;
    private String matchmakingConfigurationArn;
    private Player[] players;

    public StartMatchBackfillRequest(String ticketId, String gameSessionArn, String matchmakingConfigurationArn, Player[] players) {
        this.ticketId = ticketId;
        this.gameSessionArn = gameSessionArn;
        this.matchmakingConfigurationArn = matchmakingConfigurationArn;
        this.players = players;
    }

    public StartMatchBackfillRequest() {
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

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }
}
