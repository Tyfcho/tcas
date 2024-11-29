package me.tyfcho.tcas.integrations.traincarts;

/**
 * This module will be the main class for the communication between TCAS and TrainCarts.
 * This module will gather data from the TrainCartsDataReader class. 
 * From this module, the TrainCartsAPIWrapper will be able to send it to the rest of the plugin.
 */

import me.tyfcho.tcas.integrations.traincarts.TrainCartsDataReader;

public class TrainCartsIntegration {
    public List<String> getPowerGroup(String powerGroupTarget) {
        // Call TrainCartsPowerGroup class
        
        return List.of(powerGroup)
    }
}