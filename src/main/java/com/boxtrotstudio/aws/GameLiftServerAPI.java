package com.boxtrotstudio.aws;

import com.boxtrotstudio.aws.common.GenericOutcome;
import com.boxtrotstudio.aws.common.StringOutcome;

public class GameLiftServerAPI {
    public static final String SDK_VERSION = "3.1.5";

    public static StringOutcome getSdkVersion() {
        return new StringOutcome(SDK_VERSION);
    }
}
