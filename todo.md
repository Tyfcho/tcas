# TCAS Plugin Development TODO

This document outlines the tasks and goals for implementing the TCAS plugin, breaking down each module and class required to complete the plugin.

## 1. **Main Plugin Class (`ThemeParkControlAttractionSystems.java`)**
- [x] **Initialize Plugin**
    - [x] Set up the `onEnable()` and `onDisable()` methods.
    - [x] Register all commands (like `/tcas`).
    - [ ] Register event listeners (e.g., GUI interaction listeners).
    - [ ] Ensure config files are created and loaded properly during initialization.
- [ ] **Plugin Configuration**
    - [ ] Implement configuration handling for custom plugin settings in `config.yml` (if needed).

---

## 2. **Commands Module (`commands/`)**

### **`TCASCommand.java`**
- [x] **Create Main Command Handler**
    - [x] Set up `/tcas` as the main command.
    - [x] Add logic to route subcommands (like `create`, `dispatch`, `mode`).
    - [x] Use `AttractionManager` from `ThemeParkControlAttractionSystems` for managing player-attraction relationships.
- [ ] **Subcommands**
    - [ ] **`/tcas create`**: Open the Factory Creation GUI.
    - [ ] **`/tcas dispatch`**: Add logic to start an attraction (depending on the type: coaster, flatride).
    - [ ] **`/tcas mode <operator/auto/manual/maintenance>`**: Add logic to switch between different operation modes for attractions.
    - [ ] **`/tcas power`**: Add logic to claim control of the attraction in operator mode.
    - [ ] **`/tcas showtrigger`**: Implement logic to display the current status of an attraction's triggers.
    - [ ] **`/tcas gates`**: Add logic to control the gates of the attraction (open/close).
    - [ ] **`/tcas restraints`**: Implement restraint control logic for coasters or other attractions.

---

## 3. **GUI Module (`gui/`)**

### **`FactoryCreationGUI.java`**
- [ ] **Design the GUI**
    - [ ] Create an inventory-based GUI for users to create and configure attractions.
    - [ ] Add clickable icons for choosing attraction types (e.g., Coaster, Flatride).
    - [ ] Add input options (via item clicks) for configuring specific parameters, like:
        - [ ] Coaster brake sectors (0–9).
        - [ ] Flatride sequence completion triggers.
- [ ] **Handle GUI Input**
    - [ ] Add logic to handle user input (detect which item was clicked).
    - [ ] Process user selections and save the configuration temporarily until confirmed.

---

## 4. **Listeners Module (`listeners/`)**

### **`GUIEventListener.java`**
- [ ] **Listen for GUI Click Events**
    - [ ] Add logic to handle `InventoryClickEvent` when users interact with the Factory Creation GUI.
    - [ ] Ensure that invalid interactions are blocked (e.g., removing items from the GUI).
    - [ ] Call the appropriate methods to update attraction settings based on user input.

---

## 5. **Attractions Module (`attractions/`)**

### **`Attraction.java`**
- [x] **Base Attraction Class**
    - [x] Define common properties and methods shared by all attraction types (e.g., name, type, operating mode).
    - [x] Include methods for controlling the attraction, such as starting, stopping, and setting the mode.

### **`Coaster.java`**
- [ ] **Coaster-Specific Logic**
    - [ ] Add properties specific to coasters (e.g., number of brake sectors, marshalling).
    - [ ] Implement coaster control logic (e.g., starting and managing sections).

### **`Flatride.java`**
- [ ] **Flatride-Specific Logic**
    - [ ] Define properties for flatrides (e.g., sequence completion).
    - [ ] Implement logic to trigger flatrides and manage their sequences.

---

## 6. **Storage Module (`storage/`)**

### **`AttractionStorage.java`**
- [ ] **Handle Attraction Data Storage**
    - [ ] Implement methods for saving attraction configurations to a `config.yml` file or another storage format.
    - [ ] Load attraction configurations when the plugin starts or reloads.
    - [ ] Provide a method to update attractions (for instance, after being edited via the GUI).

---

## 7. **Additional Features and Future Expansion**

### **TrainCarts Keyboard Control**
- [ ] **Integrate with TrainCarts API**
    - [ ] Implement keyboard-controlled movement of TrainCarts via plugin.
    - [ ] Allow users to configure velocity offsets and other movement parameters (acceleration, deceleration, etc.).
    - [ ] Add command to manage and map keyboard inputs to TrainCarts movement (e.g., `/tcas keycontrol`).

### **Operating Modes**
- [ ] **Operator Mode**: Restrict control to the player who claimed the attraction.
- [ ] **Auto Mode**: Automate attraction operation with a timer.
- [ ] **Manual Mode**: Allow manual control of all attraction parameters.
- [ ] **Maintenance Mode**: Provide commands for attraction maintenance (e.g., track management, train transfer).

### **Attraction Registration and Warping**
- [ ] Implement functionality for registering and warping to specific attractions via a command (e.g., `/tcas warp <attraction>`).

### **Expand Attraction Types**
- [ ] Implement additional attraction types beyond Coasters and Flatrides as needed.

---

## 8. **Testing and Documentation**

### **Testing**
- [ ] Test all features thoroughly in a local development environment (e.g., with a Spigot or Paper Minecraft server).
- [ ] Ensure compatibility with the TrainCarts plugin and any other plugins involved.
- [ ] Test the Factory Creation GUI to ensure proper input handling.
- [ ] Verify data storage (config saving and loading).
- [ ] Check for bugs or errors related to event handling, especially with the GUI.

### **Documentation**
- [ ] Create user documentation (how to use the `/tcas` commands, how to create attractions).
- [ ] Provide detailed explanations of operating modes and attraction configuration options.
- [ ] Comment the code for future developers who may want to expand the plugin.

---
## Overall Timeline
1. **Phase 1**: Core Setup
    - Set up the main plugin structure, basic commands, and event listeners.
2. **Phase 2**: GUI Development
    - Develop the GUI for creating attractions, handle user input, and implement configuration saving.
3. **Phase 3**: Attraction Management
    - Implement classes for managing different attraction types (Coaster, Flatride) and control logic.
4. **Phase 4**: Storage and Persistence
    - Implement data storage for saving and loading attraction configurations.
5. **Phase 5**: TrainCarts and Additional Features
    - Add TrainCarts keyboard control, operating modes, and other features as listed in the future expansion section.
6. **Phase 6**: Testing and Bug Fixing
    - Thoroughly test all functionality in a live environment to ensure stability and compatibility.
---