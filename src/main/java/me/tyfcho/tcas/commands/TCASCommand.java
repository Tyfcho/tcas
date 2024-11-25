package me.tyfcho.tcas.commands;

import cloud.commandframework.arguments.standard.StringArgument;
import cloud.commandframework.bukkit.BukkitCommandManager;
import cloud.commandframework.context.CommandContext;
import me.tyfcho.tcas.ThemeParkControlAttractionSystems;
import me.tyfcho.tcas.attractions.Attraction;
import me.tyfcho.tcas.attractions.AttractionMode;
import org.bukkit.entity.Player;

/**
 * Overview of the Commands System.
 * The main command for the plugin will be /tcas, and it will have several subcommands to handle different aspects of attraction management.
 * These subcommands will include:
 * - /tcas create: Opens the GUI for creating a new attraction.
 * - /tcas dispatch: Starts the selected attraction (has prerequisites from gates, restraints, traininstation, if applicable).
 * - /tcas mode  auto/manual/maintenance>: Switches between different operating modes (administrator only for maintenance). 
 * - /tcas power: Claims control over the attraction (for operator mode, together with initializing the attraction).
 * - /tcas showtrigger: Displays the current status of the attraction’s trigger (e.g. animation of a floor with a dive coaster).
 * - /tcas gates: Controls the gates of the attraction (only applicable if configured, open/close).
 * - /tcas restraints: Controls the restraints (only applicable if configured, same logic as gates).
 */
public class TCASCommand {

    private final BukkitCommandManager<Player> commandManager;
    private final ThemeParkControlAttractionSystems plugin;

    public TCASCommand(BukkitCommandManager<Player> commandManager, ThemeParkControlAttractionSystems plugin) {
        this.commandManager = commandManager;
        this.plugin = plugin;
    }

    public void registerCommands() {
        // Register /tcas create command
        commandManager.command(
                commandManager.commandBuilder("tcas")
                        .literal("create")
                        .handler(this::handleCreateCommand)
                        .build()
        );

        // Register /tcas dispatch command
        commandManager.command(
                commandManager.commandBuilder("tcas")
                        .literal("dispatch")
                        .handler(this::handleDispatchCommand)
                        .build()
        );

        // Register /tcas mode command with an argument
        commandManager.command(
                commandManager.commandBuilder("tcas")
                        .literal("mode")
                        .argument(StringArgument.of("mode"))
                        .handler(this::handleModeCommand)
                        .build()
        );
    }

    private void handleCreateCommand(CommandContext<Player> context) {
        Player player = context.getSender();
        player.sendMessage("Opening Factory Creation GUI...");
        // Additional logic here...
    }

    /**
     * The dispatch command can only be ran by the user that has "claimed" the attraction.
     * The dispatch command will only be ran when the following pre-requisites are met:
     * - Attraction is in station / ride sequence has been completed (default value = 0)
     * - Gates are closed (if applicable)
     * - Restraints are closed (if applicable)
     * - Command was called by operating user
     * 
     * Executing the command will be as follows:
     * - /tcas dispatch <attraction>
     * 
     * Optional for administrators: 
     * - force: /tcas dispatch <attraction> -f
     * - debug: /tcas dispatch <attraction> -d
     * 
     * Forcing the dispatch:
     * - Will skip the pre-requisites
     * 
     * Debugging the dispatch:
     * - Will go through every step of the sequence
     * - Will print the print-out to the executor
     */
    private void handleDispatchCommand(CommandContext<Player> context) {
        Player player = context.getSender();
        Attraction attraction = plugin.getAttractionManager().getAttractionForPlayer(player);
        String admin = context.get("admin");

        if (admin != null) {
            //TODO: Add logic to here to check whether player has "tcas.admin" permission node
            /**
             * Forcing the dispatch will run the complete "dispatch sequence".
             * Forcing the dispatch will always tell user what outcome of run sequence is
             * 
             */
        }
        // Add logic here
        player.sendMessage("Dispatching the attraction...");
    }

    private void handleModeCommand(CommandContext<Player> context) {
        Player player = context.getSender();
        String mode = context.get("mode");
        Attraction attraction = plugin.getAttractionManager().getAttractionForPlayer(player);

        if (attraction == null) {
            player.sendMessage("You are not managing an attraction.");
            return;
        }

        switch (mode.toLowerCase()) {
            case "operator":
                player.sendMessage("Switched to Operator Mode.");
                attraction.setMode(AttractionMode.OPERATOR);
                break;
            case "auto":
                player.sendMessage("Switched to Auto Mode.");
                attraction.setMode(AttractionMode.AUTO);
                break;
            case "manual":
                player.sendMessage("Switched to Manual Mode.");
                attraction.setMode(AttractionMode.MANUAL);
                break;
            case "maintenance":
                player.sendMessage("Switched to Maintenance Mode.");
                attraction.setMode(AttractionMode.MAINTENANCE);
                break;
            default:
                player.sendMessage("Unknown mode.");
        }
    }
}
