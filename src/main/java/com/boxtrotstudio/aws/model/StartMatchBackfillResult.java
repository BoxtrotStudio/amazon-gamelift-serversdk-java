package com.boxtrotstudio.aws.model;

import com.amazon.whitewater.auxproxy.pbuffer.Sdk;

public class StartMatchBackfillResult {

    private String ticketId;

    public StartMatchBackfillResult(String ticketId) {
        this.ticketId = ticketId;
    }

    public StartMatchBackfillResult(Sdk.BackfillMatchmakingResponse response) {
        ticketId = response.getTicketId();
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }
}
