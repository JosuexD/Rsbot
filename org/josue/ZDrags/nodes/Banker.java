package org.josue.ZDrags.nodes;

import org.josue.ZDrags.util.Constants;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;

/**
 * Created with IntelliJ IDEA.
 * User: Josue
 * Date: 5/11/13
 * Time: 4:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class Banker {
    public static class Banking extends Node {

        @Override
        public boolean activate() {
            return Constants.BANK_AREA.contains(Players.getLocal().getLocation()) && Inventory.getCount() != 8;
        }

        @Override
        public void execute() {
             System.out.println("Banking Node");
            if((!Bank.isOpen() && (Inventory.isFull() || Inventory.getCount() != 8 ))){
                System.out.println("Opening Bank");
                Bank.open();
                Task.sleep(1500, 2000);

            }
            if (Bank.isOpen()){
                if(Inventory.getCount() >8){
                    System.out.println("Depositing Inventory");
                    Bank.depositInventory();
                    Task.sleep(1000);
                }
                else{
                    System.out.println("Withdrawing Stuff");
                    Bank.withdraw(Constants.IMP, 1);
                    Bank.withdraw(Constants.PRAYER_FLASK, 2);
                    Bank.withdraw(Constants.PRAYER_RENEWAL_FLASK, 1);
                    Bank.withdraw(Constants.SUPER_ANTIFIRE, 1);
                    Bank.withdraw(Constants.FOOD, 3);
                    Bank.close();
                }
            }

        }
    }
}
