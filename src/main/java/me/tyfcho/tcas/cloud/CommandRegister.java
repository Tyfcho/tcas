package me.tyfcho.tcas.cloud;

import cloud.commandframework.Command;
import cloud.commandframework.arguments.standard.BooleanArgument;
import cloud.commandframework.context.CommandContext;
import cloud.commandframework.bukkit.BukkitCommandManager;
import cloud.commandframework.arguments.standard.StringArgument;
import me.tyfcho.tcas.managers.AttractionManager;
import me.tyfcho.tcas.ridetypes.Attraction;
import me.tyfcho.tcas.ridetypes.Mode; // Make sure the Mode enum is imported
import org.bukkit.entity.Player;

public class CommandRegister {

    private final BukkitCommandManager<Player> commandManager;
    private final AttractionManager attractionManager;

    public CommandRegister(BukkitCommandManager<Player> commandManager, AttractionManager attractionManager) {
        this.commandManager = commandManager;
        this.attractionManager = attractionManager;
    }

    public void registerCommands() {
        registerCreateCommand();
        registerRideControlCommands();
        registerModeCommands();
        registerManualControlCommands();
    }

    private void registerCreateCommand() {
        Command.Builder<Player> createCommand = commandManager.commandBuilder("tcas", "create")
                .argument(StringArgument.of("attraction"))
                .senderType(Player.class)
                .handler(this::handleCreateAttraction);

        commandManager.command(createCommand.build());
    }

    private void handleCreateAttraction(CommandContext<Player> context) {
        Player sender = context.getSender();
        String attractionName = context.get("attraction");

        if (attractionManager.attractionExists(attractionName)) {
            sender.sendMessage("Attraction with this name already exists.");
        } else {
            attractionManager.createAttraction(sender, attractionName);
            sender.sendMessage("Attraction " + attractionName + " created!");
        }
    }

    private void registerRideControlCommands() {
        Command.Builder<Player> rideControlCommand = commandManager.commandBuilder("tcas", "ridecontrol")
                .argument(StringArgument.of("subcommand"))
                .argument(StringArgument.of("attraction"))
                .argument(BooleanArgument.of("state"))
                .senderType(Player.class)
                .handler(this::handleRideControl);

        commandManager.command(rideControlCommand.build());
    }

    private void handleRideControl(CommandContext<Player> context) {
        Player player = context.getSender();
        String subcommand = context.get("subcommand");
        String attractionName = context.get("attraction");
        boolean state = context.get("state");

        switch (subcommand.toLowerCase()) {
            case "power":
                handlePowerSubcommand(player, attractionName, state);
                break;
            case "dispatch":
                handleDispatchSubcommand(player, attractionName, state);
                break;
            case "gates":
                handleGatesSubcommand(player, attractionName, state);
                break;
            case "restraints":
                handleRestraintsSubcommand(player, attractionName, state);
                break;
            case "marshall":
                handleMarshallSubcommand(player, attractionName, state);
                break;
            default:
                player.sendMessage("Unknown subcommand: " + subcommand);
        }
    }

    private void handlePowerSubcommand(Player player, String attractionName, boolean state) {
        if (!attractionManager.attractionExists(attractionName)) {
            player.sendMessage("Attraction " + attractionName + " not found.");
            return;
        }
        Attraction attraction = attractionManager.getAttraction(attractionName);
        attraction.setPower(state);
        player.sendMessage("Power for " + attractionName + " set to " + state);
    }

    private void handleDispatchSubcommand(Player player, String attractionName, boolean state) {
        if (!attractionManager.attractionExists(attractionName)) {
            player.sendMessage("Attraction " + attractionName + " not found.");
            return;
        }
        Attraction attraction = attractionManager.getAttraction(attractionName);
        attraction.setDispatch(state);
        player.sendMessage("Dispatch for " + attractionName + " set to " + state);
    }

    private void handleGatesSubcommand(Player player, String attractionName, boolean state) {
        if (!attractionManager.attractionExists(attractionName)) {
            player.sendMessage("Attraction " + attractionName + " not found.");
            return;
        }
        Attraction attraction = attractionManager.getAttraction(attractionName);
        attraction.setGates(state);
        player.sendMessage("Gates for " + attractionName + " set to " + state);
    }

    private void handleRestraintsSubcommand(Player player, String attractionName, boolean state) {
        if (!attractionManager.attractionExists(attractionName)) {
            player.sendMessage("Attraction " + attractionName + " not found.");
            return;
        }
        Attraction attraction = attractionManager.getAttraction(attractionName);
        attraction.setRestraints(state);
        player.sendMessage("Restraints for " + attractionName + " set to " + state);
    }

    private void handleMarshallSubcommand(Player player, String attractionName, boolean state) {
        if (!attractionManager.attractionExists(attractionName)) {
            player.sendMessage("Attraction " + attractionName + " not found.");
            return;
        }
        Attraction attraction = attractionManager.getAttraction(attractionName);
        attraction.setMarshall(state);
        player.sendMessage("Marshall for " + attractionName + " set to " + state);
    }

    private void registerModeCommands() {
        Command.Builder<Player> modeCommand = commandManager.commandBuilder("tcas", "ridecontrol", "mode")
                .argument(StringArgument.of("attraction"))
                .argument(StringArgument.of("mode"))
                .senderType(Player.class)
                .handler(this::handleModeCommand);

        commandManager.command(modeCommand.build());
    }

    private void handleModeCommand(CommandContext<Player> context) {
        Player player = context.getSender();
        String attractionName = context.get("attraction");
        String modeString = context.get("mode").toString().toUpperCase();  // Convert to uppercase for enum compatibility

        try {
            Mode mode = Mode.valueOf(modeString);  // Convert string to enum
            setAttractionMode(attractionName, mode, player);
        } catch (IllegalArgumentException e) {
            player.sendMessage("Unknown mode: " + modeString);
        }
    }

    private void setAttractionMode(String attraction, Mode mode, Player player) {
        if (!attractionManager.attractionExists(attraction)) {
            player.sendMessage("Attraction " + attraction + " not found.");
            return;
        }
        Attraction attractionObj = attractionManager.getAttraction(attraction);
        attractionObj.setMode(mode);  // Set mode in the attraction object
        player.sendMessage("Set " + attraction + " to " + mode + " mode.");
    }

    private void registerManualControlCommands() {
        Command.Builder<Player> manualControlCommand = commandManager.commandBuilder("tcas", "manualcontrol")
                .literal("register")
                .argument(StringArgument.of("attraction"))
                .senderType(Player.class)
                .handler(this::handleManualControlRegister);

        commandManager.command(manualControlCommand.build());
    }

    private void handleManualControlRegister(CommandContext<Player> context) {
        Player player = context.getSender();
        String attractionName = context.get("attraction");

        if (!attractionManager.registerManualControl(attractionName)) {
            player.sendMessage("Failed to register manual control for " + attractionName);
        } else {
            player.sendMessage("Manual control registered for " + attractionName);
        }
    }
}
