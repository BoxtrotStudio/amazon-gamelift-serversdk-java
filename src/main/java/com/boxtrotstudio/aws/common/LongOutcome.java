package com.boxtrotstudio.aws.common;

public class LongOutcome extends GenericOutcome {
    private long result;

    public LongOutcome(long result) {
        super();
        this.result = result;
    }

    public LongOutcome(GameLiftError error) {
        super(error);
    }

    public LongOutcome(GameLiftError error, long result) {
        super(error);
        this.result = result;
    }

    public long getResult() {
        return result;
    }
}
