package me.tyfcho.tcas.panel.buttons;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.mca.mcapanels.MCAPanels;
import org.mca.mcapanels.Util.FileManager;
import org.mca.mcapanels.Util.Util;

import java.util.ArrayList;

public class Entrance {
    public ItemStack entranceOpen;

    public ItemStack entranceClosed;

    public Entrance() {
        FileManager fileManager = new FileManager((JavaPlugin) MCAPanels.plugin);
        int menutype = fileManager.getConfig("config.yml").get().getInt("menustyle");
        if (menutype == 1) {
            this.entranceClosed = new ItemStack(Material.BLUE_TERRACOTTA, 1);
            ItemMeta daM = this.entranceClosed.getItemMeta();
            daM.setDisplayName(Util.color("&7Ingang"));
            ArrayList<String> lores = new ArrayList<>();
            lores.add(Util.color("&3Gesloten"));
            daM.setLore(lores);
            daM.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
            this.entranceClosed.setItemMeta(daM);
            this.entranceOpen = new ItemStack(Material.LIGHT_BLUE_TERRACOTTA, 1);
            ItemMeta ddM = this.entranceOpen.getItemMeta();
            ddM.setDisplayName(Util.color("&7Ingang"));
            ArrayList<String> lores2 = new ArrayList<>();
            lores2.add(Util.color("&3Open"));
            ddM.setLore(lores2);
            ddM.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
            this.entranceOpen.setItemMeta(ddM);
        } else {
            this.entranceClosed = new ItemStack(Material.BLUE_CONCRETE, 1);
            ItemMeta daM = this.entranceClosed.getItemMeta();
            daM.setDisplayName(Util.color("&7Ingang"));
            ArrayList<String> lores = new ArrayList<>();
            lores.add(Util.color("&3Gesloten"));
            daM.setLore(lores);
            daM.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
            this.entranceClosed.setItemMeta(daM);
            this.entranceOpen = new ItemStack(Material.LIGHT_BLUE_CONCRETE, 1);
            ItemMeta ddM = this.entranceOpen.getItemMeta();
            ddM.setDisplayName(Util.color("&7Ingang"));
            ArrayList<String> lores2 = new ArrayList<>();
            lores2.add(Util.color("&3Open"));
            ddM.setLore(lores2);
            ddM.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
            this.entranceOpen.setItemMeta(ddM);
        }
    }
}

