package org.josue.jdrags.nodes;

import org.josue.jdrags.util.Constants;
import org.powerbot.core.event.events.MessageEvent;
import org.powerbot.core.event.listeners.MessageListener;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Settings;
import org.powerbot.game.api.methods.tab.Inventory;

/**
 * Created with IntelliJ IDEA.
 * User: Josue
 * Date: 5/11/13
 * Time: 5:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class Antifire extends Node implements MessageListener {


    @Override
    public boolean activate() {
        return Settings.get(1299) == 0 && Inventory.contains(Constants.ANTIFIRE_POTIONS);
    }

    @Override
    public void execute() {
        System.out.println("Drinking Antifire");
        Inventory.getItem(Constants.ANTIFIRE_POTIONS).getWidgetChild().interact("Drink");
        Task.sleep(1500);
    }

    @Override
    public void messageReceived(MessageEvent messageEvent) {
        String txt = messageEvent.getMessage();
        if (txt.contains("Your resistance to dragonfire is about to run out.") && Inventory.contains(Constants.ANTIFIRE_POTIONS)) {
            Inventory.getItem(Constants.ANTIFIRE_POTIONS).getWidgetChild().interact("Drink");
        }
    }
}

