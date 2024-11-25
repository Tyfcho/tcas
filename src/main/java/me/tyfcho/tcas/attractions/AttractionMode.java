package me.tyfcho.tcas.attractions;

/**
 * Represents the different modes that an attraction can operate in.
 */
public enum AttractionMode {

    /**
     * Operator mode allows a player to take full manual control of the attraction.
     * Note: this will still need the control panel, so it will still limit players in execution of commands.
     */
    OPERATOR("Operator Mode", "tcas.mode.operator"),

    /**
     * Auto mode allows the attraction to run automatically with predefined behaviors.
     */
    AUTO("Auto Mode", "tcas.mode.auto"),

    /**
     * Manual mode allows players to control specific parts of the attraction, like dispatches.
     * Note: this will completely change the way of controlling the attraction.
     * The following stuff will be set to manual:
     * - Block sections
     * - Switch tracks
     * - Shunting
     * - Sections within the dispatch sequence (e.g. the floor falling away from a dive coaster station)
     * - External actuators, such as gates and show doors (if applicable, e.g. the doors between entrance to the building and pre-show)
     * 
     * Rollercoasters don't really have their own actuator groups, but (most) flatrides do!
     * This is why the flatrides will have seperate "manual" logic compared to the rollercoasters:
     * - Seperate group control (e.g. arm 1 through 4 can be actuated to a specific angle by hand)
     * - Seperate cart control (e.g. release the brakes of carriage 3 while carriage 1 to 4 (excluding 3), is still locked)
     * 
     */
    MANUAL("Manual Mode", "tcas.mode.manual"),

    /**
     * Maintenance mode allows for track maintenance and transfer management.
     * 
     * The difference between maintenance and manual mode is that control over the attraction in maintenance mode is not limited to 1 person.
     * - Control panel is disabled in this mode
     * - Power is disabled in this mode (will not be read as pre-requisite to executing parts of programming)
     * - Removes the limitation of the amount of operators to more than 1
     * - Disables entrance + exit gates and opens all the doors internally
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
