package com.boxtrotstudio.examples;

import com.boxtrotstudio.aws.GameLiftServerAPI;
import com.boxtrotstudio.aws.ProcessParameters;
import com.boxtrotstudio.aws.common.GameLiftError;
import com.boxtrotstudio.aws.common.GenericOutcome;

import java.io.File;

public class MyServerClass {

    public static void main(String[] args) throws GameLiftError {
        new MyServerClass().start();
    }

    public void start() throws GameLiftError {
        int port = 7777;

        GenericOutcome outcome = GameLiftServerAPI.initSdk();
        if (outcome.isSuccessful()) {
            File[] logs = new File[] {
                new File("server.log"),
                    new File("match.log")
            };

            //The constructor will use File.getAbsolutePath() for each File object
            ProcessParameters parameters = new ProcessParameters(port, logs);

            parameters
                    .whenHealthCheck(() -> {
                        //This is the HealthCheck callback.

                        //GameLift will invoke this callback every 60 seconds or so.

                        //Here, a game server might want to check the health of dependencies and such.

                        //Simply return true if healthy, false otherwise.

                        //The game server has 60 seconds to respond with its health status.
                        // GameLift will default to 'false' if the game server doesn't respond in time.

                        //In this case, we're always healthy!

                        return true;
                    })
                    .whenGameSessionStarts(param -> {
                        //When a game session is created, GameLift sends an activation request
                        // to the game server and passes along the game session object containing
                        // game properties and other settings.

                        //Here is where a game server should take action based on the game session object.

                        //Once the game server is ready to receive incoming player connections,
                        // it should invoke GameLiftServerAPI.ActivateGameSession()

                        GameLiftServerAPI.activateGameSession();
                    })
                    .whenProcessTerminate(() -> {
                        //OnProcessTerminate callback. GameLift will invoke this callback before
                        // shutting down an instance hosting this game server.

                        //It gives this game server a chance to save its state, communicate with services, etc.,
                        // before being shut down.

                        //In this case, we simply tell GameLift we are indeed going to shutdown.

                        GameLiftServerAPI.processEnding();
                    });

            GenericOutcome processStart = GameLiftServerAPI.processReady(parameters);

            if (processStart.isSuccessful()) {
                System.out.println("Process Ready!");
            } else {
                throw processStart.getError();
            }
        } else {
            throw outcome.getError();
        }
    }

    public void shutdown() {
        //Make sure to call GameLiftServerAPI.Destroy()
        // when the application quits.
        // This resets the local connection with GameLift's agent.

        GameLiftServerAPI.destroy();
    }
}
