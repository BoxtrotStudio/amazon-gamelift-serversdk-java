package com.boxtrotstudio.aws.model;

import com.amazon.whitewater.auxproxy.pbuffer.Sdk;

import java.util.HashMap;

public class GameSession {
    private String GameSessionId;
    private String Name;
    private boolean joinable;
    private String FleetId;
    private int MaximumPlayerSessionCount = 0;
    private int port;
    private String ipAddress;
    private String gameSessionData;
    private String MatchmakerData;
    private String DnsName;
    private HashMap<String, String> gameProperties = new HashMap<String, String>();

    public GameSession() { }

    public GameSession(Sdk.GameSession sdk) {
        GameSessionId = sdk.getGameSessionId();
        Name = sdk.getName();
        FleetId = sdk.getFleetId();
        MaximumPlayerSessionCount = sdk.getMaxPlayers();
        joinable = sdk.getJoinable();
        for (Sdk.GameProperty p : sdk.getGamePropertiesList()) {
            addGameProperty(p.getKey(), p.getValue());
        }

    }

    public boolean isJoinable() {
        return joinable;
    }

    public void setJoinable(boolean joinable) {
        this.joinable = joinable;
    }

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

    public void addGameProperty(String key, String value) {
        this.gameProperties.put(key, value);
    }

    public String getGameProperty(String key) {
        return this.gameProperties.get(key);
    }
}
