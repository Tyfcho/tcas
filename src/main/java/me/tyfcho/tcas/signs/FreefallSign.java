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

public class FreefallSign extends SignAction {
  public boolean match(SignActionEvent signActionEvent) {
    return signActionEvent.isType(new String[] { "freefall" });
  }

  public void execute(SignActionEvent event) {
    if (event.isAction(new SignActionType[] { SignActionType.GROUP_ENTER })) {
      Station.StationConfig configDrop = new Station.StationConfig();
      configDrop.setLaunchSpeed(Double.parseDouble(event.getLine(2).split(" ")[0]));
      Station stationDrop = new Station(event, configDrop);
      stationDrop.getLaunchConfig().setDistance(Double.parseDouble(event.getLine(3).split(" ")[0]));
      stationDrop.centerTrain();
      event.getGroup().getProperties().setSpeedLimit(Double.parseDouble(event.getLine(2).split(" ")[0]));
      event.getGroup().getActions().launchReset();
      double time = 2.0D * Double.parseDouble(event.getLine(3).split(" ")[0]) / Double.parseDouble(event.getLine(2).split(" ")[0]);

      Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)AdvancedSigns.getInstance(), () -> {
        event.getGroup().getProperties().setSlowingDown(true);
        stationDrop.launchTo(BlockFace.DOWN);
        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)AdvancedSigns.getInstance(), () -> {
          // Add any additional code here that you want to run after the delay
        }, (long)time);
      }, Long.parseLong(event.getLine(2).split(" ")[2]) * 20L);
    }
  }


  public boolean build(SignChangeActionEvent event) {
    return
      SignBuildOptions.create()
      .setName(event.isCartSign() ? "cart freefall" : "train freefall")
      .setDescription("holds the train for x seconds then leaves the train free to handle by given")
      .handle(event.getPlayer());
  }
}