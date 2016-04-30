package com.creationshare.text;

import com.creationshare.docframe.DocumentWindow;
import com.creationshare.docframe.ProductInformation;

import java.awt.*;
import java.io.File;

/**
 * Created by matt on 4/21/16.
 */
public class TextEditorWindow extends DocumentWindow {
	public static void main(String[] args) {
		TextEditorWindow win = new TextEditorWindow();
		win.setVisible(true);
	}

	public TextEditorWindow() {
		registerTab(new CodeTab(this));
	}

	public ProductInformation getProductInformation() {
		ProductInformation info = new ProductInformation();
		info.code = "TE";
		info.name = "Text Editor";
		info.color = Color.decode("#C75CFF");
		return info;
	}

	public void addTab() {
		registerTab(new CodeTab(this));
	}

	public void openFile(File file) {
		registerTab(new CodeTab(this, file));
	}

	public void openSettings() {

	}
}
