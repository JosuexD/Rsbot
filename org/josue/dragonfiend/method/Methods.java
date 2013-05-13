package org.josue.dragonfiend.method;


import org.josue.dragonfiend.util.Variables;
import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.methods.tab.Summoning;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.Entity;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

/**
 * Created with IntelliJ IDEA.
 * User: Josue
 * Date: 5/11/13
 * Time: 5:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class Methods {
    public static boolean isOnScreen(Entity e) {
        WidgetChild actionbar = Widgets.get(640, 6);
        return e.isOnScreen()
                && (!actionbar.isOnScreen() || !actionbar.getBoundingRectangle().contains(e.getCentralPoint()));
    }

    private static final Filter<NPC> AGGRO_FILTER = new Filter<NPC>() {
        @Override
        public boolean accept(NPC aggresive) {
            return aggresive != null
                    && aggresive.getInteracting() != null
                    && !aggresive.equals(Summoning.getFamiliar())
                    && aggresive.getInteracting().equals(Players.getLocal());
        }
    };

    public static final NPC getNearest() {
        return NPCs.getNearest(AGGRO_FILTER);
    }

    public static int getEXPPerHour() {
        final long runTime = System.currentTimeMillis() - Variables.startTime;
        Variables.xp = Skills.getExperience(Skills.STRENGTH) - Variables.startxp;
        return (int) ((Variables.xp * 3600000D) / runTime);
    }

    public interface Condition {
        public boolean validate();
    }

    public static boolean sleep(int MIN, int MAX, Condition CONDITION) {
        final int rnd = Random.nextInt(15, 21);
        final Timer t = new Timer(Random.nextInt(MIN, MAX));
        t.reset();
        while (t.isRunning()) {
            if (CONDITION.validate())
                return true;
            Task.sleep(rnd);
        }

        return false;
    }
}

