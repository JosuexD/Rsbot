package org.josue.dragonfiend.nodes;

import org.josue.dragonfiend.method.Methods;
import org.josue.dragonfiend.util.Constants;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.tab.Prayer;
import org.powerbot.game.api.methods.widget.Camera;
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
public class WalkingtoBank extends Node {


    @Override
    public boolean activate() {
        return Inventory.isFull() || !Inventory.contains(Constants.ANTIFIRE_POTIONS)
                || Players.getLocal().getHealthPercent() <= 30 && !Inventory.contains(Constants.FOOD);
    }

    @Override
    public void execute() {
        System.out.println("Walking back to bank");
        final SceneObject PORTAL = SceneEntities.getNearest(Constants.PORTAL);
        final NPC AGGRESSIVE = Methods.getNearest();
        if (Prayer.isQuickOn() == true && AGGRESSIVE == null) {
            Prayer.toggleQuick(false);
            Task.sleep(1000);
        }
        if (PORTAL != null) {
            if (PORTAL.isOnScreen()) {
                PORTAL.interact("Enter");
                Task.sleep(700, 1300);
            }
            if (Calculations.distanceTo(PORTAL) >= 5) {
                Walking.walk(PORTAL);
            } else Camera.turnTo(PORTAL);

        } else if (!Constants.BANK_AREA.contains(Players.getLocal().getLocation())) {
            TilePath path;
            path = Walking.newTilePath(Constants.BANK_TO_PATH);
            path.traverse();
            Task.sleep(1500);
        }

    }
}

