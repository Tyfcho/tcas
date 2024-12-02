package me.tyfcho.tcas.integrations.traincarts;

/**
 * Reading if a power channel exists.
 * Reading power-states from TrainCarts power channels and sending them out will be handled within this class.
 */

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class TrainCartsDataReader {

    private static final String FILE_PATH = "./power/groups/";
    private final TrainCartsIntegration trainCartsIntegration;

    /**
     * Constructor for the TrainCartsDataReader.
     * 
     * @param trainCartsIntegration The TrainCarts integration instance
     */
    public TrainCartsDataReader(TrainCartsIntegration trainCartsIntegration) {
        this.trainCartsIntegration = trainCartsIntegration;
    }

    /**
     * Checks if a power channel exists in TrainCarts.
     * 
     * @param channelName The name of the targeted channel
     * @return True if the channel exists within TrainCarts, otherwise it will return false
     */
    public boolean doesChannelExistInTrainCarts(String channelName) {
        return trainCartsIntegration.channelExists(channelName);
    }

    /**
     * Checks if a power channel exists in the specified group.
     * 
     * @param groupName The name of the group
     * @param channel The name of the channel
     * @return True if the channel exists in the group, otherwise false
     * @throws IOException If the group file cannot be accessed
     */
    public boolean doesChannelExistInGroup(String groupName, String channel) throws IOException {
        File groupFile = new File(FILE_PATH + groupName + ".yaml");
        if (!groupFile.exists()) {
            return false;
        }

        Yaml yaml = new Yaml();
        Map<String, Object> groupData;

        try (FileReader reader = new FileReader(groupFile)) {
            groupData = yaml.load(reader);
        }

        @SuppressWarnings("unchecked")
        List<String> channels = (List<String>) groupData.get("channels");

        return channels !=null && channels.cintains(channel);

    }

    /**
     * Reads the power state of a TrainCarts channel.
     * 
     * @param channelName The name of the channel
     * @return The power state of the channel (false/true)
     */
    public String getPowerState(String channelName) {
        return trainCartsIntegration.getChannelPowerState(channelName);
    }
}