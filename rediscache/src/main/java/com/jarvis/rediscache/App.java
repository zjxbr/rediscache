package com.jarvis.rediscache;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		
		AnnotationConfigApplicationContext springContext = new AnnotationConfigApplicationContext();
		springContext.scan("com.jarvis.rediscache*");
		springContext.refresh();
	}
}
