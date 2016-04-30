package com.creationshare.codecalc;

import com.creationshare.codecalc.calculator.CalculatorTab;
import com.creationshare.docframe.DocumentWindow;
import com.creationshare.docframe.ProductInformation;
import com.creationshare.docframe.tabs.SettingsTab;
import com.creationshare.docframe.utils.IconLabel;
import com.creationshare.docframe.tabs.BasicTab;
import com.creationshare.docframe.TabbedWindow;

import java.awt.*;
import java.io.File;

/**
 * Created by matt on 4/19/16.
 */
public class CodeCalculatorWindow extends DocumentWindow {

	private IconLabel saveIcon, openIcon, settingsIcon;

	public static void main(String[] args) {
		CodeCalculatorWindow win = new CodeCalculatorWindow();
		win.setVisible(true);
		win.optimize();
	}

	public CodeCalculatorWindow() {
		registerTab(new CalculatorTab(this));
	}

	public ProductInformation getProductInformation() {
		ProductInformation info = new ProductInformation();
		info.code = "TS";
		info.name = "Textsheet";
		info.color = Color.decode("#5A62FF");
		return info;
	}

	public void addTab() {
		registerTab(new CalculatorTab(this));
	}

	public void openFile(File file) {
		registerTab(new CalculatorTab(this, file));
	}

	public void openSettings() {
		registerTab(new CalculatorSettingsTab(this));
	}

	public void optimize() {
		for (BasicTab tab: tabs) {
			if (tab instanceof CalculatorTab) {
				((CalculatorTab) tab).editor.optimize();
			}
		}
	}
}
