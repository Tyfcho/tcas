/**
 * Overview of the Commands System.
 * The main command for the plugin will be /tcas, and it will have several subcommands to handle different aspects of attraction management.
 * These subcommands will include:
 * - /tcas create: Opens the GUI for creating a new attraction.
 * - /tcas dispatch: Starts the selected attraction (has prerequisites from gates, restraints, traininstation, if applicable).
 * - /tcas mode  auto/manual/maintenance>: Switches between different operating modes (administrator only for maintenance). 
 * - /tcas power: Claims control over the attraction (for operator mode, together with initializing the attraction).
 * - /tcas showtrigger: Displays the current status of the attraction’s trigger (e.g. animation of a floor with a dive coaster).
 * - /tcas gates: Controls the gates of the attraction (only applicable if configured, open/close).
 * - /tcas restraints: Controls the restraints (only applicable if configured, same logic as gates).
 *
 *
 *
 *
 * @version 1.0
 * @author tyfcho
 */
package me.tyfcho.tcas.commands;