package com.boxtrotstudio.aws.common;

import com.boxtrotstudio.aws.model.StartMatchBackfillResult;

public class StartMatchBackfillOutcome extends GenericOutcome {

    private StartMatchBackfillResult result;

    public StartMatchBackfillOutcome(StartMatchBackfillResult result) {
        this.result = result;
    }

    public StartMatchBackfillOutcome(GameLiftError error, StartMatchBackfillResult result) {
        super(error);
        this.result = result;
    }

    public StartMatchBackfillResult getResult() {
        return result;
    }
}
