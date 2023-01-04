package com.sagor.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionMain {
	public static void main(String[] args)
			throws IllegalAccessException, InvocationTargetException, ClassNotFoundException, InstantiationException,
			IllegalArgumentException, NoSuchMethodException, SecurityException {
		// static void method for barCodeGen
		String className = "com.sagor.util.BarCodeGen";
		Method method = ReflectionUtil.getMethod(className, "createBarCode128", String.class);
		method.invoke(Class.forName(className), "123456");

		// static void method for ReflectionTest no Argument type // Argument na thekale
		// String.class dibo na
		String className1 = "com.sagor.util.ReflectionTest";
		Method method1 = ReflectionUtil.getMethod(className1, "helloStatic");
		method1.invoke(Class.forName(className1));

		// static non void method
		// String className2 = "com.sagor.util.ReflectionTest";
		Method method2 = ReflectionUtil.getMethod(className1, "helloStatic", String.class);
		String s = (String) method2.invoke(Class.forName(className1), "Hello Static");
		System.out.println(s);

		// non static non void method
		Method method3 = ReflectionUtil.getMethod(className1, "helloNonStatic", String.class);
		String s2 = (String) method3.invoke(Class.forName(className1).getConstructor().newInstance(),
				"Hello Non Static");
		System.out.println(s2);

		// Non static void method
		Method method4 = ReflectionUtil.getMethod(className1, "helloNonStatic");
		String s3 = (String) method4.invoke(Class.forName(className1).getConstructor().newInstance());

	}

}
