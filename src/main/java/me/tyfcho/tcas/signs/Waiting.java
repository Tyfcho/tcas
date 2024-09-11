package me.tyfcho.tcas.signs;

import com.bergerkiller.bukkit.tc.Station;
import com.bergerkiller.bukkit.tc.events.SignActionEvent;
import com.bergerkiller.bukkit.tc.events.SignChangeActionEvent;
import com.bergerkiller.bukkit.tc.signactions.SignAction;
import com.bergerkiller.bukkit.tc.signactions.SignActionType;
import me.tyfcho.tcas.AdvancedSigns;
import java.util.Locale;
import org.bukkit.Bukkit;
import org.bukkit.block.BlockFace;
import org.bukkit.plugin.Plugin;

public class Waiting extends SignAction {
  int timer = 0;

  boolean runningTimer = false;

  int countdown;

  public boolean match(SignActionEvent event) {
    return event.isType(new String[] { "warten" });
  }

  public void execute(SignActionEvent event) {
    if (!event.isAction(new SignActionType[] { SignActionType.GROUP_ENTER }))
      return;
    if (!this.runningTimer) {
      this.runningTimer = true;
      this.timer = Integer.parseInt(event.getLine(2));
      this.countdown = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)AdvancedSigns.getInstance(), () -> {
            if (this.timer != 0) {
              this.timer--;
            } else {
              this.runningTimer = false;
              Bukkit.getScheduler().cancelTask(this.countdown);
              this.countdown = 0;
            }
          },0L, 20L);
    } else {
      Station station = new Station(event);
      event.getGroup().getActions().launchReset();
      station.centerTrain();
      Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)AdvancedSigns.getInstance(), () -> {
            station.launchTo(BlockFace.valueOf(event.getLine(3).toUpperCase(Locale.ROOT)));
            this.timer = Integer.parseInt(event.getLine(2));
          },this.timer * 20L);
    }
  }

  public boolean build(SignChangeActionEvent event) {
    return false;
  }
}