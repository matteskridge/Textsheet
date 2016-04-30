package com.creationshare.text;

import com.creationshare.docframe.DocumentWindow;
import com.creationshare.docframe.components.DocSyntaxArea;
import com.creationshare.docframe.tabs.DocumentTab;
import com.creationshare.docframe.utils.FileUtility;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.io.File;

/**
 * Created by matt on 4/21/16.
 */
public class CodeTab extends DocumentTab {

	private CodeEditor editor;

	public CodeTab(DocumentWindow win) {
		super(win);
		editor = new CodeEditor();
		component = editor;
	}

	public CodeTab(DocumentWindow win, File file) {
		super(win, file);
		editor = new CodeEditor();
		component = editor;
		editor.loadFile(file);
	}

	class CodeEditor extends JPanel implements DocumentListener {
		private DocSyntaxArea area;
		private RTextScrollPane scroll;

		public CodeEditor() {
			setLayout(new BorderLayout());

			area = new DocSyntaxArea();
			area.setHighlightCurrentLine(true);
			area.setCurrentLineHighlightColor(Color.decode("#FAFAFA"));
			area.getDocument().addDocumentListener(this);

			scroll = new RTextScrollPane(area);
			scroll.setBorder(BorderFactory.createEmptyBorder());
			scroll.setViewportBorder(null);

			add(scroll, BorderLayout.CENTER);
		}

		public void loadFile(File file) {
			area.getDocument().removeDocumentListener(this);
			area.setText(FileUtility.readFile(file));
			area.moveCursorToTop();
			area.detectDoctype(file);
			area.getDocument().addDocumentListener(this);
		}

		public void insertUpdate(DocumentEvent e) {
			changed = true;
		}

		public void removeUpdate(DocumentEvent e) {
			changed = true;
		}

		public void changedUpdate(DocumentEvent e) {
			changed = true;
		}
	}

	protected void writeToFile(File file) {
		FileUtility.writeFile(file, editor.area.getText());
	}
}
