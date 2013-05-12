package org.josue.jdrags.method;


import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.methods.tab.Summoning;
import org.powerbot.game.api.util.Filter;
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

    public static long startTime = -1;
    public static int startxp = 0;
    public static int xp;

    public static int getEXPperHour() {
        final long runTime = System.currentTimeMillis() - startTime;
        xp = Skills.getExperience(Skills.STRENGTH) - startxp;
        return (int) ((xp * 3600000D) / runTime);
    }
}
