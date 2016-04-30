package com.creationshare.docframe.top;

import com.creationshare.docframe.utils.IconLabel;
import com.creationshare.docframe.tabs.BasicTab;
import com.creationshare.docframe.TabbedWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by matt on 4/19/16.
 */
public class TabItem extends JPanel implements MouseListener {

	private TabbedWindow win;
	private BasicTab tab;
	private JLabel title;

	public TabItem(TabbedWindow win, BasicTab tab) {
		this.win = win;
		this.tab = tab;

		setOpaque(false);
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(5,12,5,12));
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		title = new JLabel("Document");
		add(title, BorderLayout.CENTER);

		IconLabel icon = new IconLabel("ea0f", 9, Color.decode("#AAAAAA"), Color.decode("#555555"), Color.decode("#000000"));
		icon.setBorder(BorderFactory.createEmptyBorder(3,5,0,0));
		icon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				win.removeTab(tab);
			}
		});
		add(icon, BorderLayout.EAST);

		addMouseListener(this);
	}

	public void paintComponent(Graphics g) {
		if (tab.selected) {
			g.setColor(Color.decode("#F4F4F4"));
			g.fillRect(0, 0, getWidth(), getHeight());
		} else {
			g.setColor(Color.decode("#FFFFFF"));
			g.fillRect(0, 0, getWidth(), getHeight());
		}

		super.paintComponent(g);
	}

	public void mouseClicked(MouseEvent e) {
		win.showTab(tab);
	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void setText(String text) {
		title.setText(text);
	}

	public String getText() {
		return title.getText();
	}

	public BasicTab getTab() {
		return tab;
	}
}
