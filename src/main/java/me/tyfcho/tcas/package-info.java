/**
 * This package contains the core components of the TCAS (Theme Park Control Attraction Systems) plugin.
 * The plugin is designed to enhance attraction management in Minecraft through TrainCarts and other
 * ride control systems.
 * <p>
 * Package Overview:
 * <p>
 * - commands:
 *     This package contains all the command classes. Each command class is responsible for handling
 *     specific subcommands, such as creating and operating attractions. The main command is `/tcas`.
 *     Subcommands include `/tcas create`, `/tcas dispatch`, etc.
 * <p>
 * - gui:
 *     The gui package handles the creation and management of graphical user interfaces (GUIs) for the plugin.
 *     This includes opening custom inventory screens for players to interact with, such as the Factory Creation
 *     GUI that allows operators to create new attractions. Event listeners are used to process user interactions
 *     with these GUIs.
 * <p>
 * - listeners:
 *     This package contains classes that manage event listeners. These listeners capture player actions, such as
 *     interacting with the GUI, clicking on inventory slots, or performing other actions within the game that
 *     trigger specific plugin behaviors.
 * <p>
 * - attractions:
 *     The attractions package contains the core classes for managing different types of attractions, such as
 *     coasters and flatrides. Each attraction type has its own class that defines its specific behaviors, like
 *     coaster sector configuration or flatride sequence management. All attractions share common characteristics
 *     managed by a parent class (e.g., Attraction).
 * <p>
 * - storage:
 *     This package handles the storage and persistence of data, such as saving and loading attraction configurations.
 *     Attraction data is stored in YAML files or other formats, allowing the plugin to retain attraction details
 *     across server restarts and reloads.
 * <p>
 * - ThemeParkControlAttractionSystems.java:
 *     The main plugin class that serves as the entry point for TCAS. It is responsible for registering commands
 *     and event listeners, as well as managing the overall lifecycle of the plugin (initialization, shutdown).
 *
 * @version 1.0
 * @author tyfcho
 */
package me.tyfcho.tcas;
