package me.tyfcho.tcas.listeners;

import me.tyfcho.tcas.gui.FactoryCreationGUI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class FactoryClickListener implements Listener {

    private final FactoryCreationGUI factoryCreationGUI;

    public FactoryClickListener(FactoryCreationGUI factoryCreationGUI) {
        this.factoryCreationGUI = factoryCreationGUI;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        // Check if it's our custom GUI
        if (event.getView().getTitle().contains("Select Attraction Type") ||
                event.getView().getTitle().contains("Configure")) {
            event.setCancelled(true);  // Prevent players from taking items from the GUI
            factoryCreationGUI.handleInventoryClick(event);
        }
    }
}
