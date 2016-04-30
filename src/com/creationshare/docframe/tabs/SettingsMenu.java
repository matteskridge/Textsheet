package com.creationshare.docframe.tabs;

import javax.swing.*;
import java.awt.*;
import java.util.UUID;

/**
 * Created by matt on 4/21/16.
 */
public class SettingsMenu extends JPanel {
	public String icon, name, uuid;

	public SettingsMenu(String icon, String name) {
		this.uuid = UUID.randomUUID().toString();
		this.icon = icon;
		this.name = name;

		setBackground(Color.white);
	}
}
