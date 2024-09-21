package me.tyfcho.tcas.panel.configuration;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ConfigManager {

    private final Plugin plugin;
    private File configFile;
    private FileConfiguration config;
    private File dataFolder; // Add dataFolder variable
    private File languageFile;

    public ConfigManager(Plugin plugin) {
        this.plugin = plugin;
        this.dataFolder = plugin.getDataFolder(); // Initialize dataFolder
    }

    public void setupConfig() {
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }

        configFile = new File(dataFolder, "config.yml");

        if (!configFile.exists()) {
            // Copy the default config.yml from resources to the plugin's directory
            try (InputStream inputStream = plugin.getResource("config.yml")) {
                if (inputStream == null) {
                    plugin.getLogger().warning("Default config.yml not found in resources.");
                    return;
                }

                Files.copy(inputStream, configFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        config = YamlConfiguration.loadConfiguration(configFile);
    }
}
