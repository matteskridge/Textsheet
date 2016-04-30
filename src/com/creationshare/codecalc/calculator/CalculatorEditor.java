package com.creationshare.codecalc.calculator;

import com.creationshare.codecalc.CodeCalculatorWindow;
import com.creationshare.codecalc.components.CalculatorSplitPane;
import com.creationshare.codecalc.evaluation.CodeEvaluator;
import com.creationshare.codecalc.evaluation.EvaluationResult;
import com.creationshare.docframe.components.DocSyntaxArea;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Created by matt on 4/19/16.
 */
public class CalculatorEditor extends JPanel implements DocumentListener, ComponentListener, AdjustmentListener, KeyListener {

	private CodeCalculatorWindow win;
	private CodeEvaluator evaluator;
	private JSplitPane split;
	private RTextScrollPane pane;
	private RSyntaxTextArea area;
	private ResultPane results;
	private Timer runInvoker;
	public boolean changed;

	public CalculatorEditor(CodeCalculatorWindow win) {
		this.win = win;
		evaluator = new CodeEvaluator();

		setLayout(new BorderLayout());

		area = new DocSyntaxArea();
		area.setSyntaxEditingStyle(RSyntaxTextArea.SYNTAX_STYLE_GROOVY);
		area.addKeyListener(this);

		/*split = new CalculatorSplitPane();
		split.setLeftComponent(pane = new RTextScrollPane(area));
		split.setRightComponent(results = new ResultPane(this));
		split.setDividerLocation(200);
		add(split, BorderLayout.CENTER);*/

		add(pane = new RTextScrollPane(area), BorderLayout.CENTER);
		add(results = new ResultPane(this), BorderLayout.EAST);

		pane.setBorder(BorderFactory.createEmptyBorder());
		pane.setViewportBorder(null);
		pane.getVerticalScrollBar().addAdjustmentListener(this);

		area.getDocument().addDocumentListener(this);
		//split.addComponentListener(this);

		runInvoker = new Timer(15, e -> runCodeNow());
		runInvoker.setRepeats(false);

	}

	public void insertUpdate(DocumentEvent e) {
		runCode();
		changed = true;
	}

	public void removeUpdate(DocumentEvent e) {
		runCode();
		changed = true;
	}

	public void changedUpdate(DocumentEvent e) {
		runCode();
		changed = true;
	}

	private void runCode() {
		if (runInvoker.isRunning()) {
			runInvoker.restart();
		} else {
			runInvoker.start();
		}
	}

	private void runCodeNow() {
		CodeRunner runner = new CodeRunner();
		runner.run();
	}

	public void componentResized(ComponentEvent e) {
		//split.setDividerLocation(getWidth()-200);
	}

	public void componentMoved(ComponentEvent e) {
		//split.setDividerLocation(getWidth()-200);
	}

	public void componentShown(ComponentEvent e) {
		//split.setDividerLocation(getWidth()-200);
	}

	public void componentHidden(ComponentEvent e) {
		//split.setDividerLocation(getWidth()-200);
	}

	public void adjustmentValueChanged(AdjustmentEvent e) {
		results.syncScroll(pane);
	}

	public void optimize() {
		//split.setDividerLocation(getWidth()-200);
	}

	public String getText() {
		return area.getText();
	}

	public void setText(String text) {
		area.setText(text);
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {

	}

	public void keyReleased(KeyEvent e) {
		HotkeyManager.process(win, e);
	}

	public void moveCursorToTop() {
		area.setSelectionStart(0);
		area.setSelectionEnd(0);
	}

	public void fillPanel(JPanel panel) {
		//remove(split);
		add(panel, BorderLayout.CENTER);
	}

	public void syncScroll(JScrollPane sc) {
		pane.getVerticalScrollBar().setValue(sc.getVerticalScrollBar().getValue());
	}

	class CodeRunner extends SwingWorker<ArrayList<EvaluationResult>, Object> {

		public ArrayList<EvaluationResult> doInBackground() {
			return evaluator.execute(area.getText());
		}

		protected void done() {
			try {
				results.showResults(get());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

}
