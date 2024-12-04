package me.tyfcho.tcas;

import me.tyfcho.tcas.integrations.traincarts.TrainCartsAPIWrapper;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * This package contains the core components of the TCAS (Theme Park Control Attraction Systems) plugin. The plugin is designed to enhance attraction management in Minecraft through TrainCarts and other ride control systems.
 */
public final class ThemeParkControlAttractionSystems extends JavaPlugin {

  /**
   * Call all methods within the plugin that need initialization
   */

  private TrainCartsAPIWrapper tcAPI;

  @Override
  public void onEnable() {

    // Initialize wrapper
    this.tcAPI = new TrainCartsAPIWrapper(this);

    getLogger().info("TCAS Plugin has been enabled.");
  }

  @Override
  public void onDisable() {
    getLogger().info("TCAS Plugin has been disabled.");
  }

  /**
   * Get the API Wrapper for other parts of the plugin.
   * 
   * @return The TrainCartsAPIWrapper instance
   */
  public TrainCartsAPIWrapper getAPI() {
    return tcAPI;
  }
}
