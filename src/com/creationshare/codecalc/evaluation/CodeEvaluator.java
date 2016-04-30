package com.creationshare.codecalc.evaluation;

import com.creationshare.docframe.utils.FileUtility;
import groovy.lang.GroovyShell;
import org.codehaus.groovy.control.CompilerConfiguration;

import java.util.ArrayList;

/**
 * Created by matt on 4/19/16.
 */
public class CodeEvaluator {

	private GroovyShell shell;
	private String api;

	public static ArrayList<EvaluationResult> currentResultSet;

	public CodeEvaluator() {
		try {
			CompilerConfiguration config = new CompilerConfiguration();
			config.setScriptBaseClass("com.creationshare.codecalc.evaluation.api.CalculatorAPI");

			shell = new GroovyShell(config);
			shell.evaluate(FileUtility.getInstallFile("api/calculator_constants.gy"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public ArrayList<EvaluationResult> execute(String code) {

		String[] lines = code.split("\n");
		ArrayList<EvaluationResult> results = new ArrayList<>();
		currentResultSet = results;

		for (int i = 0; i < lines.length; i++) {
			if (lines[i].equals("")) {
				results.add(new EvaluationResult(lines[i]));
				continue;
			}

			String line = CodePreprocessor.process(lines[i]);

			String script = "above = line_"+(i+1)+" = l"+(i+1)+" = "+line;

			try {
				results.add(new EvaluationResult(shell.evaluate(script), lines[i]));
			} catch (Exception ex) {
				results.add(new EvaluationResult(lines[i]));
			}
		}

		return results;
	}
}
