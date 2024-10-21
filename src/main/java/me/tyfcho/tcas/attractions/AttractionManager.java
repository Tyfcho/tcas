package me.tyfcho.tcas.attractions;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class AttractionManager {

    // Map to store which player is managing which attraction
    private final Map<Player, Attraction> playerAttractionMap = new HashMap<>();

    // Register an attraction for a player (when they claim it)
    public void assignAttractionToPlayer(Player player, Attraction attraction) {
        playerAttractionMap.put(player, attraction);
    }

    // Remove the player from the map (when they leave or relinquish control)
    public void removeAttractionForPlayer(Player player) {
        playerAttractionMap.remove(player);
    }

    // Get the current attraction the player is managing
    public Attraction getAttractionForPlayer(Player player) {
        return playerAttractionMap.get(player);
    }

    // Check if the player is managing an attraction
    public boolean isManagingAttraction(Player player) {
        return playerAttractionMap.containsKey(player);
    }
}
