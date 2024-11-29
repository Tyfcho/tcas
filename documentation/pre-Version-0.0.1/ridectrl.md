## RideControl Scope

### Overview of the Commands System.
 * The main command for the plugin will be /tcas, and it will have several subcommands to handle different aspects of attraction management.
 * These subcommands will include:
    - /tcas create: Opens the GUI for creating a new attraction.
    - /tcas dispatch: Starts the selected attraction (has prerequisites from gates, restraints, traininstation, if applicable).
    - /tcas mode  auto/manual/maintenance>: Switches between different operating modes (administrator only for maintenance). 
    - /tcas power: Claims control over the attraction (for operator mode, together with initializing the attraction).
    - /tcas showtrigger: Displays the current status of the attraction’s trigger (e.g. animation of a floor with a dive coaster).
    - /tcas gates: Controls the gates of the attraction (only applicable if configured, open/close).
    - /tcas restraints: Controls the restraints (only applicable if configured, same logic as gates).
---
#### Create
To be added

---
#### Dispatch
The dispatch command can only be ran by the user that has "claimed" the attraction.

The dispatch command will only be ran when the following pre-requisites are met:
- Attraction is in station / ride sequence has been completed (default value = 0)
- Gates are closed (if applicable)
- Restraints are closed (if applicable)
- Command was called by operating user

Executing the command will be as follows:
- `/tcas dispatch <attraction> <optional>`

Optional for administrators: 
- force: `/tcas dispatch <attraction> -f` or `/tcas dispatch <attraction> -force`
- debug: `/tcas dispatch <attraction> -d` or `/tcas dispatch <attraction> -debug`

Forcing the dispatch:
- Will skip the pre-requisites

Debugging the dispatch:
- Will go through every step of the sequence
- Will print the print-out to the executor

Note:
- Forcing the dispatch will run the complete "dispatch sequence".
- Forcing the dispatch will always tell user what outcome of run sequence is
- Context admin in command execution will only be allowed if it is `"-f"` or `"-d"`

---
#### Modes
TCAS has 4 different types of operation modes, in which some pre-requisites will act differently, together with other changes such as lower-level operation.

The following types of operation modes are available within the plugin:
- Operator mode:
    - Allows a player to take full manual control of the attraction.
        - Note: this will still need the control panel, so it will still limit players in execution of commands.
- Auto mode:
    - Allows the attraction to run automatically with predefined behaviors.
- Manual mode: 
    - Allows players to control specific parts of the attraction, like dispatches.
        - Note: this will completely change the way of controlling the attraction.
            - The following stuff will be set to manual:
                - Block sections
                - Switch tracks
                - Shunting
                - Sections within the dispatch sequence (e.g. the floor falling away from a dive coaster station)
                - External actuators, such as gates and show doors (if applicable, e.g. the doors between entrance to the building and pre-show)

        - Note: Rollercoasters don't really have their own actuator groups, but (most) flatrides do!
        This is why the flatrides will have seperate "manual" logic compared to the rollercoasters:
            - Seperate group control (e.g. arm 1 through 4 can be actuated to a specific angle by hand)
            - Seperate cart control (e.g. release the brakes of carriage 3 while carriage 1 to 4 (excluding 3), is still locked)

---
#### Power
This command will mostly be a pre-requisite to run while on operator mode, it will both claim the attraction to the designated operator, as well as enable the controls for said operator.

---
#### Showtriggers
Showtriggers will do as the name suggests, it will be able to run both shows but also seperate elements within a show. This part of the plugin needs to be designed at this time (date of writing is 25-11-2024). When the system has been designed, then the documentation will be added here, just like any other method within the plugin.

---
#### Gates and Restraints
Both of these commands are ran seperately. The gates will control an external actuator within the attraction tec group. The restraints on the other hand, will be internal actuators, which will entail that the pre-defined tec code will be used.

Keep in mind that gates are grouped, there is no need to designate every gate seperately.

---
#### Example

TCAS is written with TrainCarts in mind, which is why we are going to talk as if we are creating a TrainCarts attraction.

Let's say we have a rollercoaster called Goliath, that will be configured as follows:
- It has 2 trains (so there are 2 shunt locations)
- It has 1 station and 1 block section
- It has gates and restraints
- It also has a gate at the exit
- It has no showtriggers and/or external dispatch pre-requisites

For this example, let's assume we're going to make Goliath's tec code `GOL`.
That will make it so we can make the following channels:
- Shunting: 
    - `GOL_Shunt.A` (For station shunt sign)
    - `GOL_Shunt.B` (For end-brake shunt sign)
- Dispatch:
    - `GOL_Dispatch`
- Entrance gates:
    - `GOL_Gates.I`
- Exit gates:
    - `GOL_Gates.O`
- Restraints:
    - `GOL_Restraints`

Everything listed above, are actions (actuators). But what if we want to also read out what the status is of the attraction is, then we would need sensing abilities, we can do this using sensors.

To sense what the attraction is doing, we can add the following things:
- Is shunting allowed?
    - `GOL_CanShunt.A` (Can Shunt sign A actually shunt?)
    - `GOL_CanShunt.B` (Can Shunt sign B actually shunt?)
- Is dispatching "Safe"?
    - `GOL_TrackClear` (To check whether both trains are stationary in safe spots)
    - `GOL_TrainComms` (Is the train in the station, which will implicate it has communication?)
    - `GOL_GatesClosed` (Are the gates closed?)
    - `GOL_StationClear` (Are there no players in unsafe areas close to the station?)
    - `GOL_RestClosed` (Are the restraints closed?)

For automatic control, we would need to add a few more statements:
- `GOL_TrainOcc.A` (Train in station is occupied by player/players)
- `GOL_TrainOcc.B` (Train on brake-section is occupied by player/players)
- `GOL_SafePass` (Is it safe for the train on brake to pass through the station?)

