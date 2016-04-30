package com.creationshare.codecalc.evaluation;

/**
 * The code preprocessor accepts one line entered by the user
 * at a time, and converts it into groovy code. This is used by
 * the CodeEvaluator to prepare code for evaulation in Groovy.
 *
 * Created by matt on 4/20/16.
 */
public class CodePreprocessor {
	public static String process(String line) {

		// Support for colons-comments
		if (line.contains(":")) {
			line = line.substring(line.indexOf(':')+1);
		}

		// Basic mathematical operations
		line = line.replaceAll("([0-9.]+)( |)\\^( |)([0-9.]+)", "$1 ** $4");

		// Binary operations
		line = line.replaceAll("(^|\\s)([0-9.]+)d", "$1dec2dec($2)");
		line = line.replaceAll("(^|\\s)([0-1.]+)b", "$1bin2dec($2)");
		line = line.replaceAll("(^|\\s)([0-9a-fA-F.]+)h", "$1hex2dec(\"$2\")");

		// Percentage operations
		line = line.replaceAll("([0-9.]+)%", "percent($1)");

		// Currency operations
		line = line.replaceAll("\\$([0-9.]+)", "dollars($1)");
		line = line.replaceAll("€([0-9.]+)", "euros($1)");
		line = line.replaceAll("([0-9.]+) (dollar|euro)(s|)", "$2s($1)");

		// Time operations
		line = line.replaceAll("([0-9.]+) (hour|minute|second|day|year|month|week)(s|)", "$2s($1)");
		line = line.replaceAll("([0-9]?[0-9])/([0-9]?[0-9])/([0-9]?[0-9]?[0-9][0-9])", "date(\"$1\", \"$2\", \"$3\")");

		// Length operations
		line = line.replaceAll("([0-9.]+) (meter|centimeter|kilometer|feet|foot|yard|mile)(s|)", "$2s($1)");

		// Binary operations
		line = line.replaceAll("([0-9a-zA-Z\\.\\(\\)]+)\\s+mod\\s+([0-9a-zA-Z\\.\\(\\)]+)", "$1 % $2");
		line = line.replaceAll("([0-9a-zA-Z\\.\\(\\)]+)\\s+and\\s+([0-9a-zA-Z\\.\\(\\)]+)", "$1 & $2");
		line = line.replaceAll("([0-9a-zA-Z\\.\\(\\)]+)\\s+xor\\s+([0-9a-zA-Z\\.\\(\\)]+)", "$1 ^ $2");
		line = line.replaceAll("([0-9a-zA-Z\\.\\(\\)]+)\\s+or\\s+([0-9a-zA-Z\\.\\(\\)]+)", "$1 | $2");

		// Conversions
		line = line.replaceAll("(.*?) (in|to|as) (binary|hex|decimal|bin|dec)(s|)", "dec_to_$3($1)");
		line = line.replaceAll("(.*?) (in|to|as) (hour|minute|second|day|year|month|week)(s|)", "seconds_to_$3s($1)");
		line = line.replaceAll("(.*?) (in|to|as) (dollar|euro)(s|)", "dollars_to_$3s($1)");
		line = line.replaceAll("(.*?) (in|to|as) (meter|centimeter|kilometer|feet|foot|yard|mile)(s|)", "meters_to_$3s($1)");
		line = line.replaceAll("(.*?) (in|to|as) (date)", "date($1)");

		// Support for unknown units
		line = line.replaceAll("([0-9.]+) ([a-zA-Z0-9]+)", "$1");

		//System.out.println("CodePreprocessor Final Code = "+line);

		return line;

	}
}
