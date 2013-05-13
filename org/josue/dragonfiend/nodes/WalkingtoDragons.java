package org.josue.dragonfiend.nodes;

import org.josue.dragonfiend.method.Methods;
import org.josue.dragonfiend.util.Constants;
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
public class WalkingtoDragons extends Node {


    @Override
    public boolean activate() {
        final NPC DRAGON = NPCs.getNearest(Constants.BLACK_DRAGONS);
        return DRAGON == null;
    }

    @Override
    public void execute() {
        final SceneObject SHRINE = SceneEntities.getNearest(12093);
        System.out.println("Walking back to Dragons");
        final NPC DRAGON = NPCs.getNearest(Constants.BLACK_DRAGONS);
        if (SHRINE != null && Calculations.distanceTo(SHRINE) <= 3 && Inventory.contains(Constants.RAW_CHICKEN)) {
            if (Inventory.isItemSelected() == true && SHRINE != null) {
                SHRINE.interact("Use");
                Methods.sleep(200, 300, new Methods.Condition() {
                    @Override
                    public boolean validate() {
                        return Inventory.isItemSelected() == false;
                    }
                });;
            } else {
                Inventory.selectItem(Constants.RAW_CHICKEN);
            }
        } else if (DRAGON == null) {
            TilePath path;
            path = Walking.newTilePath(Constants.BANK_TO_PATH);
            path.reverse().traverse();
            Task.sleep(1500);
        }
    }
}

