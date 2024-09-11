package me.tyfcho.tcas.signs;

import com.bergerkiller.bukkit.tc.Station;
import com.bergerkiller.bukkit.tc.events.SignActionEvent;
import com.bergerkiller.bukkit.tc.events.SignChangeActionEvent;
import com.bergerkiller.bukkit.tc.signactions.SignAction;
import com.bergerkiller.bukkit.tc.signactions.SignActionType;
import com.bergerkiller.bukkit.tc.utils.SignBuildOptions;
import me.tyfcho.tcas.AdvancedSigns;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class HoldAndReleaseSign extends SignAction {
  public boolean match(SignActionEvent event) {
    return event.isType(new String[] { "holdandrelease" });
  }

  public void execute(SignActionEvent event) {
    if (event.isAction(new SignActionType[] { SignActionType.GROUP_ENTER })) {
      Station station = new Station(event);
      event.getGroup().getActions().launchReset();
      event.getGroup().getProperties().setSlowingDown(false);
      station.centerTrain();
      Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin) AdvancedSigns.getInstance(), () -> event.getGroup().getProperties().setSlowingDown(true),

          Integer.parseInt(event.getLine(2)) * 20L);
    }
  }

  public boolean build(SignChangeActionEvent event) {
    return
      SignBuildOptions.create()
      .setName(event.isCartSign() ? "cart holdandrelease" : "train holdandrelease")
      .setDescription("holds the train for x seconds then leaves the train free to handle by gravity")
      .handle(event.getPlayer());
  }
}