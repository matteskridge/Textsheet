package com.creationshare.docframe.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by matt on 4/20/16.
 */
public class IconLabel extends JLabel implements MouseListener {

	private boolean hoverEnabled = true;
	private String actionCommand;
	private ArrayList<ActionListener> listeners = new ArrayList();
	private Color color, highlightColor, pressColor;

	public IconLabel(String code) {
		this(code, 16, Color.black, Color.black, Color.black);
	}

	public IconLabel(String code, int size) {
		this(code, size, Color.black, Color.black, Color.black);
	}

	public IconLabel(String code, int size, Color color, Color highlightColor, Color pressColor) {

		this.color = color;
		this.highlightColor = highlightColor;
		this.pressColor = pressColor;

		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, FileUtility.getInstallFile("fonts/IcoMoon-Free.ttf")));
		} catch (IOException|FontFormatException e) {
			e.printStackTrace();
		}

		char icon = (char)Integer.parseInt(code, 16);
		setFont(new Font("IcoMoon-Free", Font.PLAIN, size));
		setForeground(color);
		setText(""+icon);

		addMouseListener(this);
	}

	public void mouseClicked(MouseEvent e) {
		for (ActionListener listen: listeners) {
			listen.actionPerformed(new ActionEvent(this, 0, actionCommand));
		}
	}

	public void mousePressed(MouseEvent e) {
		if (hoverEnabled) {
			highlight(pressColor);
		}
	}

	public void mouseReleased(MouseEvent e) {
		if (hoverEnabled) {
			if (isMouseWithinComponent(this)) {
				highlight(highlightColor);
			} else {
				highlight(color);
			}
		}
	}

	public void mouseEntered(MouseEvent e) {
		if (hoverEnabled) {
			highlight(highlightColor);
		}
	}

	public void mouseExited(MouseEvent e) {
		if (hoverEnabled) {
			highlight(color);
		}
	}

	private void highlight(Color color) {
		setForeground(color);
		repaint();
	}

	public static boolean isMouseWithinComponent(Component c) {
		Point mousePos = MouseInfo.getPointerInfo().getLocation();
		Rectangle bounds = c.getBounds();
		bounds.setLocation(c.getLocationOnScreen());
		return bounds.contains(mousePos);
	}

	public void addActionListener(ActionListener listener) {
		listeners.add(listener);
	}

	public void setActionCommand(String action) {
		this.actionCommand = action;
	}

	public void removeActionListener(ActionListener listener) {
		listeners.remove(listener);
	}

	public void setHoverEnabled(boolean hoverEnabled) {
		this.hoverEnabled = hoverEnabled;
	}

	public void flash(Color color) {
		setForeground(color);
		Timer timer = new Timer(500, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setForeground(Color.decode("#AAAAAA"));
			}
		});
		timer.setRepeats(false);
		timer.start();
	}
}
