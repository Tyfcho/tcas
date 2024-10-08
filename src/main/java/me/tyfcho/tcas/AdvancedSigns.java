package me.tyfcho.tcas;

import com.bergerkiller.bukkit.tc.signactions.SignAction;
import me.tyfcho.tcas.cloud.CommandRegister;
import me.tyfcho.tcas.signs.*;
import me.tyfcho.tcas.signs.MonorailSign;
import org.bukkit.plugin.java.JavaPlugin;

public final class AdvancedSigns extends JavaPlugin {
  private static AdvancedSigns instance;
  public final HoldAndReleaseSign holdAndReleaseSign = new HoldAndReleaseSign();
  public final EnableSlowdownSign slowdownSign = new EnableSlowdownSign();
  public final BrakeAndSlowdownFalseSign brakeAndSlowdownFalseSign = new BrakeAndSlowdownFalseSign();
  public final FreefallSign freefallSign = new FreefallSign();
  public final MonorailSign monorailSign = new MonorailSign();
  public final CatchCarSign catchCarSign = new CatchCarSign();
  public final TripleLaunchSign tLaunch = new TripleLaunchSign();

  public void onEnable() {
    instance = this;
    registerSigns();    // Registers signs to TrainCarts
  }

  public void onDisable() {
    SignAction.unregister((SignAction)this.tLaunch);
    SignAction.unregister((SignAction)this.holdAndReleaseSign);
    SignAction.unregister((SignAction)this.slowdownSign);
    SignAction.unregister((SignAction)this.brakeAndSlowdownFalseSign);
    SignAction.unregister((SignAction)this.freefallSign);
    SignAction.unregister((SignAction)this.monorailSign);
    SignAction.unregister((SignAction)this.catchCarSign);
  }

  public static void setInstance(AdvancedSigns instance) {
    AdvancedSigns.instance = instance;
  }

  public static AdvancedSigns getInstance() {
    return instance;
  }

  private void registerSigns() {
    SignAction.register((SignAction)this.tLaunch);
    SignAction.register((SignAction)this.holdAndReleaseSign);
    SignAction.register((SignAction)this.slowdownSign);
    SignAction.register((SignAction)this.brakeAndSlowdownFalseSign);
    SignAction.register((SignAction)this.freefallSign);
    SignAction.register((SignAction)this.monorailSign);
    SignAction.register((SignAction)this.catchCarSign);
  }

}
