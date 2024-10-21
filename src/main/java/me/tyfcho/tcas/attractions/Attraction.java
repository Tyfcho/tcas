package me.tyfcho.tcas.attractions;

import org.bukkit.entity.Player;

// Example Attraction class
public class Attraction {
    private Player operator;  // The player who has claimed the attraction
    private AttractionMode currentMode;  // Current operating mode (Operator, Auto, etc.)

    // Check if the attraction is claimed by a player
    public boolean isClaimedBy(Player player) {
        return operator != null && operator.equals(player);
    }

    // Claim the attraction
    public void claim(Player player) {
        this.operator = player;
    }

    // Check if the attraction is unclaimed
    public boolean isUnclaimed() {
        return operator == null;
    }

    // Set the current mode of the attraction
    public void setMode(AttractionMode mode) {
        this.currentMode = mode;
    }

    // Check if the current operator has left the area
    public void releaseClaimIfOperatorLeftArea(Player player) {
        if (operator != null && operator.equals(player)) {
            operator = null;
            setMode(AttractionMode.AUTO);  // Automatically set to Auto mode when no operator is present
        }
    }
}

