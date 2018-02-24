package com.boxtrotstudio.aws.model;

public enum PlayerSessionCreationPolicy {
    NOT_SET,
    ACCEPT_ALL,
    DENY_ALL;

    public static PlayerSessionCreationPolicy GetPlayerSessionCreationPolicyForName(String name) {
        switch (name)
        {
            case "ACCEPT_ALL":
                return PlayerSessionCreationPolicy.ACCEPT_ALL;
            case "DENY_ALL":
                return PlayerSessionCreationPolicy.DENY_ALL;
            default:
                return PlayerSessionCreationPolicy.NOT_SET;
        }
    }

    public static String GetNameForPlayerSessionCreationPolicy(PlayerSessionCreationPolicy value) {
        switch (value)
        {
            case ACCEPT_ALL:
                return ACCEPT_ALL.name();
            case DENY_ALL:
                return DENY_ALL.name();
            default:
                return NOT_SET.name();
        }
    }
}
