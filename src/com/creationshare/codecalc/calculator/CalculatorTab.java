package com.creationshare.codecalc.calculator;

import com.creationshare.codecalc.CodeCalculatorWindow;
import com.creationshare.docframe.tabs.DocumentTab;
import com.creationshare.docframe.utils.FileUtility;

import java.io.File;

/**
 * Created by matt on 4/19/16.
 */
public class CalculatorTab extends DocumentTab {
	public CalculatorEditor editor;

	public CalculatorTab(CodeCalculatorWindow win) {
		super(win);
		editor = new CalculatorEditor(win);
		component = editor;
		setTabName("Document");
	}

	public CalculatorTab(CodeCalculatorWindow win, File file) {
		super(win, file);

		editor = new CalculatorEditor(win);
		editor.setText(FileUtility.readFile(file));
		editor.moveCursorToTop();
		component = editor;

		setTabName(file.getName());
	}

	protected void writeToFile(File file) {
		FileUtility.writeFile(file, editor.getText());
	}

}
