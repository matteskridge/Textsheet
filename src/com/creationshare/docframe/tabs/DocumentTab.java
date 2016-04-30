package com.creationshare.docframe.tabs;

import com.creationshare.codecalc.CodeCalculatorWindow;
import com.creationshare.docframe.TabbedWindow;
import com.creationshare.docframe.utils.FileSelector;
import com.creationshare.docframe.utils.FileUtility;

import java.io.File;

/**
 * Created by matt on 4/21/16.
 */
public abstract class DocumentTab extends BasicTab {
	public File file;

	public DocumentTab(TabbedWindow win) {
		super(win);
	}

	public DocumentTab(TabbedWindow win, File file) {
		this(win);
		this.file = file;
		setTabName(file.getName());
	}

	public void save(TabbedWindow win) {
		if (file == null) {
			FileSelector.save(win, file, this);
		} else {
			save(file);
			changed = false;
		}
	}

	public void save(File file) {
		writeToFile(file);
	}

	public void saveAs(TabbedWindow win) {
		FileSelector.save(win, file, this);
	}

	protected abstract void writeToFile(File file);

}
