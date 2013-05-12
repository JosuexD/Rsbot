package org.josue.jdrags.nodes;

import org.josue.jdrags.method.Methods;
import org.josue.jdrags.util.Constants;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.interactive.NPC;

/**
 * Created with IntelliJ IDEA.
 * User: Josue
 * Date: 5/11/13
 * Time: 4:37 PM
 * To change this template use File | Settings | File Templates.
 */

public class Attack extends Node {


    @Override
    public boolean activate() {

        final NPC dragon = NPCs.getNearest(Constants.BLACK_DRAGONS);
        return dragon != null && Players.getLocal().getInteracting() == null;
    }

    @Override
    public void execute() {
        System.out.println("Attacking Node");
        final NPC agr = Methods.getNearest();
        final NPC dragon = NPCs.getNearest(Constants.BLACK_DRAGONS);
        if (agr != null && agr.getInteracting().equals(Players.getLocal())) {
            System.out.println("Aggressive Spotted");
            if (Methods.isOnScreen(agr)) {
                agr.interact("Attack");
                Task.sleep(700, 1500);
            } else {
                Walking.walk(agr);
                Camera.turnTo(agr);
                Task.sleep(700, 1500);
            }
        } else if (agr == null && dragon != null && !dragon.isInCombat() && dragon.getInteracting() == null) {
            if (Methods.isOnScreen(dragon)) {
                System.out.println("Attacking idle dragon");
                dragon.interact("Attack");
                Camera.turnTo(dragon);
                Task.sleep(1500);
            } else {
                Walking.walk(dragon);
                Camera.turnTo(dragon);
            }
        }

    }
}

