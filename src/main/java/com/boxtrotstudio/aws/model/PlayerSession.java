package com.boxtrotstudio.aws.model;

import com.amazon.whitewater.auxproxy.pbuffer.Sdk;

public class PlayerSession {
    private String playerId;
    private String playerSessionId;
    private String gameSessionId;
    private String fleetId;
    private String ipAddress;
    private String playerData;
    private int port = 0;
    private long creationTime = 0;
    private long terminationTime = 0;
    private PlayerSessionStatus status;
    private String dnsName;

    public PlayerSession() {
    }

    public PlayerSession(Sdk.PlayerSession response) {
        this.playerData = response.getPlayerData();
        this.playerId = response.getPlayerId();
        this.gameSessionId = response.getGameSessionId();
        this.fleetId = response.getFleetId();
        this.ipAddress = response.getIpAddress();
        this.port = response.getPort();
        this.creationTime = response.getCreationTime();
        this.terminationTime = response.getTerminationTime();
        this.status = PlayerSessionStatus.getPlayerSessionStatusForName(response.getStatus());
        this.dnsName = response.getDnsName();
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getPlayerSessionId() {
        return playerSessionId;
    }

    public void setPlayerSessionId(String playerSessionId) {
        this.playerSessionId = playerSessionId;
    }

    public String getGameSessionId() {
        return gameSessionId;
    }

    public void setGameSessionId(String gameSessionId) {
        this.gameSessionId = gameSessionId;
    }

    public String getFleetId() {
        return fleetId;
    }

    public void setFleetId(String fleetId) {
        this.fleetId = fleetId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getPlayerData() {
        return playerData;
    }

    public void setPlayerData(String playerData) {
        this.playerData = playerData;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }

    public long getTerminationTime() {
        return terminationTime;
    }

    public void setTerminationTime(long terminationTime) {
        this.terminationTime = terminationTime;
    }

    public PlayerSessionStatus getStatus() {
        return status;
    }

    public void setStatus(PlayerSessionStatus status) {
        this.status = status;
    }

    public String getDnsName() {
        return dnsName;
    }

    public void setDnsName(String dnsName) {
        this.dnsName = dnsName;
    }
}
