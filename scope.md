# Plugin Scope

## Features and Objectives

### 1. Factory Creation via GUI

- **Command:** `/tcas create <attraction>`
- **Description:** Provides a GUI for creating new attractions. The user will input the attraction type (coaster or flatride) and name. The name must be unique.

### 2. Attraction Types

#### Coaster
- **Description:** Requires the number of sectors for the coaster, which corresponds to the number of brake sections.
- **Input:** Number of sectors (0-9).

#### Flatride
- **Description:** Does not require sectors. Instead, it uses a sequence completion trigger.
- **Trigger Command:** `/tcas sequence done <attraction>`

### 3. Subcommands for `/tcas ridecontrol`

- **Power:** Claims the controlling user (available in operator mode).
- **Dispatch:** Starts the attraction.
- **Gates:** Controls the gates.
- **Restraints:** Manages restraints.
- **Marshall:** Not required for flat rides, only for coasters.
- **Mode:** Changes the operating mode (operator/auto/manual/maintenance).
- **Showtrigger:** Displays the trigger status.

**Note:** All subcommands need to be registered for coasters. Flatrides do not require the marshall command.

### 4. Modes

#### Operator Mode
- **Description:** Only users with the `tcas.ridecontrol.operator` permission can claim and control the attraction. Commands must be executed by the claimed user.

#### Auto Mode
- **Description:** Automatically manages the attraction based on a configurable start time.
- **Config Command:** `/tcas options autostart <attraction> <time>`
- **Operation:** 
  - Open gates when sequence is done or the train is in the station with restraints open.
  - Start timer, then close restraints and dispatch the ride.
  - Once the ride is done, open exit gates, open restraints, and open entrance gate.
  - Repeat the process.

#### Manual Mode
- **Description:** Allows manual control of attractions.
- **Commands for Flatrides:**
  - Register and manage manual controls.
- **Commands for Coasters:**
  - Register and manage block sections and dispatch sections.

#### Maintenance Mode
- **Description:** For server administrators to manage track maintenance and transfers.
- **Commands:**
  - `/tcas maintenancecontrol register <attraction>`
  - `/tcas maintenancecontrol addtransfer <attraction> <section-id>`
  - `/tcas maintenancecontrol removetransfer <attraction> <section-id>`
  - `/tcas maintenancecontrol addtransferposition <attraction> <section-id> <gate>`
  - `/tcas maintenancecontrol removetransferposition <attraction> <section-id> <gate>`
  - `/tcas maintenancecontrol addtransfercommand <attraction> <section-id> <command>`
  - `/tcas maintenancecontrol removetransfercommand <attraction> <section-id> <command>`
  - `/tcas maintenancecontrol transfer <attraction> <section-id> <gate> <load/unload>`

### 5. TrainCarts Integration

- **Description:** Adds support for keyboard-controlled movement of TrainCarts.
- **Features:**
  - Movement controlled via keyboard (A and D buttons).
  - Configurable offset for velocity changes.
  - The configuration file should allow server owners to set movement parameters.
- **API Integration:** Implement using TrainCarts API to add properties to carts, signs, and nodes.

## Future Extensions
- Support to simulate "PLC logic", here's a brief explaination about what it would be about: [Click here](https://en.wikipedia.org/wiki/IEC_61131-3). Most of the things that will be implemented would be in the direction of ladder and sequencial function diagrams. How this would be implemented, hasn't been researched enough to give proper explaination about it.
- Future updates will be noted here.
