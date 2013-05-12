package org.josue.ZDrags.nodes;

import org.josue.ZDrags.util.Constants;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.tab.Prayer;

/**
 * Created with IntelliJ IDEA.
 * User: Josue
 * Date: 5/11/13
 * Time: 5:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class Drinking {
    public static class Drink extends Node {


        @Override
        public boolean activate() {
            return Prayer.getPoints() <= 300;
        }

        @Override
        public void execute() {
            System.out.println("Drinking Potions");
            for(int p : Constants.PRAYER_POTIONS){
                Inventory.getItem(p).getWidgetChild().interact("Drink");
                Task.sleep(1500);

            }
        }
    }
}
