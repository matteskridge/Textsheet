package com.creationshare.docframe.components;

import com.creationshare.docframe.utils.DoctypeDetector;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import java.io.File;

/**
 * Created by matt on 4/21/16.
 */
public class DocSyntaxArea extends RSyntaxTextArea {
	public DocSyntaxArea() {
		setLineWrap(false);
		setHighlightCurrentLine(false);
	}

	public void moveCursorToTop() {
		setSelectionStart(0);
		setSelectionEnd(0);
	}

	public void detectDoctype(File file) {
		String doctype = DoctypeDetector.detectDoctype(file);
		if (doctype != null) {
			setSyntaxEditingStyle(doctype);
		} else {
			setSyntaxEditingStyle("text/plain");
		}
	}
}
