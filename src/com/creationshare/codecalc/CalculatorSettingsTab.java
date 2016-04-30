package com.creationshare.codecalc;

import com.creationshare.docframe.TabbedWindow;
import com.creationshare.docframe.tabs.SettingsMenu;
import com.creationshare.docframe.tabs.SettingsTab;

/**
 * Created by matt on 4/21/16.
 */
public class CalculatorSettingsTab extends SettingsTab {

	private SettingsMenu editor, formulas, advanced;

	public CalculatorSettingsTab(TabbedWindow win) {
		super(win);

		editor = new SettingsMenu("ea5f", "Editor");
		addTab(editor);

		formulas = new SettingsMenu("ea67", "Formulas");
		addTab(formulas);

		advanced = new SettingsMenu("e997", "Advanced");
		addTab(advanced);
	}
}
