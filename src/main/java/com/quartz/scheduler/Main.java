package com.quartz.scheduler;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(final String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("quartz-jobs.xml");
	}

}
