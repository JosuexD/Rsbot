package org.josue.jdrags.nodes;

import org.josue.jdrags.method.Methods;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Settings;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.wrappers.interactive.NPC;

/**
 * Created with IntelliJ IDEA.
 * User: Josue
 * Date: 5/11/13
 * Time: 5:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class QuickPrayers extends Node {


    @Override
    public boolean activate() {
        final NPC agr = Methods.getNearest();

        return Players.getLocal().isInCombat() && Players.getLocal().getInteracting() != null && Settings.get(1769) == 0;

    }

    @Override
    public void execute() {
        final NPC agr = Methods.getNearest();
        if (agr != null) {
            System.out.println("Quickprayer Node");
            Widgets.get(548, 165).click(true);
            Task.sleep(1000);
        } else {
            Widgets.get(548, 165).click(true);
            Task.sleep(1000);
        }
    }
}

