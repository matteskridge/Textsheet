package com.creationshare.docframe.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by matt on 4/19/16.
 */
public class FileUtility {

	private static boolean initialized = false;
	private static File install;

	public static void init() {
		initialized = true;
		install = new File("install");
	}

	public static File getInstallFile(String path) {
		if (!initialized) init();
		return new File(install.getAbsolutePath()+"/"+path);
	}

	public static String readInstallFile(String s) {
		return readFile(getInstallFile(s));
	}

	public static String readFile(File file) {
		try {
			byte[] encoded = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
			return new String(encoded, Charset.defaultCharset());
		} catch (Exception ex) {
			return "";
		}
	}

	public static void writeFile(File file, String text) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(text);
			writer.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
