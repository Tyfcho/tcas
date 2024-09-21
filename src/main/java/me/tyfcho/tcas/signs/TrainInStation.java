package me.tyfcho.tcas.signs;

import com.bergerkiller.bukkit.tc.events.SignActionEvent;
import com.bergerkiller.bukkit.tc.events.SignChangeActionEvent;
import com.bergerkiller.bukkit.tc.signactions.SignAction;
import com.bergerkiller.bukkit.tc.signactions.SignActionType;
import org.bukkit.plugin.java.JavaPlugin;
import org.mca.mcapanels.MCAPanels;
import org.mca.mcapanels.Util.FileManager;
import org.mca.mcapanels.Util.PanelAPI;

public class TrainInStation extends SignAction {
    public boolean match(SignActionEvent signActionEvent) {
        return signActionEvent.isType(new String[] { "traininstation" });
    }

    public void execute(SignActionEvent signActionEvent) {
        if (signActionEvent.isAction(new SignActionType[] { SignActionType.GROUP_ENTER }) || signActionEvent.isAction(new SignActionType[] { SignActionType.REDSTONE_ON })) {
            String panel = signActionEvent.getLine(2);
            FileManager fileManager = new FileManager((JavaPlugin)MCAPanels.plugin);
            fileManager.getConfig("cp.yml").get().set(panel + ".statusses.traininstation", Boolean.valueOf(true));
            fileManager.saveConfig("cp.yml");
            PanelAPI.updatePanel(panel);
        }
    }

    public boolean build(SignChangeActionEvent signChangeActionEvent) {
        String panelname = signChangeActionEvent.getLine(2);
        if (PanelAPI.panelExists(panelname)) {
            signChangeActionEvent.getPlayer().sendMessage("TrainInStation created");
            return true;
        }
        signChangeActionEvent.getPlayer().sendMessage("Panel does not exist");
        return false;
    }
}
