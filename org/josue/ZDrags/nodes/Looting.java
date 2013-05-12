package org.josue.ZDrags.nodes;


import org.josue.ZDrags.util.Constants;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;


import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.GroundItems;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.node.GroundItem;
import org.josue.ZDrags.method.Methods;


/**
 * Created with IntelliJ IDEA.
 * User: Josue
 * Date: 5/11/13
 * Time: 5:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class Looting {
    public static class Loot extends Node {

        @Override
        public boolean activate() {
            final GroundItem loot = GroundItems.getNearest(Constants.LOOT);

            return loot != null && !Inventory.isFull();
        }

        @Override
        public void execute() {
            System.out.println("Looting to make that bank :D");
            final GroundItem loot = GroundItems.getNearest(Constants.LOOT);
            if (loot != null) {
                if (Methods.isOnScreen(loot) && !Players.getLocal().isMoving()) {
                    loot.interact("Take");
                    Task.sleep(700, 1200);
                } else {
                    Camera.turnTo(loot);
                }
            }
            if (Calculations.distanceTo(loot) >= 8) {
                Walking.walk(loot);
            }
        }
    }
}
