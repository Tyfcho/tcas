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

public class Power {
    public ItemStack powerOn;

    public ItemStack powerOff;

    public Power() {
        FileManager fileManager = new FileManager((JavaPlugin) MCAPanels.plugin);
        int menutype = fileManager.getConfig("config.yml").get().getInt("menustyle");
        if (menutype == 1) {
            this.powerOn = new ItemStack(Material.LIME_TERRACOTTA, 1);
            ItemMeta daM = this.powerOn.getItemMeta();
            daM.setDisplayName(Util.color("&7Bedrijfstelling"));
            ArrayList<String> lores = new ArrayList<>();
            lores.add(Util.color("&2In Bedrijf"));
            daM.setLore(lores);
            daM.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
            this.powerOn.setItemMeta(daM);
            this.powerOff = new ItemStack(Material.RED_TERRACOTTA, 1);
            ItemMeta ddM = this.powerOff.getItemMeta();
            ddM.setDisplayName(Util.color("&7Bedrijfstelling"));
            ArrayList<String> lores2 = new ArrayList<>();
            lores2.add(Util.color("&cUit Bedrijf"));
            ddM.setLore(lores2);
            ddM.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
            this.powerOff.setItemMeta(ddM);
        } else {
            this.powerOn = new ItemStack(Material.LIME_CONCRETE, 1);
            ItemMeta daM = this.powerOn.getItemMeta();
            daM.setDisplayName(Util.color("&7Bedrijfstelling"));
            ArrayList<String> lores = new ArrayList<>();
            lores.add(Util.color("&2In Bedrijf"));
            daM.setLore(lores);
            daM.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
            this.powerOn.setItemMeta(daM);
            this.powerOff = new ItemStack(Material.RED_CONCRETE, 1);
            ItemMeta ddM = this.powerOff.getItemMeta();
            ddM.setDisplayName(Util.color("&7Bedrijfstelling"));
            ArrayList<String> lores2 = new ArrayList<>();
            lores2.add(Util.color("&cUit Bedrijf"));
            ddM.setLore(lores2);
            ddM.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
            this.powerOff.setItemMeta(ddM);
        }
    }
}