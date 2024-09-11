package me.tyfcho.tcas.signs;

import com.bergerkiller.bukkit.tc.Station;
import com.bergerkiller.bukkit.tc.events.SignActionEvent;
import com.bergerkiller.bukkit.tc.events.SignChangeActionEvent;
import com.bergerkiller.bukkit.tc.signactions.SignAction;
import com.bergerkiller.bukkit.tc.signactions.SignActionType;
import com.bergerkiller.bukkit.tc.utils.SignBuildOptions;
import java.util.Locale;
import org.bukkit.block.BlockFace;

public class EnableSlowdownSign extends SignAction {
  public boolean match(SignActionEvent event) {
    return event.isType(new String[] { "enableslowdown" });
  }

  public void execute(SignActionEvent event) {
    if (event.isAction(new SignActionType[] { SignActionType.GROUP_ENTER })) {
      Station.StationConfig config = new Station.StationConfig();
      config.setLaunchSpeed(event.getMember().getRealSpeedLimited());
      Station station = new Station(event, config);
      event.getGroup().getProperties().setSlowingDown(true);
      event.getGroup().getProperties().setSpeedLimit(Double.parseDouble(event.getLine(2)));
      event.getGroup().getActions().launchReset();
      station.getLaunchConfig().setDistance(1.0D);
      station.launchTo(BlockFace.valueOf(event.getLine(3).toUpperCase(Locale.ROOT)));
    }
  }

  public boolean build(SignChangeActionEvent event) {
    return
      SignBuildOptions.create()
      .setName(event.isCartSign() ? "cart enableslowdown" : "train enableslowdown")
      .setDescription("leaves the train free to handle by gravity and changes its maxspeed")
      .handle(event.getPlayer());
  }
}