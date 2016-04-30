package com.creationshare.docframe.utils;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Method;

/**
 * Created by matt on 4/19/16.
 */
public class OSOptimizer {

	public static void optimize(JFrame win) {
		enableOSLAF(win);
		enableOSXFullscreen(win);
	}

	public static void enableOSLAF(JFrame win) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * https://stackoverflow.com/questions/6873568/fullscreen-feature-for-java-apps-on-osx-lion
	 * @param window
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	public static void enableOSXFullscreen(Window window) {

		try {
			Class util = Class.forName("com.apple.eawt.FullScreenUtilities");
			Class params[] = new Class[]{Window.class, Boolean.TYPE};
			Method method = util.getMethod("setWindowCanFullScreen", params);
			method.invoke(util, window, true);
		} catch (ClassNotFoundException e1) {
		} catch (Exception e) {
		}
	}

}
