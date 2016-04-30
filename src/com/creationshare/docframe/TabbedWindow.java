package com.creationshare.docframe;

import com.creationshare.docframe.tabs.BasicTab;
import com.creationshare.docframe.utils.IconLabel;
import com.creationshare.docframe.top.TopPanel;
import com.creationshare.docframe.utils.OSOptimizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by matt on 4/21/16.
 */
public abstract class TabbedWindow extends JFrame {

	protected ArrayList<BasicTab> tabs = new ArrayList<>();
	private TopPanel top;
	private TabCenter tabCenter;
	public final ProductInformation info;

	public TabbedWindow() {
		info = getProductInformation();

		OSOptimizer.optimize(this);

		add(top = new TopPanel(this), BorderLayout.NORTH);
		add(tabCenter = new TabCenter(), BorderLayout.CENTER);

		setTitle(info.name);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(300,200));
		setSize(600, 400);
		setLocationRelativeTo(null);
	}

	public abstract ProductInformation getProductInformation();

	public IconLabel addMenuAction(String action, String icon, String name, ActionListener listener) {
		return top.addMenuAction(action, icon, name, listener);
	}

	public void registerTab(BasicTab tab) {
		boolean empty = tabCenter.getComponentCount() == 0;
		tabCenter.add(tab.component, tab.uuid);
		tabs.add(tab);
		showTab(tab);
	}

	public void showTab(BasicTab tab) {
		for (BasicTab t: tabs) t.selected = false;
		tab.selected = true;
		tabCenter.card.show(tabCenter, tab.uuid);
		top.updateTabs(tabs);
	}

	public void removeTab(BasicTab tab) {
		// Show the confirm dialog if needed
		if (tab.changed) {
			int value = JOptionPane.showConfirmDialog(this, "You have unsaved changes. Close this file?");
			if (value != JOptionPane.YES_OPTION) {
				return;
			}
		}

		boolean empty = tabCenter.getComponentCount() == 1;

		// Change the selected tab
		int nextIndex = tabs.indexOf(tab)+1;
		int previousIndex = tabs.indexOf(tab)-1;
		if (nextIndex < tabs.size()) showTab(tabs.get(nextIndex));
		else if (previousIndex >= 0) showTab(tabs.get(previousIndex));

		// De-register the tab
		tabs.remove(tab);
		tabCenter.remove(tab.component);

		top.updateTabs(tabs);



		if (empty) {
			setVisible(false);
			System.exit(0);
		} else {

		}
	}

	public BasicTab getSelectedTab() {
		for (BasicTab t: tabs) {
			if (t.selected) return t;
		}
		return null;
	}

	public void addTab() {

	}

	public ArrayList<BasicTab> getTabs() {
		return tabs;
	}

	class TabCenter extends JPanel {
		private CardLayout card;

		public TabCenter() {
			setLayout(card = new CardLayout());
		}
	}
}
