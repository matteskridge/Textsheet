package com.creationshare.markdown;

import com.creationshare.docframe.DocumentWindow;
import com.creationshare.docframe.TabbedWindow;
import com.creationshare.docframe.ProductInformation;
import com.creationshare.docframe.utils.IconLabel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by matt on 4/21/16.
 */
public class MarkdownWindow extends DocumentWindow {
	public static void main(String[] args) {
		MarkdownWindow win = new MarkdownWindow();
		win.setVisible(true);
	}

	public MarkdownWindow() {

	}

	public ProductInformation getProductInformation() {
		ProductInformation info = new ProductInformation();
		info.code = "ME";
		info.name = "Markdown Editor";
		info.color = Color.decode("#FF5C5C");
		return info;
	}

	public void openFile(File file) {

	}

	public void openSettings() {

	}
}
