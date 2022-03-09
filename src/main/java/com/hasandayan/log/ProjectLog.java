package com.hasandayan.log;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

public class ProjectLog {

	private static ProjectLog projectLog = null;
	private static final String LOG_FILE = "project_log.txt";
	private PrintWriter writer;

	private ProjectLog() {
		try {
			FileWriter fw = new FileWriter(System.getProperty("user.home") + "/" + LOG_FILE);
			writer = new PrintWriter(fw, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static synchronized ProjectLog getInstance() {

		if (projectLog == null) {
			projectLog = new ProjectLog();
		}

		return projectLog;
	}

	public void writeLog(String head, String content) {
		writer.println(head.toUpperCase(Locale.ENGLISH) + ": " + content);
	}

}
