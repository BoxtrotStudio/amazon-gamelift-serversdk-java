package com.boxtrotstudio.aws.model;

public class StartMatchBackfillResult {

    private String ticketId;

    public StartMatchBackfillResult(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }
}
