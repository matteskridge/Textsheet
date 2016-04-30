package com.creationshare.codecalc.evaluation.api;

import com.creationshare.codecalc.evaluation.CodeEvaluator;
import groovy.lang.Script;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by matt on 4/20/16.
 */
public abstract class CalculatorAPI extends Script {
	public double sin(double num) {
		return Math.sin(num);
	}

	public double cos(double num) {
		return Math.cos(num);
	}

	public double tan(double num) {
		return Math.tan(num);
	}

	public double asin(double num) {
		return Math.asin(num);
	}

	public double acos(double num) {
		return Math.acos(num);
	}

	public double atan(double num) {
		return Math.atan(num);
	}

	public double atan2(double num, double num2) {
		return Math.atan2(num, num2);
	}

	public double circle_area(double radius) {
		return Math.PI * Math.pow(radius, 2);
	}

	public double circle_circumference(double radius) {
		return 2 * Math.PI * radius;
	}

	public double sum() {
		return sum(1, CodeEvaluator.currentResultSet.size()+1);
	}

	public double sum(int start) {
		return sum(start, CodeEvaluator.currentResultSet.size()+1);
	}

	public double sum(int start, int finish) {
		start -= 1;
		finish -= 1;

		double sum = 0;
		for (int i = start; i <= finish; i++) {
			if (CodeEvaluator.currentResultSet.size() > i) {
				sum += CodeEvaluator.currentResultSet.get(i).asNumber();
			}
		}
		return sum;
	}

	public double average() {
		return average(1, CodeEvaluator.currentResultSet.size()+1);
	}

	public double average(int start) {
		return average(start, CodeEvaluator.currentResultSet.size()+1);
	}

	public double average(int start, int finish) {
		start -= 1;
		finish -= 1;

		int counted = 0;
		double sum = 0;

		for (int i = start; i <= finish; i++) {
			if (CodeEvaluator.currentResultSet.size() > i) {
				counted += 1;
				sum += CodeEvaluator.currentResultSet.get(i).asNumber();
			}
		}

		return sum / counted;
	}

	public double percent(double number) {
		return number / 100.;
	}

	public double dollars(double number) {
		return number;
	}

	public double euros(double number) {
		return number * 1.13;
	}

	public double dollars_to_dollars(double number) {
		return number;
	}

	public double dollars_to_euros(double number) {
		return number / 1.13;
	}

	public double years(double number) {
		return number * 365. * 86400;
	}

	public double months(double number) {
		return number * 30 * 86400;
	}

	public double weeks(double number) {
		return number * 7 * 86400;
	}

	public double days(double number) {
		return number * 86400;
	}

	public double hours(double number) {
		return number * 60 * 60;
	}

	public double minutes(double number) {
		return number * 60;
	}

	public double seconds(double number) {
		return number;
	}

	public double date(String month, String day, String year) {

		// Allow for two digit dates
		if (year.length() == 2) {
			int yearInt = Integer.parseInt(year);
			if (yearInt > 30) {
				year = "19"+year;
			} else {
				year = "20"+year;
			}
		}

		String date = month+"/"+day+"/"+year;
		DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

		try {
			return (((Date) formatter.parse(date)).getTime()) / 1000.;
		} catch (Exception ex) {
			return 0;
		}
	}

	public double seconds_to_seconds(double num) {
		return num;
	}

	public double seconds_to_minutes(double num) {
		return num / 60.;
	}

	public double seconds_to_hours(double num) {
		return num / 60. / 60.;
	}

	public double seconds_to_days(double num) {
		return num / 86400.;
	}

	public double seconds_to_weeks(double num) {
		return num / 86400. / 7.;
	}

	public double seconds_to_months(double num) {
		return num / 86400. / 30.;
	}

	public double seconds_to_years(double num) {
		return num / 86400. / 365.;
	}

	public double meters(double num) {
		return num;
	}

	public double kilometers(double num) {
		return num * 1000.;
	}

	public double centimeters(double num) {
		return num / 100.;
	}

	public double millimeters(double num) {
		return num / 1000.;
	}

	public double foots(double num) {
		return num * 0.3048;
	}

	public double feets(double num) {
		return num * 0.3048;
	}

	public double feet(double num) {
		return num * 0.3048;
	}

	public double yards(double num) {
		return num * 0.9144;
	}

	public double miles(double num) {
		return num * 0.000568182;
	}

	public double meters_to_kilometers(double num) {
		return num / 1000.;
	}

	public double meters_to_centimeters(double num) {
		return num * 100.;
	}

	public double meters_to_millimeters(double num) {
		return num * 1000.;
	}

	public double meters_to_foots(double num) {
		return num * 3.28084;
	}

	public double meters_to_feets(double num) {
		return num * 3.28084;
	}

	public double meters_to_feet(double num) {
		return num * 3.28084;
	}

	public double meters_to_yards(double num) {
		return num * 1.09361;
	}

	public double meters_to_miles(double num) {
		return num * 0.000621371;
	}

	public double meters_to_meters(double num) {
		return num;
	}

	public double bin2dec(double num) {
		try {
			int n = Integer.parseInt(("" + (int) (num)), 2);
			System.out.println("Dec = "+n);
			return n;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return 0;
	}

	public int bin2dec(int num) {
		return (int) bin2dec((double)num);
	}

	public double hex2dec(String num) {
		return (double)Integer.parseInt(num, 16);
	}

	public int hex2dec(int num) {
		return (int) hex2dec(""+num);
	}

	public double dec2dec(double num) {
		return num;
	}

	public int dec2dec(int num) {
		return (int) dec2dec((double)num);
	}

	public double dec_to_dec(double num) {
		return num;
	}

	public double dec_to_decimal(double num) {
		return num;
	}

	public double dec_to_bin(double num) {
		return Double.parseDouble(Integer.toBinaryString((int)num));
	}

	public double dec_to_binary(double num) {
		return Double.parseDouble(Integer.toBinaryString((int)num));
	}

	public String dec_to_hex(double num) {
		return Integer.toHexString((int)num);
	}

	public String dec_to_hex(int num) {
		return Integer.toHexString(num);
	}

	public String date(double num) {
		DateFormat f = new SimpleDateFormat("MM/dd/yyyy");
		return f.format(new Date((long)(num*1000.0)));
	}
}
