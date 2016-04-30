package com.creationshare.docframe.tabs;

import com.creationshare.docframe.TabbedWindow;
import com.creationshare.docframe.top.TabItem;

import javax.swing.*;
import java.awt.*;
import java.util.UUID;

/**
 * Created by matt on 4/21/16.
 */
public class BasicTab extends JPanel {
	public String name;
	public String uuid;
	public Component component;
	public TabItem tab;
	public boolean selected;
	public boolean changed;

	public BasicTab(TabbedWindow win) {
		uuid = UUID.randomUUID().toString();
		tab = new TabItem(win, this);
		setTabName("Document");
	}

	public void optimize() {

	}

	public void setTabName(String name) {
		this.name = name;
		tab.setText(name);
	}
}
