/**
 *Overview of the Commands System
 * The main command for your plugin will be /tcas, and it will have several subcommands to handle different aspects of attraction management. These subcommands will include:
 *
 * /tcas create: Opens the GUI for creating a new attraction.
 * /tcas dispatch: Starts the selected attraction.
 * /tcas mode <operator/auto/manual/maintenance>: Switches between different operating modes.
 * /tcas power: Claims control over the attraction (for operator mode).
 * /tcas showtrigger: Displays the current status of the attraction’s trigger.
 * /tcas gates: Controls the gates of the attraction (open/close).
 * /tcas restraints: Controls the restraints (for coasters or other rides).
 *
 *
 *
 *
 * @version 1.0
 * @author tyfcho
 */
package me.tyfcho.tcas.commands;