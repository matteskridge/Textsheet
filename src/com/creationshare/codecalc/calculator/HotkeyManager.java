package com.creationshare.codecalc.calculator;

import com.creationshare.codecalc.CodeCalculatorWindow;

import java.awt.event.KeyEvent;

/**
 * Created by matt on 4/21/16.
 */
public class HotkeyManager {
	public static void process(CodeCalculatorWindow win, KeyEvent e) {
		if (e.getKeyCode() == 83 && e.isMetaDown()) {
			win.actionSave();
		} else if (e.getKeyCode() == 79 && e.isMetaDown()) {
			win.actionOpen();
		} else if (e.getKeyChar() == 's' && e.isControlDown()) {
			win.actionSave();
		}
	}
}
