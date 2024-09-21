package me.tyfcho.tcas.Util;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.mca.mcapanels.MCAPanels;

public class Util {
    public static String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static void loadEvent(Listener eventClassName) {
        MCAPanels.plugin.getServer().getPluginManager().registerEvents(eventClassName, (Plugin)MCAPanels.plugin);
    }

    public static void loadCommand(String command, CommandExecutor executor) {
        MCAPanels.plugin.getCommand(command).setExecutor(executor);
    }
}
