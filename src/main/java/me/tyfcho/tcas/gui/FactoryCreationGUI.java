package me.tyfcho.tcas.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class FactoryCreationGUI {

    // Open the initial GUI where players select between Flatrides and Coasters
    public void openAttractionTypeGUI(Player player) {
        Inventory gui = Bukkit.createInventory(null, 9, "Select Attraction Type");

        // Create items for Flatrides and Coasters
        ItemStack flatrideItem = new ItemStack(Material.SLIME_BLOCK);
        ItemMeta flatrideMeta = flatrideItem.getItemMeta();
        flatrideMeta.setDisplayName(ChatColor.GREEN + "Flatride");
        flatrideItem.setItemMeta(flatrideMeta);

        ItemStack coasterItem = new ItemStack(Material.MINECART);
        ItemMeta coasterMeta = coasterItem.getItemMeta();
        coasterMeta.setDisplayName(ChatColor.BLUE + "Coaster");
        coasterItem.setItemMeta(coasterMeta);

        // Set items in the GUI
        gui.setItem(1, flatrideItem);
        gui.setItem(2, coasterItem);

        // Open the GUI for the player
        player.openInventory(gui);
    }

    // Handle the player clicking on either option
    public void handleInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack clickedItem = event.getCurrentItem();

        if (event.getView().getTitle().equals("Select Attraction Type")) {
            if (clickedItem != null && clickedItem.getItemMeta() != null) {
                String displayName = clickedItem.getItemMeta().getDisplayName();

                if (displayName.contains("Flatride")) {
                    player.sendMessage("You selected Flatride.");
                    // Proceed to Route 1: Ask about gates and triggers
                    openFlatrideConfigGUI(player);
                } else if (displayName.contains("Coaster")) {
                    player.sendMessage("You selected Coaster.");
                    // Proceed to Route 2: Ask about stations
                    openCoasterStationConfigGUI(player);
                }
            }
        }
    }

    // Additional method for opening the Flatride configuration GUI
    public void openFlatrideConfigGUI(Player player) {
        Inventory gui = Bukkit.createInventory(null, 9, "Configure Flatrides");

        // Create Yes/No options
        ItemStack yesItem = new ItemStack(Material.LIME_WOOL);
        ItemMeta yesMeta = yesItem.getItemMeta();
        yesMeta.setDisplayName(ChatColor.GREEN + "Yes");
        yesItem.setItemMeta(yesMeta);

        ItemStack noItem = new ItemStack(Material.RED_WOOL);
        ItemMeta noMeta = noItem.getItemMeta();
        noMeta.setDisplayName(ChatColor.RED + "No");
        noItem.setItemMeta(noMeta);

        // Example: Question 1: Entrance gate
        gui.setItem(1, yesItem);  // Yes option
        gui.setItem(2, noItem);   // No option

        player.openInventory(gui);
    }

    // Additional method for opening the Coaster configuration GUI
    public void openCoasterStationConfigGUI(Player player) {
        Inventory gui = Bukkit.createInventory(null, 9, "Configure Coasters");

        // Create Yes/No options for the station type
        ItemStack singleStationItem = new ItemStack(Material.POWERED_RAIL);
        ItemMeta singleStationMeta = singleStationItem.getItemMeta();
        singleStationMeta.setDisplayName(ChatColor.YELLOW + "Single Station");
        singleStationItem.setItemMeta(singleStationMeta);

        ItemStack doubleStationItem = new ItemStack(Material.DETECTOR_RAIL);
        ItemMeta doubleStationMeta = doubleStationItem.getItemMeta();
        doubleStationMeta.setDisplayName(ChatColor.AQUA + "Double Station");
        doubleStationItem.setItemMeta(doubleStationMeta);

        // Set items in the GUI
        gui.setItem(1, singleStationItem);
        gui.setItem(2, doubleStationItem);

        player.openInventory(gui);
    }
}
