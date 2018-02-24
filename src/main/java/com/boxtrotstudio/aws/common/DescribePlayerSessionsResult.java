package com.boxtrotstudio.aws.common;

import com.amazon.whitewater.auxproxy.pbuffer.Sdk;
import com.boxtrotstudio.aws.common.PlayerSession;
import com.boxtrotstudio.aws.model.PlayerSessionStatus;

import java.util.ArrayList;
import java.util.List;

public class DescribePlayerSessionsResult {
    public static final int MAX_PLAYER_SESSION = 1024;

    private String NextToken;
    private List<PlayerSession> playerSessions = new ArrayList<PlayerSession>();

    public DescribePlayerSessionsResult() { }

    public DescribePlayerSessionsResult(Sdk.DescribePlayerSessionsResponse response) {
        setNextToken(response.getNextToken());
        setPlayerSessions(new ArrayList<PlayerSession>());
        for (Sdk.PlayerSession session : response.getPlayerSessionsList()) {
            PlayerSession newSession = new PlayerSession(session);

            addPlayer(newSession);
        }
    }

    public String getNextToken() {
        return NextToken;
    }

    void setNextToken(String nextToken) {
        NextToken = nextToken;
    }

    public List<PlayerSession> getPlayerSessions() {
        return playerSessions;
    }

    void setPlayerSessions(List<PlayerSession> playerSessions) {
        this.playerSessions = playerSessions;
    }

    public void addPlayer(PlayerSession playerSession) {
        if (playerSessions.size() < MAX_PLAYER_SESSION) {
            playerSessions.add(playerSession);
        }
    }
}
