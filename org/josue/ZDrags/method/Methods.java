package org.josue.ZDrags.method;


import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.wrappers.Entity;
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
}
