package org.josue.ZDrags.nodes;

import org.josue.ZDrags.method.Methods;
import org.josue.ZDrags.util.Constants;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.util.Random;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Summoning;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.wrappers.interactive.NPC;

/**
 * Created with IntelliJ IDEA.
 * User: Josue
 * Date: 5/11/13
 * Time: 4:37 PM
 * To change this template use File | Settings | File Templates.
 */

public class Attack {
    public static Filter<NPC> AGGRO_FILTER = new Filter<NPC>() {
        @Override
        public boolean accept(NPC aggresive) {
            return aggresive != null
                    && aggresive.getInteracting() != null
                    && !aggresive.equals(Summoning.getFamiliar())
                    && aggresive.getInteracting().equals(Players.getLocal());
        }
    };

    public static NPC getNearest() {
        return NPCs.getNearest(AGGRO_FILTER);
    }

    public static class Attacking extends Node {

        @Override
        public boolean activate() {

            final NPC dragon = NPCs.getNearest(Constants.BLACK_DRAGONS);
            return dragon != null && Players.getLocal().getInteracting() == null;
        }

        @Override
        public void execute() {
            System.out.println("Attacking Node");
            final NPC agr = getNearest();
            final NPC dragon = NPCs.getNearest(Constants.BLACK_DRAGONS);
            if (agr != null) {
                System.out.println("Aggressive Spotted");
                if (Methods.isOnScreen(agr)) {
                    agr.interact("Attack");
                    Task.sleep(700, 1500);
                } else {
                    Walking.walk(agr);
                    Camera.turnTo(agr);
                    Task.sleep(700, 1500);
                }
            }
            else if (agr == null && dragon != null && !dragon.isInCombat() && dragon.getInteracting() == null){
                if(Methods.isOnScreen(dragon)){
                     System.out.println("Attacking idle dragon");
                dragon.interact("Attack");
                Camera.turnTo(dragon);
                Task.sleep(1500);
                }
                else {
                    Walking.walk(dragon);
                    Camera.turnTo(dragon);
                }
            }

        }
    }
}
