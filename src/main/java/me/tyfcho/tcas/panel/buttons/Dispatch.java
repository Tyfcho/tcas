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

public class Dispatch {
    public ItemStack dispatchAllowed;

    public ItemStack dispatchDenied;

    public Dispatch() {
        FileManager fileManager = new FileManager((JavaPlugin) MCAPanels.plugin);
        int menutype = fileManager.getConfig("config.yml").get().getInt("menustyle");
        if (menutype == 1) {
            this.dispatchAllowed = new ItemStack(Material.BLUE_TERRACOTTA, 1);
            ItemMeta daM = this.dispatchAllowed.getItemMeta();
            daM.setDisplayName(Util.color("&7Vrijgave"));
            ArrayList<String> lores = new ArrayList<>();
            lores.add(Util.color("&3Toegestaan"));
            daM.setLore(lores);
            daM.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
            this.dispatchAllowed.setItemMeta(daM);
            this.dispatchDenied = new ItemStack(Material.RED_TERRACOTTA, 1);
            ItemMeta ddM = this.dispatchDenied.getItemMeta();
            ddM.setDisplayName(Util.color("&7Dispatch"));
            ArrayList<String> lores2 = new ArrayList<>();
            lores2.add(Util.color("&cAfgewezen"));
            ddM.setLore(lores2);
            ddM.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
            this.dispatchDenied.setItemMeta(ddM);
        } else {
            this.dispatchAllowed = new ItemStack(Material.BLUE_CONCRETE, 1);
            ItemMeta daM = this.dispatchAllowed.getItemMeta();
            daM.setDisplayName(Util.color("&7Vrijgave"));
            ArrayList<String> lores = new ArrayList<>();
            lores.add(Util.color("&3Toegestaan"));
            daM.setLore(lores);
            daM.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
            this.dispatchAllowed.setItemMeta(daM);
            this.dispatchDenied = new ItemStack(Material.RED_CONCRETE, 1);
            ItemMeta ddM = this.dispatchDenied.getItemMeta();
            ddM.setDisplayName(Util.color("&7Vrijgave"));
            ArrayList<String> lores2 = new ArrayList<>();
            lores2.add(Util.color("&cAfgewezen"));
            ddM.setLore(lores2);
            ddM.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
            this.dispatchDenied.setItemMeta(ddM);
        }
    }
}
