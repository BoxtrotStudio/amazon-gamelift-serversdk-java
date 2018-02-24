package com.boxtrotstudio.aws.model;

public enum PlayerSessionStatus {
    NOT_SET,
    RESERVED,
    ACTIVE,
    COMPLETED,
    TIMEDOUT;

    public static PlayerSessionStatus getPlayerSessionStatusForName(String name) {
        switch (name)
        {
            case "RESERVED":
                return PlayerSessionStatus.RESERVED;
            case "ACTIVE":
                return PlayerSessionStatus.ACTIVE;
            case "COMPLETED":
                return PlayerSessionStatus.COMPLETED;
            case "TIMEDOUT":
                return PlayerSessionStatus.TIMEDOUT;
            default:
                return PlayerSessionStatus.NOT_SET;
        }
    }

    public static String getNameForPlayerSessionStatus(PlayerSessionStatus value)
    {
        switch (value)
        {
            case RESERVED:
                return RESERVED.name();
            case ACTIVE:
                return ACTIVE.name();
            case COMPLETED:
                return COMPLETED.name();
            case TIMEDOUT:
                return TIMEDOUT.name();
            default:
                return NOT_SET.name();
        }
    }
}
