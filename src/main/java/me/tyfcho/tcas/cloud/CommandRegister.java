package me.tyfcho.tcas.cloud;

import cloud.commandframework.Command;
import cloud.commandframework.arguments.standard.BooleanArgument;
import cloud.commandframework.context.CommandContext;
import cloud.commandframework.bukkit.BukkitCommandManager;
import cloud.commandframework.arguments.standard.StringArgument;
import cloud.commandframework.arguments.standard.IntegerArgument;
import me.tyfcho.tcas.managers.AttractionManager;
import me.tyfcho.tcas.ridetypes.Attraction;
import me.tyfcho.tcas.ridetypes.Mode;
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
        registerSetTypeCommand();
        registerSetSectorsCommand();
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
            attractionManager.startAttractionCreation(sender, attractionName);
        }
    }

    private void registerRideControlCommands() {
        Command.Builder<Player> rideControlCommand = commandManager.commandBuilder("tcas", "ridecontrol")
                .argument(StringArgument.of("subcommand"))  // Subcommand argument (e.g., power, dispatch)
                .argument(StringArgument.of("attraction"))  // Attraction name argument
                .argument(BooleanArgument.of("state"))      // Boolean state argument (true/false)
                .senderType(Player.class)
                .handler(this::handleRideControl);

        commandManager.command(rideControlCommand.build());
    }

    private void registerModeCommands() {
        Command.Builder<Player> modeCommand = commandManager.commandBuilder("tcas", "ridecontrol", "mode")
                .argument(StringArgument.of("attraction"))
                .argument(StringArgument.of("mode"))
                .senderType(Player.class)
                .handler(this::handleModeCommand);

        commandManager.command(modeCommand.build());
    }

    private void registerManualControlCommands() {
        Command.Builder<Player> manualControlCommand = commandManager.commandBuilder("tcas", "manualcontrol")
                .literal("register")
                .argument(StringArgument.of("attraction"))
                .senderType(Player.class)
                .handler(this::handleManualControlRegister);

        commandManager.command(manualControlCommand.build());
    }

    private void registerSetTypeCommand() {
        Command.Builder<Player> setTypeCommand = commandManager.commandBuilder("tcas", "type")
                .argument(StringArgument.of("type"))
                .senderType(Player.class)
                .handler(this::handleSetAttractionType);

        commandManager.command(setTypeCommand.build());
    }

    private void handleSetAttractionType(CommandContext<Player> context) {
        Player player = context.getSender();
        String type = context.get("type");

        if (type.equalsIgnoreCase("coaster") || type.equalsIgnoreCase("flatrides")) {
            attractionManager.setAttractionType(player, type);
        } else {
            player.sendMessage("Invalid type. Please type either 'coaster' or 'flatrides'.");
        }
    }

    private void registerSetSectorsCommand() {
        Command.Builder<Player> setSectorsCommand = commandManager.commandBuilder("tcas", "sectors")
                .argument(IntegerArgument.of("sectors"))
                .senderType(Player.class)
                .handler(this::handleSetSectors);

        commandManager.command(setSectorsCommand.build());
    }

    private void handleSetSectors(CommandContext<Player> context) {
        Player player = context.getSender();
        int sectors = context.get("sectors");

        if (sectors >= 0 && sectors <= 9) {
            attractionManager.setSectors(player, sectors);
        } else {
            player.sendMessage("Invalid number of sectors. Please choose between 0 and 9.");
        }
    }

    private void handlePowerSubcommand(Player player, Attraction attraction, boolean state) {
        attraction.setPower(state);
        player.sendMessage("Power for " + attraction.getName() + " set to " + state);
    }

    private void handleDispatchSubcommand(Player player, Attraction attraction, boolean state) {
        attraction.setDispatch(state);
        player.sendMessage("Dispatch for " + attraction.getName() + " set to " + state);
    }

    private void handleGatesSubcommand(Player player, Attraction attraction, boolean state) {
        attraction.setGates(state);
        player.sendMessage("Gates for " + attraction.getName() + " set to " + state);
    }

    private void handleRestraintsSubcommand(Player player, Attraction attraction, boolean state) {
        attraction.setRestraints(state);
        player.sendMessage("Restraints for " + attraction.getName() + " set to " + state);
    }

    private void handleMarshallSubcommand(Player player, Attraction attraction, boolean state) {
        attraction.setMarshall(state);
        player.sendMessage("Marshall for " + attraction.getName() + " set to " + state);
    }


    private void handleRideControl(CommandContext<Player> context) {
        Player player = context.getSender();
        String subcommand = context.get("subcommand");
        String attractionName = context.get("attraction");
        boolean state = context.get("state");

        // Get the attraction by name
        if (!attractionManager.attractionExists(attractionName)) {
            player.sendMessage("Attraction " + attractionName + " not found.");
            return;
        }
        Attraction attraction = attractionManager.getAttraction(attractionName);

        // Handle the subcommands
        switch (subcommand.toLowerCase()) {
            case "power":
                handlePowerSubcommand(player, attraction, state);
                break;
            case "dispatch":
                handleDispatchSubcommand(player, attraction, state);
                break;
            case "gates":
                handleGatesSubcommand(player, attraction, state);
                break;
            case "restraints":
                handleRestraintsSubcommand(player, attraction, state);
                break;
            case "marshall":
                handleMarshallSubcommand(player, attraction, state);
                break;
            default:
                player.sendMessage("Unknown subcommand: " + subcommand);
        }
    }

    private void handleModeCommand(CommandContext<Player> context) {
        Player player = context.getSender();
        String attractionName = context.get("attraction");
        String modeString = context.get("mode").toString().toUpperCase();  // Ensure case-insensitive match

        // Get the attraction
        if (!attractionManager.attractionExists(attractionName)) {
            player.sendMessage("Attraction " + attractionName + " not found.");
            return;
        }
        Attraction attraction = attractionManager.getAttraction(attractionName);

        // Set the mode
        try {
            Mode mode = Mode.valueOf(modeString);  // Convert string to enum
            attraction.setMode(mode);              // Set the mode
            player.sendMessage("Set " + attraction.getName() + " to " + mode + " mode.");
        } catch (IllegalArgumentException e) {
            player.sendMessage("Invalid mode. Choose between OPERATOR, AUTO, MANUAL, or MAINTENANCE.");
        }
    }

    private void handleManualControlRegister(CommandContext<Player> context) {
        Player player = context.getSender();
        String attractionName = context.get("attraction");

        // Register manual control
        if (attractionManager.registerManualControl(attractionName)) {
            player.sendMessage("Manual control registered for " + attractionName);
        } else {
            player.sendMessage("Attraction " + attractionName + " not found.");
        }
    }

    // Rest of the ride control methods: handlePowerSubcommand, handleDispatchSubcommand, etc.
}
