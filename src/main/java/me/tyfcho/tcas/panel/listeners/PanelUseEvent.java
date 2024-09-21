package me.tyfcho.tcas.panel.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.mca.mcapanels.MCAPanels;
import org.mca.mcapanels.Util.FileManager;
import org.mca.mcapanels.Util.PanelAPI;

import java.util.List;

public class PanelUseEvent implements Listener {
    @EventHandler
    public void invRightClick(InventoryClickEvent event) {
        if (PanelAPI.panelExists(ChatColor.stripColor(event.getView().getTitle())) &&
                event.getCurrentItem() != null) {
            String name = ChatColor.stripColor(event.getView().getTitle());
            FileManager fileManager = new FileManager((JavaPlugin)MCAPanels.plugin);
            event.setCancelled(true);
            int slot = event.getSlot();
            boolean power = fileManager.getConfig("cp.yml").get().getBoolean(name + ".statusses.poweron");
            boolean entrance = fileManager.getConfig("cp.yml").get().getBoolean(name + ".statusses.entranceopen");
            boolean gates = fileManager.getConfig("cp.yml").get().getBoolean(name + ".statusses.gatesopen");
            boolean restraints = fileManager.getConfig("cp.yml").get().getBoolean(name + ".statusses.restraintsopen");
            boolean dispatch = fileManager.getConfig("cp.yml").get().getBoolean(name + ".statusses.dispatchallowed");
            boolean trackclear = fileManager.getConfig("cp.yml").get().getBoolean(name + ".statusses.trackclear");
            boolean traininstation = fileManager.getConfig("cp.yml").get().getBoolean(name + ".statusses.traininstation");
            if (slot == 10) {
                fileManager.getConfig("cp.yml").get().set(name + ".statusses.poweron", Boolean.valueOf(!power));
                fileManager.saveConfig("cp.yml");
                if (!power & trackclear & !traininstation) {
                    List<String> commands = fileManager.getConfig("cp.yml").get().getStringList(name + ".commands.power.on");
                    PanelAPI.executeCommands(commands);
                } else if (power & trackclear & traininstation & !entrance) {
                    List<String> commands = fileManager.getConfig("cp.yml").get().getStringList(name + ".commands.power.off");
                    PanelAPI.executeCommands(commands);
                    if (!gates) {
                        fileManager.getConfig("cp.yml").get().set(name + ".statusses.gatesopen", Boolean.valueOf(gates));
                        fileManager.saveConfig("cp.yml");
                        List<String> commands2 = fileManager.getConfig("cp.yml").get().getStringList(name + ".commands.gates.open");
                        PanelAPI.executeCommands(commands2);
                    } else {
                        List<String> commands2 = fileManager.getConfig("cp.yml").get().getStringList(name + ".commands.gates.closed");
                        PanelAPI.executeCommands(commands2);
                    }
                    if (!restraints) {
                        fileManager.getConfig("cp.yml").get().set(name + ".statusses.restraintsopen", Boolean.valueOf(restraints));
                        fileManager.saveConfig("cp.yml");
                        List<String> commands2 = fileManager.getConfig("cp.yml").get().getStringList(name + ".commands.restraints.open");
                        PanelAPI.executeCommands(commands2);
                    } else {
                        List<String> commands2 = fileManager.getConfig("cp.yml").get().getStringList(name + ".commands.restraints.closed");
                        PanelAPI.executeCommands(commands2);
                    }
                    if (dispatch) {
                        fileManager.getConfig("cp.yml").get().set(name + ".statusses.dispatchallowed", Boolean.valueOf(!dispatch));
                        fileManager.saveConfig("cp.yml");
                    }
                    if (!trackclear) {
                        fileManager.getConfig("cp.yml").get().set(name + ".statusses.trackclear", Boolean.valueOf(true));
                        fileManager.saveConfig("cp.yml");
                    }
                    if (traininstation) {
                        fileManager.getConfig("cp.yml").get().set(name + ".statusses.traininstation", Boolean.valueOf(false));
                        fileManager.saveConfig("cp.yml");
                    }
                }
                PanelAPI.updatePanel(name);
                return;
            }
            if (power) {
                if (slot == 13 && traininstation) {
                    fileManager.getConfig("cp.yml").get().set(name + ".statusses.gatesopen", Boolean.valueOf(!gates));
                    fileManager.saveConfig("cp.yml");
                    PanelAPI.updatePanel(name);
                    if (gates) {
                        List<String> commands = fileManager.getConfig("cp.yml").get().getStringList(name + ".commands.gates.closed");
                        PanelAPI.executeCommands(commands);
                    } else {
                        List<String> commands = fileManager.getConfig("cp.yml").get().getStringList(name + ".commands.gates.open");
                        PanelAPI.executeCommands(commands);
                    }
                    PanelAPI.updatePanel(name);
                    return;
                }
                if (slot == 14 && traininstation) {
                    fileManager.getConfig("cp.yml").get().set(name + ".statusses.restraintsopen", Boolean.valueOf(!restraints));
                    fileManager.saveConfig("cp.yml");
                    if (restraints) {
                        List<String> commands = fileManager.getConfig("cp.yml").get().getStringList(name + ".commands.restraints.closed");
                        PanelAPI.executeCommands(commands);
                    } else {
                        List<String> commands = fileManager.getConfig("cp.yml").get().getStringList(name + ".commands.restraints.open");
                        PanelAPI.executeCommands(commands);
                    }
                    PanelAPI.updatePanel(name);
                    return;
                }
                if (slot == 16 && !gates && !restraints && trackclear && traininstation) {
                    fileManager.getConfig("cp.yml").get().set(name + ".statusses.dispatchallowed", Boolean.valueOf(!dispatch));
                    fileManager.getConfig("cp.yml").get().set(name + ".statusses.trackclear", Boolean.valueOf(false));
                    fileManager.getConfig("cp.yml").get().set(name + ".statusses.traininstation", Boolean.valueOf(false));
                    fileManager.saveConfig("cp.yml");
                    List<String> dispatchCommands = fileManager.getConfig("cp.yml").get().getStringList(name + ".commands.dispatch");
                    PanelAPI.executeCommands(dispatchCommands);
                    PanelAPI.updatePanel(name);
                    return;
                }
            }
            if (slot == 11) {
                fileManager.getConfig("cp.yml").get().set(name + ".statusses.entranceopen", Boolean.valueOf(!entrance));
                fileManager.saveConfig("cp.yml");
                if (entrance) {
                    List<String> commands = fileManager.getConfig("cp.yml").get().getStringList(name + ".commands.entrance.closed");
                    PanelAPI.executeCommands(commands);
                } else {
                    List<String> commands = fileManager.getConfig("cp.yml").get().getStringList(name + ".commands.entrance.open");
                    PanelAPI.executeCommands(commands);
                }
                PanelAPI.updatePanel(name);
            }
        }
    }
}
