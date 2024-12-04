package me.tyfcho.tcas.integrations.traincarts;

/**
 * This class will facilitate the reading of power groups to the TrainCartsDataReader
 * This class will both create a yaml file per power group, as well as handle the insertion of channels to the group
 */

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.plugin.java.JavaPlugin;

import me.tyfcho.tcas.ThemeParkControlAttractionSystems;

public class TrainCartsPowerGroup {
    
    /**
     * Make the file path readable to the whole code.
     */
    private static final String FILE_PATH = "./power/groups/";

    public TrainCartsPowerGroup(JavaPlugin plugin) {
        // Ensure the directory exists
        File directory = new File(FILE_PATH);
        if (!directory.exists()) {
            directory.mkdirs();
        } else {
            plugin.getLogger().info("Power group directory does not exist, will create a new one.");
        }
    }

    /**
     * Creates a YAML file for the given group name
     *
     * @param groupName the name of the group
     * @return
     * @throws IOException if the file cannot be created
     */
    public String createGroup(String groupName) throws IOException {
        File groupFile = new File(FILE_PATH + groupName + ".yaml");
        if (!groupFile.exists()) {
            Map<String, Object> groupData = new HashMap<>();
            groupData.put("group_name", groupName);
            groupData.put("channels", new ArrayList<String>());

            try (FileWriter writer = new FileWriter(groupFile)) {
                Yaml yaml = new Yaml(getDumperOptions());
                yaml.dump(groupData, writer);
                return "Group file created: " + groupFile.getAbsolutePath();
            }
                return "Group file already exists for: " + groupName;
            }
        }

        /**
         * Adds a channel to the specified group
         *
         * @param groupName the name of the group
         * @param channel the channel to add
         * @throws IOException if the group file cannot be accessed or updated
         */
        public void addChannelToGroup(String groupName, String channel) throws IOException {
            File groupFile = new File(FILE_PATH + groupName + ".yaml");
            if (!groupFile.exists()) {
                plugin.get
            } else {

            Yaml yaml = new Yaml();
            Map<String, Object> groupData = yaml.load(new FileReader(groupFile));

            @SuppressWarnings("unchecked")
            List<String> channels = (List<String> groupData.get("channels"));
            if (!channels.contains(channel)) {
                channels.add(channel);
                groupData.put("channels", channels);

                try (FileWriter writer = new FileWriter(groupFile)) {
                    Yaml dumper = new Yaml(getDumperOptions());
                    dumper.dump(groupData, writer);
                    return String("Channel was added to group " + groupName);
                }
                else {
                    return String("Channel already exists in the group.");
                }
            }

            /**
            * Configures YAML dump option for readability
            * @return configured DumperOptions
            */
                /**
                 * Configures YAML dump option for readability
                 * @return configured DumperOptions
                 */
            private DumperOptions getDumperOptions() {
                DumperOptions options = new DumperOptions();
                options.setPrettyFlow(true);
                options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
                return options;
            }
        }
    }
}