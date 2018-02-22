package com.boxtrotstudio.aws.model;

public enum UpdateReason {
    MATCHMAKING_DATA_UPDATED,
    BACKFILL_FAILED,
    BACKFILL_TIMED_OUT,
    BACKFILL_CANCELLED,
    UNKNOWN;
    
    final static String MATCHMAKING_DATA_UPDATED_REASON = "MATCHMAKING_DATA_UPDATED";
    final static String BACKFILL_FAILED_REASON = "BACKFILL_FAILED";
    final static String BACKFILL_TIMED_OUT_REASON = "BACKFILL_TIMED_OUT";
    final static String BACKFILL_CANCELLED_REASON = "BACKFILL_CANCELLED";
    final static String UNKNOWN_REASON = "UNKNOWN";

    public static UpdateReason getUpdateReasonForName(String name)
    {
        switch (name)
        {
            case MATCHMAKING_DATA_UPDATED_REASON:
                return UpdateReason.MATCHMAKING_DATA_UPDATED;
            case BACKFILL_FAILED_REASON:
                return UpdateReason.BACKFILL_FAILED;
            case BACKFILL_TIMED_OUT_REASON:
                return UpdateReason.BACKFILL_TIMED_OUT;
            case BACKFILL_CANCELLED_REASON:
                return UpdateReason.BACKFILL_CANCELLED;
            default:
                return UpdateReason.UNKNOWN;
        }
    }

    public static String getNameForUpdateReason(UpdateReason value)
    {
        switch (value)
        {
            case MATCHMAKING_DATA_UPDATED:
                return MATCHMAKING_DATA_UPDATED_REASON;
            case BACKFILL_FAILED:
                return BACKFILL_FAILED_REASON;
            case BACKFILL_TIMED_OUT:
                return BACKFILL_TIMED_OUT_REASON;
            case BACKFILL_CANCELLED:
                return BACKFILL_CANCELLED_REASON;
            default:
                return UNKNOWN_REASON;
        }
    }
}
