package org.josue.dragonfiend.nodes;

import org.josue.dragonfiend.sk.action.ActionBar;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.input.Keyboard;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.interactive.Players;

import java.awt.event.KeyEvent;

/**
 * Created with IntelliJ IDEA.
 * User: Josue
 * Date: 5/11/13
 * Time: 4:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class Abilities extends Node {


    @Override
    public boolean activate() {
        return Players.getLocal().isInCombat()
                && Players.getLocal().getInteracting() != null;
    }

    @Override
    public void execute() {
        System.out.println("Using Abilities");
        Mouse.setSpeed(Mouse.Speed.FAST);
        if (!ActionBar.isExpanded()) {
            ActionBar.setExpanded(true);
        }
        Keyboard.sendKey((char) KeyEvent.VK_ESCAPE, 500);

        for (int count = 9; count >= 1; count--) {
            if (ActionBar.isReady(count - 1)) {
                ActionBar.useSlot(count - 1);
                break;
            }

        }
    }
}

