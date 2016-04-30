package com.creationshare.docframe.utils;

import java.io.File;
import java.util.HashMap;

/**
 * Created by matt on 4/21/16.
 */
public class DoctypeDetector {

	public static final HashMap<String, String> extensions;

	static {
		extensions = new HashMap<>();
		extensions.put(".c", "text/c");
		extensions.put(".cpp", "text/cpp");
		extensions.put(".cs", "text/cs");
		extensions.put(".css", "text/css");
		extensions.put(".h", "text/cpp");
		extensions.put(".rb", "text/ruby");
		extensions.put(".gy", "text/groovy");
		extensions.put(".groovy", "text/groovy");
		extensions.put(".html", "text/html");
		extensions.put(".htm", "text/html");
		extensions.put(".php", "text/php");
		extensions.put(".java", "text/java");
		extensions.put(".js", "text/javascript");

		extensions.put(".tcf", "text/groovy");
	}

	public static String detectDoctype(File file) {
		for (String key : extensions.keySet()) {
			if (file.getName().endsWith(key)) {
				return extensions.get(key);
			}
		}
		return null;
	}

}
