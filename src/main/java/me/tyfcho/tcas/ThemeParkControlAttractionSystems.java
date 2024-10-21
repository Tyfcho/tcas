package me.tyfcho.tcas;

import cloud.commandframework.bukkit.BukkitCommandManager;
import cloud.commandframework.execution.CommandExecutionCoordinator;
import cloud.commandframework.meta.SimpleCommandMeta;
import me.tyfcho.tcas.commands.TCASCommand;
import me.tyfcho.tcas.attractions.AttractionManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.function.Function;

public final class ThemeParkControlAttractionSystems extends JavaPlugin {

  private BukkitCommandManager<Player> commandManager;
  private AttractionManager attractionManager;

  @Override
  public void onEnable() {
    // Initialize the attraction manager
    attractionManager = new AttractionManager();

    // Initialize the command manager using Cloud Command Framework
    try {
      this.commandManager = new BukkitCommandManager<Player>(
              this,
              CommandExecutionCoordinator.simpleCoordinator(),
              // Convert CommandSender to Player
              sender -> {
                if (sender instanceof Player) {
                  return (Player) sender;
                }
                throw new IllegalArgumentException("This command can only be executed by players.");
              },
              // Convert Player to CommandSender
              player -> (CommandSender) player
      );
    } catch (Exception e) {
      getLogger().severe("Failed to initialize Cloud Command Framework.");
      e.printStackTrace();
      return;
    }

    // Register commands
    new TCASCommand(commandManager, this).registerCommands();

    getLogger().info("TCAS Plugin has been enabled.");
  }

  @Override
  public void onDisable() {
    getLogger().info("TCAS Plugin has been disabled.");
  }

  // Provide access to the AttractionManager
  public AttractionManager getAttractionManager() {
    return attractionManager;
  }
}
