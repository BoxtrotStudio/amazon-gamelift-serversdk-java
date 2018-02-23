package com.boxtrotstudio.aws.model;

import com.amazon.whitewater.auxproxy.pbuffer.Sdk;

import java.util.HashMap;

public class Player {
    private String playerId;
    private String team;
    private HashMap<String, AttributeValue> playerAttributes = new HashMap<>();
    private HashMap<String, Integer> latencyInMS = new HashMap<>();

    public Player(String playerId, String team, HashMap<String, AttributeValue> playerAttributes, HashMap<String, Integer> latencyInMS) {
        this.playerId = playerId;
        this.team = team;
        this.playerAttributes = playerAttributes;
        this.latencyInMS = latencyInMS;
    }

    public Player(String playerId, String team) {
        this.playerId = playerId;
        this.team = team;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setPlayerAttributes(HashMap<String, AttributeValue> playerAttributes) {
        this.playerAttributes = playerAttributes;
    }

    public void setLatencyInMS(HashMap<String, Integer> latencyInMS) {
        this.latencyInMS = latencyInMS;
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getTeam() {
        return team;
    }

    public HashMap<String, AttributeValue> getPlayerAttributes() {
        return playerAttributes;
    }

    public HashMap<String, Integer> getLatencyInMS() {
        return latencyInMS;
    }

    public Sdk.Player createBufferedPlayer() {
        Sdk.Player.Builder builder = Sdk.Player.newBuilder()
                .setPlayerId(playerId)
                .setTeam(team);

        for (String key : latencyInMS.keySet()) {
            builder.putLatencyInMs(key, latencyInMS.get(key));
        }

        for (String key : playerAttributes.keySet()) {
            builder.putPlayerAttributes(key, playerAttributes.get(key).createBufferedAttributeValue());
        }

        return builder.build();
    }
}
