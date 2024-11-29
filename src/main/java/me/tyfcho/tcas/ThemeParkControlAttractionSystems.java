package me.tyfcho.tcas;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * This package contains the core components of the TCAS (Theme Park Control Attraction Systems) plugin. The plugin is designed to enhance attraction management in Minecraft through TrainCarts and other ride control systems.
 */
public final class ThemeParkControlAttractionSystems extends JavaPlugin {

  /**
   * Call all methods within the plugin that need initialization
   */
  @Override
  public void onEnable() {
    getLogger().info("TCAS Plugin has been enabled.");
  }

  @Override
  public void onDisable() {
    getLogger().info("TCAS Plugin has been disabled.");
  }
}
