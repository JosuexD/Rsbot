package org.josue.dragonfiend.nodes;


import org.josue.dragonfiend.method.Methods;
import org.josue.dragonfiend.util.Constants;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.node.GroundItems;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.node.GroundItem;


/**
 * Created with IntelliJ IDEA.
 * User: Josue
 * Date: 5/11/13
 * Time: 5:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class Looting extends Node {


    @Override
    public boolean activate() {
        final GroundItem LOOT = GroundItems.getNearest(Constants.LOOT);
        return LOOT != null && !Inventory.isFull();
    }

    @Override
    public void execute() {
        System.out.println("Looting to make that bank :D");
        final GroundItem LOOT = GroundItems.getNearest(Constants.LOOT);
        if (LOOT != null) {
            if (Methods.isOnScreen(LOOT)) {
                LOOT.interact("Take", LOOT.getGroundItem().getName());
                Task.sleep(700, 1200);
            } else {
                Camera.turnTo(LOOT);
            }
        }
        if (Calculations.distanceTo(LOOT) >= 8) {
            Walking.walk(LOOT);
        }
    }
}

