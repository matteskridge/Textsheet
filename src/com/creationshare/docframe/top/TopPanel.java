package com.creationshare.docframe.top;

import com.creationshare.docframe.utils.IconLabel;
import com.creationshare.docframe.tabs.BasicTab;
import com.creationshare.docframe.TabbedWindow;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;

/**
 * Created by matt on 4/19/16.
 */
public class TopPanel extends JPanel {

	private int SIZE = 14;

	private ArrayList<TabItem> hiddenTabs = new ArrayList<>();
	private TabbedWindow win;
	private TabPanel tabs;
	private ToolPanel tools;
	private IconLabel saveIcon;
	private IconLabel settingsIcon;

	private MenuButton menu;
	private AddButton add;

	public TopPanel(TabbedWindow win) {
		this.win = win;

		setBackground(Color.white);
		setBorder(new CompoundBorder(
				BorderFactory.createMatteBorder(0,0,1,0,Color.decode("#D3D3D3")),
				BorderFactory.createEmptyBorder(0,8,0,5)
		));
		setLayout(new BorderLayout());

		add(new LogoPanel(), BorderLayout.WEST);
		add(tabs = new TabPanel(), BorderLayout.CENTER);
		add(tools = new ToolPanel(), BorderLayout.EAST);
	}

	public void updateTabs(ArrayList<BasicTab> list) {
		tabs.removeAll();

		for (BasicTab t: list) {
			tabs.add(t.tab);
		}

		tabs.add(menu = new MenuButton());
		tabs.add(add = new AddButton());

		menu.setVisible(false);

		Timer timer = new Timer(15, e -> tabs.recalculateLayout());
		timer.setRepeats(false);
		timer.start();
	}

	public IconLabel addMenuAction(String action, String icon, String name, ActionListener listener) {
		ToolItem item = new ToolItem(action, icon, name, listener);
		tools.add(item);
		return item;
	}

	class LogoPanel extends JPanel {
		public LogoPanel() {
			setLayout(new BorderLayout());
			setOpaque(false);

			JLabel label = new JLabel(win.info.code);
			label.setForeground(win.info.color);
			label.setFont(label.getFont().deriveFont(20f));
			add(label);
		}
	}

	class TabPanel extends JPanel implements ComponentListener {

		public TabPanel() {
			setOpaque(false);
			setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
			setBorder(BorderFactory.createEmptyBorder(5,8,5,8));
			addComponentListener(this);
		}

		public void componentResized(ComponentEvent e) {
			recalculateLayout();
		}

		public void componentMoved(ComponentEvent e) {

		}

		public void componentShown(ComponentEvent e) {

		}

		public void componentHidden(ComponentEvent e) {

		}

		private void recalculateLayout() {
			if (getComponentCount() <= 3) {
				menu.setVisible(false);
				revalidate();
				repaint();
				return;
			}

			hiddenTabs = new ArrayList<>();

			int containerWidth = getWidth();
			int contentWidth = menu.getWidth() + add.getWidth() + 20;

			for (int i = 0; i < getComponentCount()-2; i++) {
				contentWidth += getComponent(i).getWidth();

				if (containerWidth < contentWidth && getComponent(i) instanceof TabItem) {
					getComponent(i).setVisible(false);
					hiddenTabs.add((TabItem) getComponent(i));
				} else {
					getComponent(i).setVisible(true);
				}
			}

			//menu.setVisible(false);
			menu.setVisible(hiddenTabs.size() > 0);
			revalidate();
			repaint();
		}
	}

	class AddButton extends IconLabel implements ActionListener {
		public AddButton() {
			super("ea0a", 9, Color.decode("#AAAAAA"), Color.decode("#555555"), Color.decode("#000000"));
			addActionListener(this);
			setBorder(BorderFactory.createEmptyBorder(2,7,0,0));
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}

		public void actionPerformed(ActionEvent e) {
			win.addTab();
		}
	}

	class MenuButton extends IconLabel implements ActionListener {
		public MenuButton() {
			super("e9bf", 12, Color.decode("#AAAAAA"), Color.decode("#555555"), Color.decode("#000000"));
			addActionListener(this);
			setBorder(BorderFactory.createEmptyBorder(2,7,0,0));
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}

		public void actionPerformed(ActionEvent e) {
			JPopupMenu menu = new JPopupMenu();

			for (TabItem item: hiddenTabs) {
				JMenuItem mi = new JMenuItem(item.getText());
				mi.addActionListener(ev -> openHiddenTab(item.getTab()));
				menu.add(mi);
			}

			menu.show(this, 0, getHeight());
		}

		private void openHiddenTab(BasicTab tab) {
			/*int tabIndex = win.getTabs().indexOf(tab);
			int insertIndex = win.getTabs().indexOf(hiddenTabs.get(0))-1;

			win.getTabs().remove(tabIndex);
			win.getTabs().add(insertIndex, tab);*/
			win.showTab(tab);
		}
	}

	class ToolPanel extends JPanel {

		public ToolPanel() {
			setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
			setBorder(BorderFactory.createEmptyBorder(10,0,0,5));
			setOpaque(false);
		}
	}

	class ToolItem extends IconLabel {
		public ToolItem(String action, String icon, String name, ActionListener listener) {
			super(icon, SIZE, Color.decode("#AAAAAA"), Color.decode("#555555"), Color.decode("#000000"));
			setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			setToolTipText(name);
			setActionCommand(action);
			addActionListener(listener);
		}
	}

}
