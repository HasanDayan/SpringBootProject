package com.hasandayan.log;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

public class ProjectLog {

	public static ProjectLog _this = null;
	private final String logFile = "project_log.txt";
	private PrintWriter writer;

	private ProjectLog() {
		try {
			FileWriter fw = new FileWriter(System.getProperty("user.home") + "/" + logFile);
			writer = new PrintWriter(fw, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static synchronized ProjectLog getInstance() {

		if (_this == null) {
			_this = new ProjectLog();
		}

		return _this;
	}

	public void writeLog(String head, String content) {
		writer.println(head.toUpperCase(Locale.ENGLISH) + ": " + content);
	}

}
