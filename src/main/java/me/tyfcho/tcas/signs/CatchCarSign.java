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

public class CatchCarSign extends SignAction {
  public boolean match(SignActionEvent signActionEvent) {
    return signActionEvent.isType(new String[] { "catchcar" });
  }

  public void execute(SignActionEvent event) {
    if (event.isAction(new SignActionType[] { SignActionType.GROUP_ENTER })) {
      Station.StationConfig configDrop = new Station.StationConfig();
      configDrop.setLaunchSpeed(Double.parseDouble(event.getLine(2).split(" ")[0]));
      Station stationDrop = new Station(event, configDrop);
      stationDrop.getLaunchConfig().setDistance(5.0D);
      stationDrop.centerTrain();
      event.getGroup().getProperties().setSpeedLimit(Double.parseDouble(event.getLine(2)));
      event.getGroup().getActions().launchReset();
      Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)AdvancedSigns.getInstance(), () -> {
            event.getGroup().getProperties().setSlowingDown(false);
            stationDrop.launchTo(BlockFace.DOWN);
          });
    }
  }

  public boolean build(SignChangeActionEvent event) {
    return
      SignBuildOptions.create()
      .setName(event.isCartSign() ? "cart catchcar" : "train catchcar")
      .setDescription("holds the catchcar for x seconds then moves back down")
      .handle(event.getPlayer());
  }
}