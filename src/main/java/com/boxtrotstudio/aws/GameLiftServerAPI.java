package com.boxtrotstudio.aws;

import com.boxtrotstudio.aws.common.DescribePlayerSessionsOutcome;
import com.boxtrotstudio.aws.common.GenericOutcome;
import com.boxtrotstudio.aws.common.StringOutcome;
import com.boxtrotstudio.aws.model.DescribePlayerSessionsRequest;
import com.boxtrotstudio.aws.model.PlayerSessionCreationPolicy;

public class GameLiftServerAPI {
    static final String SDK_VERSION = "3.1.5";

    /**
     * @return The current SDK version.
     */
    public static StringOutcome getSdkVersion() {
        return new StringOutcome(SDK_VERSION);
    }

    /**
     * Initializes the GameLift server.
     * Should be called when the server starts, before any GameLift-dependent initialization happens.
     */
    public static GenericOutcome initSdk() {
        return ServerState.DEFULT_INSTANCE.initNetworking();
    }

    /**
     * Signals GameLift that the process is ready to receive GameSessions.
     * The onStartGameSession callback will be invoked when the server is bound to a GameSession. Game-property-dependent initialization (such as loading a
     * user-requested map) should take place at that time. The onHealthCheck callback is invoked asynchronously. There is no mechanism to
     * to destroy the resulting thread. If it does not complete in a given time period the server status will be reported as unhealthy.
     * @param processParameters The parameters required to successfully run the process.
     */
    public static GenericOutcome processReady(ProcessParameters processParameters) {
        return ServerState.DEFULT_INSTANCE.processReady(processParameters);
    }

    /**
     * Signals GameLift that the process is ending.
     * GameLift will eventually terminate the process and recycle the host. Once the process is marked as Ending,
     */
    public static GenericOutcome processEnding() {
        return ServerState.DEFULT_INSTANCE.processEnding();
    }

    /**
     * Reports to GameLift that the server process is now ready to receive player sessions.
     * Should be called once all GameSession initialization has finished.
     */
    public static GenericOutcome activateGameSession() {
        return ServerState.DEFULT_INSTANCE.activateGameSession();
    }

    /**
     * Reports to GameLift that the GameSession has now ended.
     * GameLift will now expect the server process to call either ProcessReady in order to launch a new GameSession
     * or ProcessEnding which will trigger this process and host to be recycled.
     */
    public static GenericOutcome terminiateGameSession() {
        return ServerState.DEFULT_INSTANCE.terminateGameSession();
    }

    /**
     * Update player session policy on the GameSession.
     */
    public static GenericOutcome updatePlayerSessionCreationPolicy(PlayerSessionCreationPolicy policy) {
        return ServerState.DEFULT_INSTANCE.updatePlayerSessionCreationPolicy(policy);
    }

    /**
     * @return The server's bound GameSession Id, if the server is Active.
     */
    public static StringOutcome getGameSessionId() {
        return ServerState.DEFULT_INSTANCE.getGameSessionId();
    }

    /**
     * Processes and validates a player session connection. This method should be called when a client requests a
     * connection to the server. The client should send the PlayerSessionID which it received from RequestPlayerSession
     * or GameLift::CreatePlayerSession to be passed into this function.
     * This method will return an UNEXPECTED_PLAYER_SESSION error if the player session ID is invalid.
     * @param playerSessionId the ID of the joining player's session.
     */
    public static GenericOutcome acceptPlayerSession(String playerSessionId) {
        return ServerState.DEFULT_INSTANCE.acceptPlayerSession(playerSessionId);
    }

    /**
     * Processes a player session disconnection. Should be called when a player leaves or otherwise disconnects from
     * the server.
     * @param playerSessionId the ID of the joining player's session.
     */
    public static GenericOutcome removePlayerSession(String playerSessionId) {
        return ServerState.DEFULT_INSTANCE.removePlayerSeession(playerSessionId);
    }

    /**
     * Retrieves properties for one or more player sessions.
     * @param describePlayerSessionsRequest The player sessions.
     * @return Request specifying which player sessions to describe.
     */
    public static DescribePlayerSessionsOutcome describePlayerSessions(DescribePlayerSessionsRequest describePlayerSessionsRequest) {
        return ServerState.DEFULT_INSTANCE.describePlayerSessions(describePlayerSessionsRequest);
    }

    /**
     * Destroy the SDK instance
     */
    public static GenericOutcome destroy() {
        ServerState.DEFULT_INSTANCE.shutdown();
        return new GenericOutcome();
    }
}
