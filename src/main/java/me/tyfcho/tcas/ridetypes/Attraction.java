package me.tyfcho.tcas.ridetypes;

import org.bukkit.entity.Player;

public class Attraction {

    private final String name;
    private boolean power;
    private boolean dispatch;
    private boolean gates;
    private boolean restraints;
    private boolean marshall;
    private Mode mode;
    private int sectors;
    private Player claimedBy;  // Player who has claimed the attraction in OPERATOR mode
    private boolean isAutoRunning;  // Track if AUTO mode is running

    public Attraction(String name) {
        this.name = name;
        this.power = false;
        this.dispatch = false;
        this.gates = false;
        this.restraints = false;
        this.marshall = false;
        this.mode = Mode.MANUAL;  // Default mode
        this.sectors = 0;
        this.claimedBy = null;
        this.isAutoRunning = false;
    }

    // Getters and setters for attraction properties

    public String getName() {
        return name;
    }

    public boolean isPowerOn() {
        return power;
    }

    public void setPower(boolean power) {
        this.power = power;
    }

    public boolean isDispatchOn() {
        return dispatch;
    }

    public void setDispatch(boolean dispatch) {
        this.dispatch = dispatch;
    }

    public boolean areGatesOpen() {
        return gates;
    }

    public void setGates(boolean gates) {
        this.gates = gates;
    }

    public boolean areRestraintsEngaged() {
        return restraints;
    }

    public void setRestraints(boolean restraints) {
        this.restraints = restraints;
    }

    public boolean isMarshallOn() {
        return marshall;
    }

    public void setMarshall(boolean marshall) {
        this.marshall = marshall;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
        // Reset claimedBy when leaving OPERATOR mode
        if (mode != Mode.OPERATOR) {
            claimedBy = null;
        }
    }

    public int getSectors() {
        return sectors;
    }

    public void setSectors(int sectors) {
        this.sectors = sectors;
    }

    // ClaimedBy for OPERATOR mode
    public Player getClaimedBy() {
        return claimedBy;
    }

    public void setClaimedBy(Player claimedBy) {
        this.claimedBy = claimedBy;
    }

    // Auto mode running
    public boolean isAutoRunning() {
        return isAutoRunning;
    }

    public void setAutoRunning(boolean isAutoRunning) {
        this.isAutoRunning = isAutoRunning;
    }
}
