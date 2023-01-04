package com.sagor.util;

public class ReflectionTest {
	private static String helloStatic(String s) {
		return s;
	}

	private static void helloStatic() {
		System.out.println("Hello static world");
	}

	private String helloNonStatic(String s) {
		return s;
	}

	private void helloNonStatic() {
		System.out.println("Hello non static world");
	}

}
