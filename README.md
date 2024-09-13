# TCAS Plugin

## Purpose

The TCAS Plugin is designed to enhance your attraction management systems by offering new and exciting ways to control and automate rides. The primary goal of this plugin is to provide a more efficient and customizable experience for managing attractions, while also helping me gain more experience in Java development.

TCAS won't be limited by just having TrainCarts support, but also support for any and all plugins that'd be noted within the scope. 

Traincarts will have the following features added through this plugin: control panels, attraction registration for warping and such, easier operation setup, controlling animations through keyboard inputs

The rest of the "Work in Progress" and/or "Future addons" will be documented in (plugins.md)[https://github.com/Tyfcho/tcas/blob/main/plugins.md]


## Features

### 1. Factory Creation via GUI
- **Description:** Easily create new attractions using a user-friendly GUI.

### 2. Attraction Types
- **Coaster:** Allows for configuring the number of sectors (0-9) corresponding to brake sections.
- **Flatride:** Utilizes a sequence completion trigger with the command `/tcas sequence done <attraction>`.

### 3. Ride Control Subcommands
- **Power:** Claims control over the attraction (available in operator mode).
- **Dispatch:** Starts the attraction.
- **Gates:** Manages the gates.
- **Restraints:** Controls the restraints.
- **Marshall:** Specific to coasters for managing train positions.
- **Mode:** Switches between operator, auto, manual, and maintenance modes.
- **Showtrigger:** Displays the current status of the trigger.

### 4. Operating Modes
- **Operator Mode:** Restricts commands to the user who claimed the attraction.
- **Auto Mode:** Automates attraction operations with a configurable start time.
- **Manual Mode:** Allows for manual control and configuration of attractions.
- **Maintenance Mode:** Provides commands for track maintenance and transfer management.

### 5. TrainCarts Keyboard Control
- **Description:** Adds support for keyboard-controlled movement of TrainCarts with configurable velocity offsets. Enhance your train carts with precise movement control using the TrainCarts API.
- **Implementation:** To achieve the feature mentioned above, administrators would have to tell the plugin what animation and what train they'd have to manage, acceleration, min/max velocity and deceleration would be things that can be implemented within the equation.

## Contributing

We welcome contributions from the community to help make this plugin more complete and polished. Whether you have suggestions, bug fixes, or new features, your input is highly valued. To contribute:

1. Fork the repository and create a new branch.
2. Implement your changes and test thoroughly.
3. Submit a pull request with a clear description of your changes.

Your expertise and feedback are crucial in making TCAS a better tool for everyone. Thank you for your support and contributions!
