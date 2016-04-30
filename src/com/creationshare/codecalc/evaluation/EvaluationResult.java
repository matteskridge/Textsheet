package com.creationshare.codecalc.evaluation;

import java.text.DecimalFormat;

/**
 * Created by matt on 4/19/16.
 */
public class EvaluationResult {

	public String source;
	public Object obj;
	public static DecimalFormat decimalFormatter;

	static {
		decimalFormatter = new DecimalFormat("#.####");
	}

	public EvaluationResult() {
		this.obj = null;
		this.source = "";
	}

	public EvaluationResult(String source) {
		this.source = source;
	}

	public EvaluationResult(Object obj, String source) {
		this.obj = obj;
		this.source = source;
	}

	public Object getObject() {
		return obj;
	}

	public String toString() {
		if (obj instanceof Integer) {
			return "" + obj;
		} else if (obj instanceof Double) {
			Double num = (Double) obj;
			return decimalFormatter.format(num);
		} else if (obj == null) {
			return "";
		} else {
			return obj.toString();
		}
	}

	public double asNumber() {
		if (obj instanceof Integer) {
			return (Integer)obj;
		} else if (obj instanceof Double) {
			Double num = (Double) obj;
			return (Double)obj;
		} else if (obj instanceof Float) {
			Float num = (Float) obj;
			return (Float)obj;
		} else {
			return 0;
		}
	}
}
