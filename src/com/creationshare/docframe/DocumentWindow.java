package com.creationshare.docframe;

import com.creationshare.codecalc.calculator.CalculatorTab;
import com.creationshare.docframe.tabs.DocumentTab;
import com.creationshare.docframe.tabs.SettingsTab;
import com.creationshare.docframe.tabs.BasicTab;
import com.creationshare.docframe.utils.FileSelector;
import com.creationshare.docframe.utils.IconLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by matt on 4/21/16.
 */
public abstract class DocumentWindow extends TabbedWindow implements ActionListener {

	private IconLabel saveIcon, openIcon, settingsIcon;

	public DocumentWindow() {
		saveIcon = addMenuAction("save", "e962", "Save", this);
		openIcon = addMenuAction("open", "e930", "Open", this);
		settingsIcon = addMenuAction("properties", "e991", "Settings", this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("open")) {
			actionOpen();
		} else if (e.getActionCommand().equals("properties")) {
			actionProperties();
		} else if (e.getActionCommand().equals("save")) {
			actionSave();
		} else if (e.getActionCommand().equals("save-as")) {
			actionSave();
		} else if (e.getActionCommand().equals("save-copy")) {
			actionSave();
		} else if (e.getActionCommand().equals("rename")) {
			actionSave();
		} else if (e.getActionCommand().equals("preferences")) {
			openSettings();
		} else if (e.getActionCommand().equals("help")) {
			actionHelp();
		}
	}

	public void registerTab(BasicTab tab) {
		BasicTab previousTab = getSelectedTab();
		int count = tabs.size();

		super.registerTab(tab);

		if (previousTab != null && !(tab instanceof SettingsTab) && previousTab instanceof DocumentTab && !previousTab.changed) {
			if (!(tab instanceof DocumentTab && ((DocumentTab)tab).file == null) && ((DocumentTab)previousTab).file == null)
				removeTab(previousTab);
		}
	}

	public void actionSave() {
		saveIcon.flash(Color.decode("#B89F74"));
		if (getSelectedTab() instanceof CalculatorTab) {
			((CalculatorTab)getSelectedTab()).save(this);
		}
	}

	public void actionOpen() {
		openIcon.flash(Color.decode("#B89F74"));
		FileSelector.open(this, file -> openFile(file));
	}

	public abstract void openFile(File file);
	public abstract void openSettings();

	private void actionProperties() {
		String[][] options = null;
		int width = 140;

		if (getSelectedTab() instanceof SettingsTab) {
			options = new String[][] {
					{"help", "Help Files"},
			};
			width = 110;
		} else if (getSelectedTab() instanceof DocumentTab) {
			options = new String[][] {
					{"save", "Save" + ((((DocumentTab)getSelectedTab()).file == null) ? "..." : "")},
					{"save-as", "Save As..."},
					{"save-copy", "Save a Copy..."},
					{},
					{"rename", "Rename file..."},
					{},
					{"preferences", "Preferences"},
					{"help", "Help Files"},
			};
		} else {
			options = new String[][] {
					{"preferences", "Preferences"},
					{"help", "Help Files"},
			};
		}

		JPopupMenu menu = new JPopupMenu();

		for (String[] item: options) {
			if (item.length == 0) {
				menu.addSeparator();
				continue;
			}
			JMenuItem m = new JMenuItem(item[1]);
			m.setActionCommand(item[0]);
			m.addActionListener(this);
			menu.add(m);
		}

		menu.show(settingsIcon, settingsIcon.getWidth()-width, settingsIcon.getHeight());
	}

	public void actionHelp() {

	}
}
