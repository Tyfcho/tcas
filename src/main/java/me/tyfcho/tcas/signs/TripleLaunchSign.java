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

public class TripleLaunchSign extends SignAction {
  private boolean inUse = false;

  public boolean match(SignActionEvent info) {
    return info.isType(new String[] { "triplelaunch" });
  }

  public void execute(SignActionEvent info) {
    if (!info.isAction(new SignActionType[] { SignActionType.GROUP_ENTER }))
      return;
    if (this.inUse)
      return;
    this.inUse = true;
    String[] speeds = info.getLine(2).split(" ");
    String[] times = info.getLine(3).split(" ");
    Station.StationConfig launch1 = new Station.StationConfig();
    launch1.setLaunchSpeed(Double.parseDouble(speeds[0]));
    Station wait = new Station(info, launch1);
    BlockFace launchDir1 = BlockFace.NORTH;
    BlockFace launchDir2 = BlockFace.SOUTH;
    switch (speeds[3].toUpperCase()) {
      case "E":
        launchDir1 = BlockFace.EAST;
        launchDir2 = BlockFace.WEST;
        break;
      case "S":
        launchDir1 = BlockFace.SOUTH;
        launchDir2 = BlockFace.NORTH;
        break;
      case "W":
        launchDir1 = BlockFace.WEST;
        launchDir2 = BlockFace.EAST;
        break;
    }
    BlockFace finalLaunchDir1 = launchDir1;
    BlockFace finalLaunchDir2 = launchDir2;
    info.getGroup().getActions().launchReset();
    info.getGroup().getProperties().setSlowingDown(false);
    wait.centerTrain();

    // First delayed task
    Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)AdvancedSigns.getInstance(), () -> {
      info.getGroup().getProperties().setSlowingDown(true);
      info.getGroup().getProperties().setSpeedLimit(3.0D);
      wait.launchTo(finalLaunchDir1);

      // Second delayed task
      Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)AdvancedSigns.getInstance(), () -> {
        // Add any additional code here for the second launch
        wait.launchTo(finalLaunchDir2);
      }, 20L * Integer.parseInt(times[1]));
    }, 20L * Integer.parseInt(times[0]));
  }

  public boolean build(SignChangeActionEvent event) {
    return
      SignBuildOptions.create()
      .setName(event.isCartSign() ? "cart triplelaunch" : "train triplelaunch")
      .setDescription("triplelaunch sign for triplelaunchcoasters")
      .handle(event.getPlayer());
  }
}
