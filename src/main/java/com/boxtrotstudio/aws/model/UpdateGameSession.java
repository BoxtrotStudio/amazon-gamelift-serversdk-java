package com.boxtrotstudio.aws.model;

public class UpdateGameSession {
    private GameSession gameSession;
    private UpdateReason updateReason;
    private String backfillTicketId;

    public UpdateGameSession(GameSession gameSession, UpdateReason updateReason, String backfillTicketId) {
        this.gameSession = gameSession;
        this.updateReason = updateReason;
        this.backfillTicketId = backfillTicketId;
    }

    public GameSession getGameSession() {
        return gameSession;
    }

    public UpdateReason getUpdateReason() {
        return updateReason;
    }

    public String getBackfillTicketId() {
        return backfillTicketId;
    }
}

