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

public class Gates {
    public ItemStack gatesOpen;

    public ItemStack gatesClosed;

    public Gates() {
        FileManager fileManager = new FileManager((JavaPlugin) MCAPanels.plugin);
        int menutype = fileManager.getConfig("cp.yml").get().getInt("menustyle");
        if (menutype == 1) {
            this.gatesOpen = new ItemStack(Material.RED_TERRACOTTA, 1);
            ItemMeta daM = this.gatesOpen.getItemMeta();
            daM.setDisplayName(Util.color("&7Poortjes"));
            ArrayList<String> lores = new ArrayList<>();
            lores.add(Util.color("&cOpen"));
            daM.setLore(lores);
            daM.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
            this.gatesOpen.setItemMeta(daM);
            this.gatesClosed = new ItemStack(Material.LIME_TERRACOTTA, 1);
            ItemMeta ddM = this.gatesClosed.getItemMeta();
            ddM.setDisplayName(Util.color("&7Poortjes"));
            ArrayList<String> lores2 = new ArrayList<>();
            lores2.add(Util.color("&2Gesloten"));
            ddM.setLore(lores2);
            ddM.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
            this.gatesClosed.setItemMeta(ddM);
        } else {
            this.gatesOpen = new ItemStack(Material.RED_CONCRETE, 1);
            ItemMeta daM = this.gatesOpen.getItemMeta();
            daM.setDisplayName(Util.color("&7Poortjes"));
            ArrayList<String> lores = new ArrayList<>();
            lores.add(Util.color("&cOpen"));
            daM.setLore(lores);
            daM.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
            this.gatesOpen.setItemMeta(daM);
            this.gatesClosed = new ItemStack(Material.LIME_CONCRETE, 1);
            ItemMeta ddM = this.gatesClosed.getItemMeta();
            ddM.setDisplayName(Util.color("&7Poortjes"));
            ArrayList<String> lores2 = new ArrayList<>();
            lores2.add(Util.color("&2Gesloten"));
            ddM.setLore(lores2);
            ddM.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
            this.gatesClosed.setItemMeta(ddM);
        }
    }
}
