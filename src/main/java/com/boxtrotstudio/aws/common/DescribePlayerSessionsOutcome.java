package com.boxtrotstudio.aws.common;

public class DescribePlayerSessionsOutcome extends GenericOutcome {
    private DescribePlayerSessionsResult result;

    public DescribePlayerSessionsOutcome(GameLiftError gameLiftError) {
        super(gameLiftError);
    }

    public DescribePlayerSessionsOutcome(DescribePlayerSessionsResult result) {
        this.result = result;
    }

    public DescribePlayerSessionsResult getResult() {
        return result;
    }
}
