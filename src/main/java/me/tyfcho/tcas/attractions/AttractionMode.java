package me.tyfcho.tcas.attractions;

/**
 * Represents the different modes that an attraction can operate in.
 */
public enum AttractionMode {

    /**
     * Operator mode allows a player to take full manual control of the attraction.
     */
    OPERATOR("Operator Mode", "tcas.mode.operator"),

    /**
     * Auto mode allows the attraction to run automatically with predefined behaviors.
     */
    AUTO("Auto Mode", "tcas.mode.auto"),

    /**
     * Manual mode allows players to control specific parts of the attraction, like dispatches.
     */
    MANUAL("Manual Mode", "tcas.mode.manual"),

    /**
     * Maintenance mode allows for track maintenance and transfer management.
     */
    MAINTENANCE("Maintenance Mode", "tcas.mode.maintenance");

    private final String displayName;
    private final String requiredPermission;

    /**
     * Constructor for AttractionMode enum.
     *
     * @param displayName        The name displayed for the mode.
     * @param requiredPermission The permission required to use this mode.
     */
    AttractionMode(String displayName, String requiredPermission) {
        this.displayName = displayName;
        this.requiredPermission = requiredPermission;
    }

    /**
     * Gets the display name of the mode.
     *
     * @return The display name of the mode.
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Gets the permission required to switch to this mode.
     *
     * @return The permission string for this mode.
     */
    public String getRequiredPermission() {
        return requiredPermission;
    }
}
