package com.boxtrotstudio.aws.model;

import java.util.HashMap;

public class GameSession {
    private String GameSessionId;
    private String Name;
    private String FleetId;
    private int MaximumPlayerSessionCount = 0;
    private HashMap<String, String> gameProperties = new HashMap<String, String>();

    public String getGameSessionId() {
        return GameSessionId;
    }

    public void setGameSessionId(String gameSessionId) {
        GameSessionId = gameSessionId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getFleetId() {
        return FleetId;
    }

    public void setFleetId(String fleetId) {
        FleetId = fleetId;
    }

    public int getMaximumPlayerSessionCount() {
        return MaximumPlayerSessionCount;
    }

    public void setMaximumPlayerSessionCount(int maximumPlayerSessionCount) {
        MaximumPlayerSessionCount = maximumPlayerSessionCount;
    }

    public HashMap<String, String> getGameProperties() {
        return gameProperties;
    }

    public void setGameProperties(HashMap<String, String> gameProperties) {
        this.gameProperties = gameProperties;
    }
}
