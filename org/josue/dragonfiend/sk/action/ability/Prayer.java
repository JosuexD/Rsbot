package org.josue.dragonfiend.sk.action.ability;

import org.powerbot.game.api.wrappers.widget.WidgetChild;

import org.josue.dragonfiend.sk.action.Ability;
import org.josue.dragonfiend.sk.general.Completion;
import org.josue.dragonfiend.sk.tab.MainTabs;
import org.josue.dragonfiend.sk.tab.Tab;

public enum Prayer implements Ability {
    ;

    @Override
    public boolean show() {
        return false;
    }

    @Override
    public boolean isVisible() {
        return false;
    }

    @Override
    public boolean available() {
        return false;
    }

    public Tab getTab() {
        return MainTabs.PRAYER;
    }

    @Override
    public WidgetChild getChild() {
        return null;
    }

    @Override
    public Completion getChange() {
        return null;
    }

    @Override
    public int getAbilityId() {
        return 0;
    }

}
