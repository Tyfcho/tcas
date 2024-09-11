package me.tyfcho.tcas.managers;

import me.tyfcho.tcas.ridetypes.Attraction;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class AttractionManager {

    private final Map<String, Attraction> attractions = new HashMap<>();

    public boolean attractionExists(String name) {
        return attractions.containsKey(name);
    }

    public void createAttraction(Player player, String name) {
        if (!attractions.containsKey(name)) {
            Attraction attraction = new Attraction(name);
            attractions.put(name, attraction);
            player.sendMessage("Attraction '" + name + "' has been created.");
        } else {
            player.sendMessage("Attraction with the name '" + name + "' already exists.");
        }
    }

    public Attraction getAttraction(String name) {
        return attractions.get(name);
    }

    public boolean registerManualControl(String name) {
        Attraction attraction = getAttraction(name);
        if (attraction != null) {
            // Logic for registering manual control can be expanded here
            return true;
        }
        return false;
    }
}

