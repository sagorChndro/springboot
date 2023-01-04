package com.sagor.util;

public class ReflectionTest {
	public static String helloStatic(String s) {
		return s;
	}

	public static void helloStatic() {
		System.out.println("Hello static world");
	}

	public String helloNonStatic(String s) {
		return s;
	}

	public void helloNonStatic() {
		System.out.println("Hello non static world");
	}

}
