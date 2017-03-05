package com.boxtrotstudio.aws.model;

public class PlayerSession {
    private String playerId;
    private String playerSessionId;
    private String gameSessionId;
    private String fleetId;
    private String ipAddress;
    private String playerData;
    private int port;
    private long creationTime;
    private long terminationTime;
    private PlayerSessionStatus status;

    public PlayerSession() {
        port = 0;
        creationTime = 0;
        terminationTime = 0;
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
}
