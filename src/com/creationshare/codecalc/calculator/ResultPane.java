package com.creationshare.codecalc.calculator;

import com.creationshare.codecalc.evaluation.EvaluationResult;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.ArrayList;

/**
 * Created by matt on 4/19/16.
 */
public class ResultPane extends JPanel implements AdjustmentListener {

	private CalculatorEditor editor;
	private JPanel list;
	private JScrollPane scroll;

	public ResultPane(CalculatorEditor editor) {
		this.editor = editor;

		setPreferredSize(new Dimension(200,10));
		setLayout(new BorderLayout());

		list = new JPanel();
		list.setOpaque(false);
		list.setLayout(new BoxLayout(list, BoxLayout.Y_AXIS));
		list.setBorder(BorderFactory.createEmptyBorder(1,0,0,0));

		JPanel center = new JPanel();
		center.setBackground(Color.decode("#EEEEEE"));
		center.setLayout(new BorderLayout());
		center.add(list, BorderLayout.NORTH);

		scroll = new JScrollPane(center);
		scroll.setBorder(BorderFactory.createEmptyBorder());
		scroll.setViewportBorder(null);
		scroll.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		scroll.getVerticalScrollBar().addAdjustmentListener(this);
		scroll.getVerticalScrollBar().setUnitIncrement(10);
		add(scroll, BorderLayout.CENTER);
	}

	public void showResults(ArrayList<EvaluationResult> results) {
		list.removeAll();

		for (EvaluationResult result: results) {
			list.add(new ResultItem(result));
		}

		list.revalidate();
	}

	public void syncScroll(RTextScrollPane pane) {
		scroll.getVerticalScrollBar().setValue(pane.getVerticalScrollBar().getValue());
	}

	public void adjustmentValueChanged(AdjustmentEvent e) {
		editor.syncScroll(scroll);
	}

	class ResultItem extends JPanel {
		public ResultItem(EvaluationResult result) {
			setLayout(new BorderLayout());
			setPreferredSize(new Dimension(150, 15));

			JLabel label = new JLabel();
			label.setFont(label.getFont().deriveFont(11f));
			label.setText(result.toString());
			add(label);
		}
	}
}
