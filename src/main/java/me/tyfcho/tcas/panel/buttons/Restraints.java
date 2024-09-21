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

public class Restraints {
    public ItemStack restraintsOpen;

    public ItemStack restraintsClosed;

    public Restraints() {
        FileManager fileManager = new FileManager((JavaPlugin) MCAPanels.plugin);
        int menutype = fileManager.getConfig("cp.yml").get().getInt("menustyle");
        if (menutype == 1) {
            this.restraintsOpen = new ItemStack(Material.RED_TERRACOTTA, 1);
            ItemMeta daM = this.restraintsOpen.getItemMeta();
            daM.setDisplayName(Util.color("&7Beugels"));
            ArrayList<String> lores = new ArrayList<>();
            lores.add(Util.color("&cOpen"));
            daM.setLore(lores);
            daM.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
            this.restraintsOpen.setItemMeta(daM);
            this.restraintsClosed = new ItemStack(Material.LIME_TERRACOTTA, 1);
            ItemMeta ddM = this.restraintsClosed.getItemMeta();
            ddM.setDisplayName(Util.color("&7Beugels"));
            ArrayList<String> lores2 = new ArrayList<>();
            lores2.add(Util.color("&2Gesloten"));
            ddM.setLore(lores2);
            ddM.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
            this.restraintsClosed.setItemMeta(ddM);
        } else {
            this.restraintsOpen = new ItemStack(Material.RED_CONCRETE, 1);
            ItemMeta daM = this.restraintsOpen.getItemMeta();
            daM.setDisplayName(Util.color("&7Beugels"));
            ArrayList<String> lores = new ArrayList<>();
            lores.add(Util.color("&cOpen"));
            daM.setLore(lores);
            daM.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
            this.restraintsOpen.setItemMeta(daM);
            this.restraintsClosed = new ItemStack(Material.LIME_CONCRETE, 1);
            ItemMeta ddM = this.restraintsClosed.getItemMeta();
            ddM.setDisplayName(Util.color("&7Beugels"));
            ArrayList<String> lores2 = new ArrayList<>();
            lores2.add(Util.color("&2Gesloten"));
            ddM.setLore(lores2);
            ddM.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
            this.restraintsClosed.setItemMeta(ddM);
        }
    }
}

