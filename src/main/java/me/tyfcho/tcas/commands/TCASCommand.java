package me.tyfcho.tcas.commands;

import cloud.commandframework.arguments.standard.StringArgument;
import cloud.commandframework.bukkit.BukkitCommandManager;
import cloud.commandframework.context.CommandContext;
import me.tyfcho.tcas.ThemeParkControlAttractionSystems;
import me.tyfcho.tcas.attractions.Attraction;
import me.tyfcho.tcas.attractions.AttractionMode;
import org.bukkit.entity.Player;

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

    private void handleDispatchCommand(CommandContext<Player> context) {
        Player player = context.getSender();
        player.sendMessage("Dispatching the attraction...");
        // Additional logic here...
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
