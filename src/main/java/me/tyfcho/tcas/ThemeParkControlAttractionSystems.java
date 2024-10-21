package me.tyfcho.tcas;

import cloud.commandframework.bukkit.BukkitCommandManager;
import cloud.commandframework.execution.CommandExecutionCoordinator;
import cloud.commandframework.meta.SimpleCommandMeta;
import me.tyfcho.tcas.commands.TCASCommand;
import me.tyfcho.tcas.attractions.AttractionManager;
import me.tyfcho.tcas.gui.FactoryCreationGUI;
import me.tyfcho.tcas.listeners.FactoryClickListener;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * This package contains the core components of the TCAS (Theme Park Control Attraction Systems) plugin. The plugin is designed to enhance attraction management in Minecraft through TrainCarts and other ride control systems.
 * Package Overview:
 * - commands: This package contains all the command classes. Each command class is responsible for handling specific subcommands, such as creating and operating attractions. The main command is `/tcas`. Subcommands include `/tcas create`, `/tcas dispatch`, etc.
 * - gui: The gui package handles the creation and management of graphical user interfaces (GUIs) for the plugin. This includes opening custom inventory screens for players to interact with, such as the Factory Creation GUI that allows operators to create new attractions. Event listeners are used to process user interactions with these GUIs.
 * - listeners: This package contains classes that manage event listeners. These listeners capture player actions, such as interacting with the GUI, clicking on inventory slots, or performing other actions within the game that trigger specific plugin behaviors.
 * - attractions: The attractions package contains the core classes for managing different types of attractions, such as coasters and flatrides. Each attraction type has its own class that defines its specific behaviors, like coaster sector configuration or flatride sequence management. All attractions share common characteristics managed by a parent class (e.g., Attraction).
 * - storage: This package handles the storage and persistence of data, such as saving and loading attraction configurations. Attraction data is stored in YAML files or other formats, allowing the plugin to retain attraction details across server restarts and reloads.
 * - ThemeParkControlAttractionSystems.java: The main plugin class that serves as the entry point for TCAS. It is responsible for registering commands and event listeners, as well as managing the overall lifecycle of the plugin (initialization, shutdown).
 */
public final class ThemeParkControlAttractionSystems extends JavaPlugin {

  private BukkitCommandManager<Player> commandManager;
  private AttractionManager attractionManager;
  private FactoryCreationGUI factoryCreationGUI;

  @Override
  public void onEnable() {
    // Initialize the attraction manager
    attractionManager = new AttractionManager();

    // Initialize the attraction creation factory
    factoryCreationGUI = new FactoryCreationGUI();

    // Register the event listener
    getServer().getPluginManager().registerEvents(new FactoryClickListener(factoryCreationGUI), this);

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
