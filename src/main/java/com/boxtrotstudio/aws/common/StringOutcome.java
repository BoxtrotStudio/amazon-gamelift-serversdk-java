package com.boxtrotstudio.aws.common;

public class StringOutcome extends GenericOutcome {

    private String result;

    public StringOutcome(String result) {
        super();
        this.result = result;
    }

    public StringOutcome(GameLiftError error) {
        super(error);
    }

    public StringOutcome(GameLiftError error, String result) {
        super(error);
        this.result = result;
    }

    public String getResult() {
        return result;
    }
}
