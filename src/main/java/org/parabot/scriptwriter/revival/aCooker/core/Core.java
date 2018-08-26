package org.parabot.scriptwriter.revival.aCooker.core;

import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
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
    @Override
    public boolean onExecute() {
        strategies.add(new Banking());
        strategies.add(new Cooking());
        provide(strategies);
        return true;
    }
}
