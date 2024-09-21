package me.tyfcho.tcas.Util;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;
import me.tyfcho.tcas.panel.buttons.PanelButtons;
import me.tyfcho.tcas.AdvancedSigns;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PanelAPI {
    public static List<String> getPanels() {
        try {
            FileManager fileManager = new FileManager((JavaPlugin)AdvancedSigns.plugin);
            List<String> panels = new ArrayList<>();
            for (String panel : fileManager.getConfig("cp.yml").get().getConfigurationSection("").getKeys(false)) {
                if (!panel.equals("menustyle"))
                    panels.add(panel);
            }
            return panels;
        } catch (Exception ex) {
            return new ArrayList<>();
        }
    }

    public static boolean panelExists(String name) {
        return getPanels().contains(name);
    }

    public static void updatePanel(String name) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (ChatColor.stripColor(player.getOpenInventory().getTitle()).equals(name))
                openpanel(name, player);
        }
    }

    public static void removePanel(String name) {
        FileManager fileManager = new FileManager((JavaPlugin)MCAPanels.plugin);
        fileManager.getConfig("cp.yml").get().set(name, null);
        fileManager.saveConfig("cp.yml");
    }

    public static void resetPanels() {
        FileManager fileManager = new FileManager((JavaPlugin)MCAPanels.plugin);
        for (String panel : getPanels()) {
            if (panel != "menustyle") {
                fileManager.getConfig("cp.yml").get().set(panel + ".statusses.gatesopen", Boolean.valueOf(false));
                fileManager.getConfig("cp.yml").get().set(panel + ".statusses.restraintsopen", Boolean.valueOf(false));
                fileManager.getConfig("cp.yml").get().set(panel + ".statusses.entranceopen", Boolean.valueOf(false));
                fileManager.getConfig("cp.yml").get().set(panel + ".statusses.poweron", Boolean.valueOf(false));
                fileManager.getConfig("cp.yml").get().set(panel + ".statusses.dispatchallowed", Boolean.valueOf(false));
                fileManager.getConfig("cp.yml").get().set(panel + ".statusses.trackclear", Boolean.valueOf(true));
                fileManager.getConfig("cp.yml").get().set(panel + ".statusses.traininstation", Boolean.valueOf(false));
                fileManager.saveConfig("cp.yml");
            }
        }
    }

    public static void openpanel(String name, Player player) {
        FileManager fileManager = new FileManager((JavaPlugin)MCAPanels.plugin);
        Inventory inventory = Bukkit.createInventory(null, 27, (Component)Component.text(Objects.<String>requireNonNull(fileManager.getConfig("cp.yml").get().getString(name + ".displayname"))));
        if (!fileManager.getConfig("cp.yml").get().contains(name)) {
            player.sendMessage("Panel not found.");
        } else {
            boolean power = fileManager.getConfig("cp.yml").get().getBoolean(name + ".statusses.poweron");
            boolean entrance = fileManager.getConfig("cp.yml").get().getBoolean(name + ".statusses.entranceopen");
            boolean gates = fileManager.getConfig("cp.yml").get().getBoolean(name + ".statusses.gatesopen");
            boolean restraints = fileManager.getConfig("cp.yml").get().getBoolean(name + ".statusses.restraintsopen");
            boolean dispatch = fileManager.getConfig("cp.yml").get().getBoolean(name + ".statusses.dispatchallowed");
            boolean trackclear = fileManager.getConfig("cp.yml").get().getBoolean(name + ".statusses.trackclear");
            boolean traininstation = fileManager.getConfig("cp.yml").get().getBoolean(name + ".statusses.traininstation");
            PanelButtons panelButtons = new PanelButtons();
            if (power) {
                inventory.setItem(10, panelButtons.power.powerOn);
            } else {
                inventory.setItem(10, panelButtons.power.powerOff);
            }
            if (entrance) {
                inventory.setItem(11, panelButtons.entrance.entranceOpen);
            } else {
                inventory.setItem(11, panelButtons.entrance.entranceClosed);
            }
            if (gates) {
                inventory.setItem(13, panelButtons.gates.gatesOpen);
            } else {
                inventory.setItem(13, panelButtons.gates.gatesClosed);
            }
            if (restraints) {
                inventory.setItem(14, panelButtons.restraints.restraintsOpen);
            } else {
                inventory.setItem(14, panelButtons.restraints.restraintsClosed);
            }
            if (power && !gates && !restraints && traininstation && trackclear) {
                inventory.setItem(16, panelButtons.dispatch.dispatchAllowed);
            } else {
                inventory.setItem(16, panelButtons.dispatch.dispatchDenied);
            }
            player.openInventory(inventory);
        }
    }

    public static void executeCommands(List<String> commands) {
        for (String s : commands)
            Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), s);
    }

    public static void createPanel(String name) {
        FileManager fileManager = new FileManager((JavaPlugin)MCAPanels.plugin);
        fileManager.getConfig("cp.yml").get().set(name + ".displayname", name);
        fileManager.getConfig("cp.yml").get().set(name + ".statusses.gatesopen", Boolean.valueOf(false));
        fileManager.getConfig("cp.yml").get().set(name + ".statusses.restraintsopen", Boolean.valueOf(false));
        fileManager.getConfig("cp.yml").get().set(name + ".statusses.poweron", Boolean.valueOf(false));
        fileManager.getConfig("cp.yml").get().set(name + ".statusses.entranceopen", Boolean.valueOf(false));
        fileManager.getConfig("cp.yml").get().set(name + ".statusses.dispatchallowed", Boolean.valueOf(false));
        fileManager.getConfig("cp.yml").get().set(name + ".statusses.trackclear", Boolean.valueOf(true));
        fileManager.getConfig("cp.yml").get().set(name + ".statusses.traininstation", Boolean.valueOf(true));
        List<String> list = new ArrayList<>();
        list.add("command1");
        list.add("command2");
        List<String> list2 = new ArrayList<>();
        list2.add("command3");
        list2.add("command4");
        List<String> list3 = new ArrayList<>();
        list3.add("command5");
        list3.add("command6");
        List<String> list4 = new ArrayList<>();
        list4.add("command7");
        list4.add("command8");
        List<String> list5 = new ArrayList<>();
        list5.add("command9");
        list5.add("command10");
        List<String> list6 = new ArrayList<>();
        list6.add("command11");
        list6.add("command12");
        List<String> list7 = new ArrayList<>();
        list7.add("command13");
        list7.add("command14");
        List<String> list8 = new ArrayList<>();
        list8.add("command15");
        list8.add("command16");
        List<String> list9 = new ArrayList<>();
        list9.add("command17");
        list9.add("command18");
        fileManager.getConfig("cp.yml").get().set(name + ".commands.gates.open", list);
        fileManager.getConfig("cp.yml").get().set(name + ".commands.gates.closed", list2);
        fileManager.getConfig("cp.yml").get().set(name + ".commands.restraints.open", list3);
        fileManager.getConfig("cp.yml").get().set(name + ".commands.restraints.closed", list4);
        fileManager.getConfig("cp.yml").get().set(name + ".commands.power.on", list5);
        fileManager.getConfig("cp.yml").get().set(name + ".commands.power.off", list6);
        fileManager.getConfig("cp.yml").get().set(name + ".commands.entrance.open", list7);
        fileManager.getConfig("cp.yml").get().set(name + ".commands.entrance.closed", list8);
        fileManager.getConfig("cp.yml").get().set(name + ".commands.dispatch", list9);
        fileManager.saveConfig("cp.yml");
    }
}
