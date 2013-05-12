package org.josue.ZDrags.nodes;

import org.josue.ZDrags.util.Constants;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.map.TilePath;
import org.powerbot.game.api.wrappers.node.SceneObject;

/**
 * Created with IntelliJ IDEA.
 * User: Josue
 * Date: 5/11/13
 * Time: 4:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class WalkingtoDragons {
    public static class DragonWalking extends Node {

        @Override
        public boolean activate() {
            final NPC dragon = NPCs.getNearest(Constants.BLACK_DRAGONS);
            return dragon == null;
        }

        @Override
        public void execute() {
            final SceneObject shrine = SceneEntities.getNearest(12093);
            System.out.println("Walking back to dragons");
            final NPC dragon = NPCs.getNearest(Constants.BLACK_DRAGONS);
            if (Calculations.distanceTo(shrine) <= 3 && Inventory.contains(Constants.RAW_CHICKEN) && shrine != null) {

                if (Inventory.isItemSelected() == true && shrine != null) {
                    shrine.interact("Use");
                } else {
                    Inventory.selectItem(Constants.RAW_CHICKEN);
                }

            } else if (dragon == null) {
                TilePath path;
                path = Walking.newTilePath(Constants.BANK_TO_PATH);
                path.reverse().traverse();
                Task.sleep(1500);
            }

        }
    }
}
