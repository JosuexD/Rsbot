package org.josue.ZDrags.nodes;

import com.sun.xml.internal.ws.wsdl.writer.UsingAddressing;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.input.Keyboard;
import org.powerbot.game.api.methods.input.Mouse;
import org.josue.ZDrags.sk.action.ActionBar;
import org.powerbot.game.api.methods.interactive.Players;

import java.awt.event.KeyEvent;

/**
 * Created with IntelliJ IDEA.
 * User: Josue
 * Date: 5/11/13
 * Time: 4:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class Abilities {
    public static class UseAbilities extends Node{

        @Override
        public boolean activate() {
            return Players.getLocal().isInCombat()
                    && Players.getLocal().getInteracting() != null;        }

        @Override
        public void execute() {
            System.out.println("Using Abilities");
            Mouse.setSpeed(Mouse.Speed.FAST);
            if (!ActionBar.isExpanded()) {
                ActionBar.setExpanded(true);
            }
            Keyboard.sendKey((char) KeyEvent.VK_ESCAPE, 500);

            if (ActionBar.isReady(9)) {
                Keyboard.sendKey('0');
                Task.sleep(1000, 1200);
            } else if (ActionBar.isReady(8)) {
                Keyboard.sendKey('9');
                Task.sleep(3700, 3900);
            } else if (ActionBar.isReady(7)) {
                Keyboard.sendKey('8');
                Task.sleep(3700, 3900);
            } else if (ActionBar.isReady(6)) {
                Keyboard.sendKey('7');
                Task.sleep(1500, 1700);
            } else if (ActionBar.isReady(5)) {
                Keyboard.sendKey('6');
                Task.sleep(1000, 1200);
            } else if (ActionBar.isReady(4)) {
                Keyboard.sendKey('5');
                Task.sleep(1000, 1200);
            } else if (ActionBar.isReady(3)) {
                Keyboard.sendKey('4');
                Task.sleep(1500, 1700);
            } else if (ActionBar.isReady(2)) {
                Keyboard.sendKey('3');
                Task.sleep(2700, 3500);
            } else if (ActionBar.isReady(1)) {
                Keyboard.sendKey('2');
                Task.sleep(1000, 1200);
            } else if (ActionBar.isReady(0)) {
                Keyboard.sendKey('1');
                Task.sleep(1000, 1200);
            }
        }

    }
}
