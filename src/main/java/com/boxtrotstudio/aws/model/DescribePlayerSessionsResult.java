package com.boxtrotstudio.aws.model;

import java.util.ArrayList;
import java.util.List;

public class DescribePlayerSessionsResult {
    public static final int MAX_PLAYER_SESSION = 1024;

    private String NextToken;
    private List<PlayerSession> playerSessions = new ArrayList<PlayerSession>();

    public String getNextToken() {
        return NextToken;
    }

    public void setNextToken(String nextToken) {
        NextToken = nextToken;
    }

    public List<PlayerSession> getPlayerSessions() {
        return playerSessions;
    }

    public void setPlayerSessions(List<PlayerSession> playerSessions) {
        this.playerSessions = playerSessions;
    }

    public void addPlayer(PlayerSession playerSession) {
        if (playerSessions.size() < MAX_PLAYER_SESSION) {
            playerSessions.add(playerSession);
        }
    }
}
