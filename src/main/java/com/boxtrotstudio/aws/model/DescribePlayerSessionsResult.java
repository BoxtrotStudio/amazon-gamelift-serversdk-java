package com.boxtrotstudio.aws.model;

import com.amazon.whitewater.auxproxy.pbuffer.Sdk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DescribePlayerSessionsResult {
    static final int MAX_PLAYER_SESSIONS = 1024;

    private String nextToken;
    private ArrayList<PlayerSession> playerSessions = new ArrayList<>();

    public DescribePlayerSessionsResult() { }

    public DescribePlayerSessionsResult(Sdk.DescribePlayerSessionsResponse response) {
        this.nextToken = response.getNextToken();
        for (Sdk.PlayerSession playerSession : response.getPlayerSessionsList()) {
            PlayerSession session = new PlayerSession(playerSession);
            addPlayerSession(session);
        }
    }

    public void addPlayerSession(PlayerSession session) {
        if (playerSessions.size() < MAX_PLAYER_SESSIONS) {
            playerSessions.add(session);
        }
    }

    public List<PlayerSession> getPlayerSessions() {
        return Collections.unmodifiableList(playerSessions);
    }
}
