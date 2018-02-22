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
    private String matchmakerData;
    private String dnsName;
    private HashMap<String, String> gameProperties = new HashMap<String, String>();

    public GameSession() { }

    public GameSession(Sdk.GameSession sdk) {
        GameSessionId = sdk.getGameSessionId();
        Name = sdk.getName();
        FleetId = sdk.getFleetId();
        MaximumPlayerSessionCount = sdk.getMaxPlayers();
        joinable = sdk.getJoinable();
        port = sdk.getPort();
        ipAddress = sdk.getIpAddress();
        gameSessionData = sdk.getGameSessionData();
        matchmakerData = sdk.getMatchmakerData();
        dnsName = sdk.getDnsName();
        for (Sdk.GameProperty p : sdk.getGamePropertiesList()) {
            addGameProperty(p.getKey(), p.getValue());
        }

    }

    public String getGameSessionId() {
        return GameSessionId;
    }

    public String getName() {
        return Name;
    }

    public boolean isJoinable() {
        return joinable;
    }

    public String getFleetId() {
        return FleetId;
    }

    public int getMaximumPlayerSessionCount() {
        return MaximumPlayerSessionCount;
    }

    public int getPort() {
        return port;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getGameSessionData() {
        return gameSessionData;
    }

    public String getMatchmakerData() {
        return matchmakerData;
    }

    public String getDnsName() {
        return dnsName;
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
}
