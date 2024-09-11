package me.tyfcho.tcas.signs;

import com.bergerkiller.bukkit.tc.Station;
import com.bergerkiller.bukkit.tc.events.SignActionEvent;
import com.bergerkiller.bukkit.tc.events.SignChangeActionEvent;
import com.bergerkiller.bukkit.tc.signactions.SignAction;
import com.bergerkiller.bukkit.tc.signactions.SignActionType;
import com.bergerkiller.bukkit.tc.utils.SignBuildOptions;
import me.tyfcho.tcas.AdvancedSigns;
import java.util.Locale;
import org.bukkit.Bukkit;
import org.bukkit.block.BlockFace;
import org.bukkit.plugin.Plugin;

public class BrakeAndSlowdownFalseSign extends SignAction {
  public boolean match(SignActionEvent event) {
    return event.isType(new String[] { "brakeandslow" });
  }

  public void execute(SignActionEvent event) {
    if (event.isAction(new SignActionType[] { SignActionType.GROUP_ENTER })) {
      event.getGroup().getActions().launchReset();
      Station.StationConfig config = new Station.StationConfig();
      double curentSpeed = event.getMember().getRealSpeed();
      String[] line2 = event.getLine(2).split(",");
      config.setLaunchSpeed(Double.parseDouble(line2[1]));
      Station station = new Station(event, config);
      double endSpeed = Double.parseDouble(line2[1]);
      int distance = Integer.parseInt(line2[0]);
      BlockFace direction = BlockFace.valueOf(event.getLine(3).toUpperCase(Locale.ROOT));
      double acceleration = (endSpeed * endSpeed - curentSpeed * curentSpeed) / (2 * distance);
      station.getLaunchConfig().setDistance(distance);
      double duration = (endSpeed - curentSpeed) / acceleration;
      station.launchTo(direction);
      Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)AdvancedSigns.getInstance(), () -> {
            event.getGroup().getProperties().setSlowingDown(false);
            event.getGroup().getProperties().setSpeedLimit(endSpeed);
          },
          (long)duration);
    }
  }

  public boolean build(SignChangeActionEvent event) {
    return
      SignBuildOptions.create()
      .setName(event.isCartSign() ? "cart enableslowdown" : "train enableslowdown")
      .setDescription("brakes the train to its desired speed after that train changes to no slowdown")
      .handle(event.getPlayer());
  }
}
