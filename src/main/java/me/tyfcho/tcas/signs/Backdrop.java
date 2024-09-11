package me.tyfcho.tcas.signs;

import com.bergerkiller.bukkit.tc.Station;
import com.bergerkiller.bukkit.tc.events.SignActionEvent;
import com.bergerkiller.bukkit.tc.events.SignChangeActionEvent;
import com.bergerkiller.bukkit.tc.signactions.SignAction;
import com.bergerkiller.bukkit.tc.signactions.SignActionType;
import com.bergerkiller.bukkit.tc.utils.SignBuildOptions;
import me.tyfcho.tcas.AdvancedSigns;

import org.bukkit.Bukkit;
import org.bukkit.block.BlockFace;
import org.bukkit.plugin.Plugin;

public class Backdrop extends SignAction { // Class Backdrop extends SignAction which defines behavior for certain signs in the TrainCarts plugin
  private boolean isdrop = true; // This boolean flag keeps track of whether the next action should be a drop or not.

  // This method checks if the sign's type is "backdrop"
  public boolean match(SignActionEvent info) {
    return info.isType(new String[] { "backdrop" }); // Returns true if the sign matches the type "backdrop"
  }

  // This method gets triggered when a certain sign action occurs (e.g. when a train enters the sign's block)
  public void execute(SignActionEvent event) {
    // Check if the event is of the type GROUP_ENTER (train group entering)
    if (!event.isAction(new SignActionType[] { SignActionType.GROUP_ENTER }))
      return; // If not, return early without doing anything

    // Parse values from the sign's text
    int warten1 = Integer.parseInt(event.getLine(2)); // Parse the waiting time (warten1) from the second line of the sign
    int fallmeter = Integer.parseInt(event.getLine(3).split(" ")[0]); // Parse the drop distance (fallmeter) from the third line
    int bremsmeter = Integer.parseInt(event.getLine(3).split(" ")[1]); // Parse the braking distance (bremsmeter) from the third line

    // Create a configuration for the drop action
    Station.StationConfig drop = new Station.StationConfig(); // Create a StationConfig object for drop
    drop.getLaunchConfig().setDistance(fallmeter); // Set the drop distance
    drop.setOffsetFromCenter(drop.getOffsetFromCenter()); // Possibly centers the train (offset from center)
    drop.setLaunchSpeed(0.55D); // Set the speed at which the train will be launched for the drop (0.55)

    // Calculate acceleration and duration for the drop
    double dropacceleration = 0.30250000000000005D / (2 * fallmeter); // Calculate acceleration for the drop
    double dropduration = 0.55D / dropacceleration; // Calculate how long the drop will last

    // Create a configuration for the braking action
    Station.StationConfig brake = new Station.StationConfig(); // Create a StationConfig object for braking
    brake.getLaunchConfig().setDistance(bremsmeter); // Set the braking distance
    brake.setLaunchSpeed(0.0D); // Set the speed for braking (0.0, which means stopping)

    // Calculate acceleration and duration for braking
    double brakeacceleration = -0.30250000000000005D / (2 * bremsmeter); // Calculate braking acceleration
    double brakeduration = -0.55D / brakeacceleration; // Calculate braking duration

    // Check if the flag isdrop is true, meaning it's time for a drop
    if (this.isdrop) {
      event.getGroup().getActions().launchReset(); // Reset launch actions for the train group
      Station dropstation = new Station(event, drop); // Create a Station object for the drop using the event and config
      dropstation.centerTrain(); // Center the train at the station

      // Schedule the drop action to happen asynchronously after the calculated drop duration
      Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)AdvancedSigns.getInstance(), () -> {
        this.isdrop = false; // Set isdrop to false after the drop, indicating braking is next
        event.getGroup().getProperties().setSlowingDown(true); // Enable slowing down for the train group
        event.getGroup().getProperties().setSpeedLimit(2.0D); // Set the train's speed limit to 2.0
        dropstation.launchTo(BlockFace.DOWN); // Launch the train downwards, simulating a drop

        // Schedule another task for after the dropduration to prepare for the braking phase
        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin) AdvancedSigns.getInstance(), () -> {
          // After the drop is complete, initiate the braking phase
          Station brakeStation = new Station(event, brake);  // Create a station for braking
          brakeStation.centerTrain();  // Center the train at the station

          // Start braking by launching the train with speed 0 (stopping the train)
          brakeStation.launchTo(BlockFace.DOWN);  // This could be changed depending on direction (if not down)

          // Optionally, you can schedule another task after brakeduration if further actions are needed
        }, (long) dropduration);
      });
    } else {
      this.isdrop = true; // If isdrop is false, reset it to true for the next action
    }
  }

  // This method handles the creation of a new sign in the game world
  public boolean build(SignChangeActionEvent event) {
    // Configure and build the sign with appropriate options (depending on whether it's for a cart or a train)
    return SignBuildOptions.create()
            .setName(event.isCartSign() ? "cart backdrop" : "train backdrop") // Set sign name based on type
            .setDescription("creates a droptower ending sequence") // Set description for the sign
            .handle(event.getPlayer()); // Handle the event, notifying the player that the sign was successfully built
  }
}
