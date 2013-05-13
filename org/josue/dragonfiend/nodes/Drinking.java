package org.josue.dragonfiend.nodes;

import org.josue.dragonfiend.util.Constants;
import org.powerbot.core.event.events.MessageEvent;
import org.powerbot.core.event.listeners.MessageListener;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Settings;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.tab.Prayer;

/**
 * Created with IntelliJ IDEA.
 * User: Josue
 * Date: 5/11/13
 * Time: 5:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class Drinking extends Node implements MessageListener {


    @Override
    public boolean activate() {
        return Prayer.getPoints() <= 300 || Settings.get(902) == 1024;
    }

    @Override
    public void execute() {
        System.out.println("Drinking Potions");
        if (Prayer.getPoints() <= 300 && Inventory.contains(Constants.PRAYER_POTIONS)) {
            Inventory.getItem(Constants.PRAYER_POTIONS).getWidgetChild().interact("Drink");
            Task.sleep(1500);
        }
        if (Settings.get(902) == 0 && Inventory.contains(Constants.PRAYER_RENEWAL)) {
            Inventory.getItem(Constants.PRAYER_RENEWAL).getWidgetChild().interact("Drink");
        }
    }

    @Override
    public void messageReceived(MessageEvent messageEvent) {
        String txt = messageEvent.getMessage();
        if (txt.contains("Your prayer renewal will run out in 30 seconds.") && Inventory.contains(Constants.PRAYER_RENEWAL)) {
            Inventory.getItem(Constants.PRAYER_RENEWAL).getWidgetChild().interact("Drink");
        }
    }
}


