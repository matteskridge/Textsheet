package com.creationshare.docframe.tabs;

import com.creationshare.codecalc.CodeCalculatorWindow;
import com.creationshare.docframe.TabbedWindow;
import com.creationshare.docframe.utils.IconLabel;
import com.creationshare.docframe.tabs.BasicTab;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Created by matt on 4/21/16.
 */
public class SettingsTab extends BasicTab {

	private SettingsPanel pane;
	private ArrayList<TabItem> tabs = new ArrayList<>();

	public SettingsTab(TabbedWindow win) {
		super(win);
		setTabName("Preferences");
		pane = new SettingsPanel();
		component = pane;
	}

	public void addTab(SettingsMenu menu) {
		TabItem ti = new TabItem(menu.name, menu.icon, menu.uuid);
		tabs.add(ti);

		if (tabs.size() == 1) {
			ti.setSelected(true);
		} else {
			ti.setSelected(false);
		}

		pane.tabs.add(ti);
		pane.body.add(menu, menu.uuid);
	}

	class SettingsPanel extends JPanel {
		private SettingsTabs tabs;
		private SettingsBody body;

		public SettingsPanel() {
			setLayout(new BorderLayout());
			add(tabs = new SettingsTabs(), BorderLayout.NORTH);
			add(body = new SettingsBody(), BorderLayout.CENTER);
		}
	}

	class SettingsTabs extends JPanel {
		public SettingsTabs() {
			setLayout(new FlowLayout(FlowLayout.LEFT));
			setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.decode("#CCCCCC")));
			setBackground(Color.decode("#F2F2F2"));
		}
	}

	class SettingsBody extends JPanel {
		private CardLayout card;

		public SettingsBody() {
			setBackground(Color.decode("#FFFFFF"));
			setLayout(card = new CardLayout());
		}
	}

	class TabItem extends JPanel implements MouseListener {
		private IconLabel icon;
		private JLabel label;
		private boolean selected = false;
		private String uuid;

		public TabItem(String name, String icn, String uuid) {
			this.uuid = uuid;

			setLayout(new BorderLayout());
			setOpaque(false);
			setBorder(BorderFactory.createEmptyBorder(3,6,3,6));
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			addMouseListener(this);

			icon = new IconLabel(icn, 10);
			icon.setHoverEnabled(false);
			icon.setBorder(BorderFactory.createEmptyBorder(0,0,0,7));
			add(icon, BorderLayout.WEST);

			label = new JLabel(name);
			add(label, BorderLayout.CENTER);
		}

		public void setSelected(boolean selected) {
			this.selected = selected;

			if (selected) {
				icon.setForeground(Color.decode("#000000"));
				label.setForeground(Color.decode("#000000"));
				//setBackground(Color.decode("#EEEEEE"));
			} else {
				icon.setForeground(Color.decode("#888888"));
				label.setForeground(Color.decode("#888888"));
				//setBackground(Color.decode("#F8F8F8"));
			}

			icon.repaint();
			label.repaint();
		}

		public void mouseClicked(MouseEvent e) {
			pane.body.card.show(pane.body, uuid);
			for (TabItem ti: tabs) ti.setSelected(false);
			setSelected(true);
		}

		public void mousePressed(MouseEvent e) {

		}

		public void mouseReleased(MouseEvent e) {

		}

		public void mouseEntered(MouseEvent e) {

		}

		public void mouseExited(MouseEvent e) {

		}
	}
}
