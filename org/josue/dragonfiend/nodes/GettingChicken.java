package org.josue.dragonfiend.nodes;

import org.josue.dragonfiend.method.Methods;
import org.josue.dragonfiend.util.Constants;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.GroundItems;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.GroundItem;
import org.powerbot.game.api.wrappers.node.SceneObject;

/**
 * Created with IntelliJ IDEA.
 * User: Josue
 * Date: 5/11/13
 * Time: 6:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class GettingChicken extends Node {

    private final int[] CHICKENS = {41, 1017};

    @Override
    public boolean activate() {
        final SceneObject SHRINE = SceneEntities.getNearest(12093);
        return SHRINE != null && !Inventory.isFull() &&
                !Inventory.contains(Constants.RAW_CHICKEN) &&
                Calculations.distanceTo(SHRINE) <= 5;

    }

    @Override
    public void execute() {
        System.out.println("Getting raw Chicken Meat");
        final NPC CHICKEN = NPCs.getNearest(CHICKENS);
        if (CHICKEN != null && Methods.isOnScreen(CHICKEN) && Players.getLocal().getInteracting() == null) {
            CHICKEN.interact("Attack");
            Task.sleep(1500);
        }
        final GroundItem RAW = GroundItems.getNearest(Constants.RAW_CHICKEN);
        if (RAW != null) {
            RAW.interact("Take", RAW.getGroundItem().getName());
            Task.sleep(1500);
        }
    }
}

