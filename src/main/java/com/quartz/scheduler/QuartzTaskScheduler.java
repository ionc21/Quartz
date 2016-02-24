package com.quartz.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

public class QuartzTaskScheduler {

	private static boolean isRunning = false;

	public void execute() throws Exception {
		try {
			synchronized (QuartzTaskScheduler.class) {
				if (isRunning) {
					return;
				}
				isRunning = true;
			}

			showCurrentTime();

		} finally {

			synchronized (QuartzTaskScheduler.class) {
				isRunning = false;
			}
		}
	}

	private void showCurrentTime() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		System.out.println(dateFormat.format(date));
	}

}
