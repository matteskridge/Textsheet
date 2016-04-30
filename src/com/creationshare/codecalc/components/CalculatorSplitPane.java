package com.creationshare.codecalc.components;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.awt.*;

/**
 * Created by matt on 4/19/16.
 */
public class CalculatorSplitPane extends JSplitPane {
	public CalculatorSplitPane() {
		setDividerSize(8);
		setUI(new BasicSplitPaneUI() {
			public BasicSplitPaneDivider createDefaultDivider() {
				return new BasicSplitPaneDivider(this) {
					public void setBorder(Border b) {
					}

					public void paint(Graphics g) {
						super.paint(g);
						g.setColor(Color.decode("#EEEEEE"));
						g.fillRect(0, 0, getWidth(), getHeight());
						g.setColor(Color.decode("#CCCCCC"));
						g.fillRect(0, 0, 1, getSize().height);
					}
				};
			}
		});
		setOneTouchExpandable(true);
		setBorder(null);
	}
}
