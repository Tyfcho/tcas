package me.tyfcho.tcas.integrations.traincarts.signs.addons;

import com.bergerkiller.bukkit.tc.events.SignActionEvent;
import com.bergerkiller.bukkit.tc.events.SignChangeActionEvent;
import com.bergerkiller.bukkit.tc.signactions.SignAction;
import com.bergerkiller.bukkit.tc.signactions.SignActionType;
import org.bukkit.plugin.java.JavaPlugin;

public class TrainInStation extends SignAction {
    public boolean match(SignActionEvent signActionEvent) {
        return signActionEvent.isType(new String[] {"traininstation"});
    }

    public void execute(SignActionEvent signActionEvent) {
        if (signActionEvent.isAction(new signActionType[] {SignActionType.GROUP_ENTER}) || signActionEvent.isAction(new SignActionType[] {SignActionType.REDSTONE_ON})) {
            // TODO: Implement dispatch allowance reading/writing (see older projects)
        }
    }
}