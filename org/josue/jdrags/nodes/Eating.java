package org.josue.jdrags.nodes;

import org.josue.jdrags.util.Constants;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;

/**
 * Created with IntelliJ IDEA.
 * User: Josue
 * Date: 5/11/13
 * Time: 5:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class Eating extends Node {


    @Override
    public boolean activate() {
        return Players.getLocal().getHealthPercent() <= 50;
    }

    @Override
    public void execute() {
        Inventory.getItem(Constants.FOOD).getWidgetChild().interact("Eat");
        Task.sleep(750, 1300);
    }
}
