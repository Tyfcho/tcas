package me.tyfcho.tcas.signs;

import com.bergerkiller.bukkit.tc.Station;
import com.bergerkiller.bukkit.tc.controller.status.TrainStatus;
import com.bergerkiller.bukkit.tc.events.SignActionEvent;
import com.bergerkiller.bukkit.tc.events.SignChangeActionEvent;
import com.bergerkiller.bukkit.tc.signactions.SignAction;
import com.bergerkiller.bukkit.tc.signactions.SignActionType;
import com.bergerkiller.bukkit.tc.utils.SignBuildOptions;
import me.tyfcho.tcas.AdvancedSigns;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class MonorailSign extends SignAction {

    @Override
    public boolean match(SignActionEvent event) {
        return event.isType(new String[]{"monorail"});
    }

    @Override
    public void execute(SignActionEvent event) {
        int[] countdown = new int[1];  // To store the countdown task ID

        if (event.isAction(new SignActionType[]{SignActionType.GROUP_ENTER})) {
            event.getGroup().getActions().launchReset();
            Station station = new Station(event);
            station.centerTrain();

            int[] currentCountDownState = { Integer.parseInt(event.getLine(2)) };

            // First delayed task (100 ticks delay)
            Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin) AdvancedSigns.getInstance(), () -> {
                event.getGroup().getProperties().setPlayersEnter(true);
                event.getGroup().getProperties().setPlayersExit(true);
                event.getGroup().playNamedAnimation("load");

                // Schedule repeating task to handle countdown
                countdown[0] = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin) AdvancedSigns.getInstance(), () -> {
                    // Countdown logic
                    if (currentCountDownState[0] > 0) {
                        currentCountDownState[0]--;  // Decrement countdown
                    } else {
                        // End of countdown, perform the train release
                        // Make sure to use the appropriate direction here
                        station.launchTo(station.getNextDirectionFace()); // Assuming getNextDirection() returns BlockFace
                        Bukkit.getScheduler().cancelTask(countdown[0]);  // Cancel the repeating task
                    }
                }, 0L, 20L);  // Runs every 20 ticks (1 second)

            }, 100L);  // Delay of 100 ticks before starting the repeating task
        }
    }


    @Override
    public boolean build(SignChangeActionEvent event) {
        return SignBuildOptions.create()
                .setName(event.isCartSign() ? "cart holdandrelease" : "train holdandrelease")
                .setDescription("Holds the train for x seconds, then releases it to be handled by gravity")
                .handle(event.getPlayer());
    }
}
