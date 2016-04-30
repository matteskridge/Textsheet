package com.creationshare.docframe.utils;

import com.creationshare.codecalc.calculator.CalculatorTab;
import com.creationshare.docframe.TabbedWindow;
import com.creationshare.docframe.tabs.DocumentTab;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by matt on 4/20/16.
 */
public class FileSelector extends JFrame implements ActionListener {

	private Mode mode;
	private FileSelectionListener listener;
	private JFileChooser chooser;
	private JTextField fileName;

	public FileSelector(JFrame win, Mode mode, FileSelectionListener listener) {
		this.listener = listener;
		this.mode = mode;

		setLayout(new BorderLayout());

		fileName = new JTextField(20);
		fileName.setText("untitled.tcf");

		JPanel top = new JPanel();
		top.setLayout(new FlowLayout());
		top.add(new JLabel("File name: "));
		top.add(fileName);
		top.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.decode("#AAAAAA")));
		top.setVisible(mode == Mode.Save);
		add(top, BorderLayout.NORTH);

		chooser = new JFileChooser();
		chooser.setControlButtonsAreShown(false);
		add(chooser, BorderLayout.CENTER);

		JButton finish = new JButton((mode == Mode.Open) ? "Open" : "Save");
		JButton cancel = new JButton("Cancel");

		JPanel submit = new JPanel();
		submit.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		submit.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));

		if (OsCheck.isMac()) {
			submit.add(cancel);
			submit.add(finish);
		} else {
			submit.add(finish);
			submit.add(cancel);
		}

		cancel.setActionCommand("cancel");
		cancel.addActionListener(this);

		finish.setActionCommand("finish");
		finish.addActionListener(this);

		add(submit, BorderLayout.SOUTH);

		setTitle("Select a File");
		setSize(500, 350);
		setLocationRelativeTo(win);
	}

	public static void open(TabbedWindow win, FileSelectionListener listen) {
		FileSelector sel = new FileSelector(win, Mode.Open, listen);
		sel.setVisible(true);
	}

	public static void save(TabbedWindow win, File file, DocumentTab tab) {
		FileSelector sel = new FileSelector(win, Mode.Save, new FileSelectionListener() {
			public void fileSelected(File file) {
				tab.save(file);
				tab.changed = false;
				tab.file = file;
				tab.tab.setText(file.getName());
				tab.name = file.getName();
			}
		});

		if (file != null) {
			sel.setSelectedFile(file);
		}

		sel.setVisible(true);
	}

	private void setSelectedFile(File file) {
		if (mode == Mode.Save) {
			chooser.setSelectedFile(file);
			fileName.setText(file.getName());
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("cancel")) {
			setVisible(false);
		} else if (e.getActionCommand().equals("finish")) {
			if (mode == Mode.Open) {
				File file = chooser.getSelectedFile();
				listener.fileSelected(file);
			} else if (mode == Mode.Save) {
				File file = chooser.getCurrentDirectory();
				listener.fileSelected(new File(file.getAbsolutePath()+"/"+fileName.getText()));
			}
			setVisible(false);
		}
	}

	public interface FileSelectionListener {
		void fileSelected(File file);
	}

	enum Mode { Save, Open }
}
