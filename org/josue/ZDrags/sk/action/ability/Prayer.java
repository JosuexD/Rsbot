package org.josue.ZDrags.sk.action.ability;

import org.powerbot.game.api.wrappers.widget.WidgetChild;

import org.josue.ZDrags.sk.action.Ability;
import org.josue.ZDrags.sk.general.Completion;
import org.josue.ZDrags.sk.tab.MainTabs;
import org.josue.ZDrags.sk.tab.Tab;

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
