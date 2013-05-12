package org.josue.ZDrags.nodes;

import org.josue.ZDrags.util.Constants;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;

import org.powerbot.game.api.methods.Settings;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.wrappers.interactive.NPC;

/**
 * Created with IntelliJ IDEA.
 * User: Josue
 * Date: 5/11/13
 * Time: 5:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class QuickPrayers {
    public static class Quickprayer extends Node {

        @Override
        public boolean activate() {
            final NPC dragon = NPCs.getNearest(Constants.BLACK_DRAGONS);
            return Players.getLocal().isInCombat() && Settings.get(1769) == 0
                    && dragon != null;
        }

        @Override
        public void execute() {
            System.out.println("Quickprayer Node");
            Widgets.get(548, 165).click(true);
            Task.sleep(1000);
        }
    }
}
