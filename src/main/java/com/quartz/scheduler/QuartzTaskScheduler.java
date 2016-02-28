package com.quartz.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.quartz.dao.QuartzDao;

public class QuartzTaskScheduler {

	private static boolean isRunning = false;

	@Autowired
	private QuartzDao quartzDao;

	public void execute() throws Exception {
		try {
			synchronized (QuartzTaskScheduler.class) {
				if (isRunning) {
					return;
				}
				isRunning = true;
			}

			runSqlQueries();

		} finally {

			synchronized (QuartzTaskScheduler.class) {
				isRunning = false;
			}
		}
	}

	private void runSqlQueries() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		System.out.println(dateFormat.format(date));
		quartzDao.executeSqlQueries();
	}

}
