package org.parabot.scriptwriter.revival.aCooker.core;

import org.parabot.core.ui.Logger;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.parabot.scriptwriter.revival.aCooker.UI.GUI;
import org.parabot.scriptwriter.revival.aCooker.data.Settings;
import org.parabot.scriptwriter.revival.aCooker.strategies.Banking;
import org.parabot.scriptwriter.revival.aCooker.strategies.Cooking;

import java.util.ArrayList;

@ScriptManifest(author = "Atex",
        category = Category.COOKING,
        description = "Cooks karambwan at ::fossil",
        name = "aCooker", servers = { "Revival" },
        version = 0.1)
public class Core extends Script {
    ArrayList<Strategy> strategies = new ArrayList<>();
    private static Settings settings;
    private static Script core;
    @Override
    public boolean onExecute() {
        strategies.add(new Banking());
        strategies.add(new Cooking());
        provide(strategies);
        core = this;

        GUI gui = new GUI();
        while(gui.isVisible()) {
            Time.sleep(500);
        }
        if(gui.getSettings() == null) {
            Logger.addMessage("Invalid input, stopping script");
            stopScript();
        }

        settings = gui.getSettings();

        return true;
    }

    public static Settings getSettings() {
        return settings;
    }

    public static void stopScript() {
        core.setState(STATE_STOPPED);
    }
}
