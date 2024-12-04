package me.tyfcho.tcas.integrations.traincarts;

/**
 * Sending data from this package to the rest of the plugin will be handled from here.
 */

import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.logging.Logger;

// Importing the integration class, which also holds the datareader class.


public class TrainCartsAPIWrapper {

    private final TrainCartsPowerGroup trainCartsPowerGroup;
    private final Logger logger;

    /**
     * Constructor for the API Wrapper.
     * 
     * @param plugin The main plugin instance (ThemeParkControlAttractionSystems.java)
     */
    public TrainCartsAPIWrapper(JavaPlugin plugin) {
        this.logger = plugin.getLogger();
        this.trainCartsPowerGroup = new TrainCartsPowerGroup(plugin);

    }

    /**
     * Creates a power group YAML file.
     * 
     * @param groupName The name of the group
     * @return A status message
     */
    public String createPowerGroup(String groupName) {
        try {
            return trainCartsPowerGroup.createGroup(groupName);
        } catch (IOException e) {
            logger.severe("Failed to create group: " + groupName + " . Error: " + e.getMessage());
            return "Error: Could not create group.";
        }
    }

    /**
     * Adds a channel to the specified power group.
     * 
     * @param groupName The name of the group
     * @param channel The channel to add
     * @return A status message
     */
    public String addChannelToPowerGroup(String groupName, String channel) {
        try {
            return trainCartsPowerGroup.addChannelToGroup(groupName, channel);
        } catch (IOException e) {
            logger.severe("Failed to add channel " + channel + " to group " + groupName ". Error: " + e.getMessage());
            return "Error. Could not add channel to group.";
        }
    }
}