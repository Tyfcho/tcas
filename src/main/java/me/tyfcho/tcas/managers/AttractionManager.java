package me.tyfcho.tcas.managers;

import me.tyfcho.tcas.ridetypes.Attraction;
import me.tyfcho.tcas.ridetypes.AttractionCreationState;
import me.tyfcho.tcas.ridetypes.Mode;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AttractionManager {

    private final Map<String, Attraction> attractions = new HashMap<>();
    private final Map<UUID, AttractionCreationState> creationStateMap = new HashMap<>();

    public boolean attractionExists(String name) {
        return attractions.containsKey(name);
    }

    public void startAttractionCreation(Player player, String name) {
        UUID playerId = player.getUniqueId();
        creationStateMap.put(playerId, new AttractionCreationState(name));
        player.sendMessage("Choose the type of attraction (coaster or flatride). Type '/tcas type <coaster|flatrides>' to continue.");
    }

    public void setAttractionType(Player player, String type) {
        UUID playerId = player.getUniqueId();
        AttractionCreationState state = creationStateMap.get(playerId);
        if (state != null) {
            state.setType(type);
            if (type.equalsIgnoreCase("coaster")) {
                player.sendMessage("How many sectors? Type '/tcas sectors <0-9>' to continue.");
            } else {
                completeAttractionCreation(player);
            }
        }
    }

    public void setSectors(Player player, int sectors) {
        UUID playerId = player.getUniqueId();
        AttractionCreationState state = creationStateMap.get(playerId);
        if (state != null) {
            state.setSectors(sectors);
            completeAttractionCreation(player);
        }
    }

    public void completeAttractionCreation(Player player) {
        UUID playerId = player.getUniqueId();
        AttractionCreationState state = creationStateMap.get(playerId);
        if (state != null) {
            String attractionName = state.getAttractionName();
            String type = state.getType();
            int sectors = state.getSectors();

            Attraction attraction = new Attraction(attractionName);
            if (type.equalsIgnoreCase("coaster")) {
                attraction.setSectors(sectors);  // Example of storing sectors for coaster
            }

            attractions.put(attractionName, attraction);

            player.sendMessage("Attraction " + attractionName + " created! Type: " + type + (type.equals("coaster") ? ", Sectors: " + sectors : ""));
            creationStateMap.remove(playerId);  // Remove player from creation state map
        }
    }

    public Attraction getAttraction(String name) {
        return attractions.get(name);
    }

    public boolean registerManualControl(String name) {
        Attraction attraction = getAttraction(name);
        if (attraction != null) {
            // Placeholder: Set manual control enabled state (if applicable)
            attraction.setMode(Mode.MANUAL);  // Set to manual mode for this example
            return true;
        }
        return false;
    }
}

