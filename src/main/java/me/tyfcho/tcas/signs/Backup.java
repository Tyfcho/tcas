package me.tyfcho.tcas.signs;

import com.bergerkiller.bukkit.tc.Station;
import com.bergerkiller.bukkit.tc.events.SignActionEvent;
import com.bergerkiller.bukkit.tc.events.SignChangeActionEvent;
import com.bergerkiller.bukkit.tc.signactions.SignAction;
import com.bergerkiller.bukkit.tc.signactions.SignActionType;
import me.tyfcho.tcas.AdvancedSigns;
import org.bukkit.Bukkit;
import org.bukkit.block.BlockFace;
import org.bukkit.plugin.Plugin;

public class Backup extends SignAction {
  private boolean isup = true;

  public boolean match(SignActionEvent info) {
    return info.isType(new String[] { "backup" });
  }

  public void execute(SignActionEvent info) {
    if (!info.isAction(new SignActionType[] { SignActionType.GROUP_ENTER }))
      return;
    if (this.isup) {
      this.isup = false;
    } else {
      long time = Long.parseLong(info.getLine(3));
      this.isup = true;
      Station.StationConfig config = new Station.StationConfig();
      config.setLaunchSpeed(Double.parseDouble(info.getLine(2)));
      config.getLaunchConfig().setDistance(5.0D);
      Station station = new Station(info, config);
      info.getGroup().getProperties().setSlowingDown(false);
      info.getGroup().getActions().launchReset();
      station.centerTrain();
      Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)AdvancedSigns.getInstance(), () -> station.launchTo(BlockFace.UP), time * 20L);
    }
  }

  public boolean build(SignChangeActionEvent event) {
    return false;
  }

  public void setIsup(boolean isup) {
    this.isup = isup;
  }
}