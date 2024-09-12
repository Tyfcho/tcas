i'd like to make it so that i can actually change it to sectors
so here's how i'd like to do it:
- make a factory using a gui, that will be called using /tcas create <attraction> (has to be unique)
- from this point, we can ask the user the following:
- what type of attraction is it? (coaster or flatride)
- if coaster > ask how many sectors there are (this'd be dependent on how many brake sections there are, so this'd be an user input between 0-9)
- if flatride > don't ask for the amount of sectors, but you should add a trigger that the attraction is done with its sequence, this'd be registered with /tcas sequence done <attraction>
- for both types, add the following subcommands: /tcas ridecontrol <subcommand> <bool>

subcommands would be:
- power (this will also claim the controlling user, if on "operator mode")
- dispatch
- gates
- restraints
- marshall
- mode (operator/auto/manual/maintenance, will explain below)
- showtrigger

all these subcommands need to be registered if the type is coaster, if the type is flatride, there won't be a need for marshalling

modes:
- operator: players that have the permission tcas.ridecontrol.operator would be allowed to claim the ride using the power command, while in this mode, the commands used need to be checked whether it is called upon by the "claiming" user each time before it will be executed, if it is not executed by the claimed user, deny the command
- auto: this would make it so it detect whether a player is in the attraction, then it'd start a timer that is configurable using /tcas options autostart <attraction> <time>, it will execute in the following order: open gates once sequence is done or the train is in the station with the restraints open, **player takes a seat**, start timer, once timer has been completed, close restraints, dispatch ride, sequence is done, open gates at exit, open restraints, open entrance gate, repeat
- manual: this would make it so every block section needs to be dispatched manually at coasters, or any sequence part in the sequence of a flatride
options regarding the flatride need to be registered for it to be in manual mode using:
/tcas manualcontrol register <attraction>
/tcas manualcontrol addcontrol <attraction> <controlname> <command>
/tcas manualcontrol removecontrol <attraction> <controlname>
/tcas manualcontrol actuate <attraction> <controlname> (this would set manualcontrol controlname section actuation to true in config, so the plugin can detect it is actuated)
/tcas manualcontrol reset <attraction> <control> (only execute if controlname section is set to true)
/tcas manualcontrol resetall <attraction> (execute reset for all controlname sections that are actuated)

on coasters, it would add the following:
/tcas manualcontrol register <attraction>
/tcas manualcontrol addblocksection <attraction> <section-id>
/tcas manualcontrol removeblock <attraction> <section-id>
/tcas manualcontrol dispatchsection <attraction> <section-id> (if there's a train detected)

maintenance: will only be used by server administrators, but to execute it, they will still need the permission node tcas.ridecontrol.maintenance
in maintenance mode, users will be allowed to use transfer tracks, marshall trains etc.
this will add the following commands:
/tcas maintenancecontrol register <attraction>
/tcas maintenancecontrol addtransfer <attraction> <section-id>
/tcas maintenancecontrol removetransfer <attraction> <section-id>
/tcas maintenancecontrol addtransferposition <attraction> <section-id> <gate>
/tcas maintenancecontrol removetransferposition <attraction> <section-id> <gate>
/tcas maintenancecontrol addtransfercommand <attraction> <section-id> <command>
/tcas maintenancecontrol removetransfercommand <attraction> <section-id> <command>
/tcas maintenancecontrol transfer <attraction> <section-id> <gate> <load/unload> (only allowed if the track is clear, depending on loading a train onto the track or unloading one check whether the transfer section is clear, this'd only be applicable with loading the train onto the track, also check what gates are occupied and which ones are not)

by default, there will be a 0 and 1 transfer position, 0 will be the one that is in the normal circuit, 1 will be the first transfer section. 

i'd like to make an addon for traincarts, in which i can add a property to a cart, which in turn will add support for moving it using the a and d buttons on the players keyboard, it would affect an animation on the cart that they themselves ride in, there should be a config file that allows the server owner to change the offset (so how much a button press would add to the velocity), then it should check whether it is allowed to control the movement or not using a boolean that will be given using a different property, these properties need to be able to be added onto both signs and nodes, there is an api that does that for traincarts.


// will be extended with future features, so when i tell you to have a look at the scope again, you'd need to look at this file.
