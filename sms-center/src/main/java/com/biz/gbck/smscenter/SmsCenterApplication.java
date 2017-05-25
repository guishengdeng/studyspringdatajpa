package com.biz.gbck.smscenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SmsCenterApplication {

	public static void main(String[] args) {
		ApplicationContext tx = new ClassPathXmlApplicationContext("application.xml");
	}
}
