package com.boxtrotstudio.aws.model;

import java.util.ArrayList;

public class MatchmakerData {
    //TODO It seems like this class isn't used at all

    // Match fields
    private static final String FIELD_MATCH_ID = "matchId";
    private static final String FIELD_MATCHMAKING_CONFIG_ARN = "matchmakingConfigurationArn";
    private static final String FIELD_TEAMS = "teams";

    // Team fields
    private static final String FIELD_NAME = "name";
    private static final String FIELD_PLAYERS = "players";

    // Player fields
    private static final String FIELD_PLAYER_ID = "playerId";
    private static final String FIELD_ATTRIBUTES = "attributes";
    private static final String FIELD_LATENCY = "attributes";

    // Attribute fields
    private static final String FIELD_ATTRIBUTE_TYPE = "attributeType";
    private static final String FIELD_ATTRIBUTE_VALUE = "valueAttribute";
    
    public String matchId;
    public String matchmakingConfigurationArn;
    public ArrayList<Player> Players = new ArrayList<>();

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getMatchmakingConfigurationArn() {
        return matchmakingConfigurationArn;
    }

    public void setMatchmakingConfigurationArn(String matchmakingConfigurationArn) {
        this.matchmakingConfigurationArn = matchmakingConfigurationArn;
    }

    public ArrayList<Player> getPlayers() {
        return Players;
    }

    public void setPlayers(ArrayList<Player> players) {
        Players = players;
    }
}
