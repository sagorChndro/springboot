package com.sagor.util;

import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ReflectionUtil {
	private static final Logger logger = LogManager.getLogger(ReflectionUtil.class.getName());

	public static Method getMethod(String registerClassName, String methodName, Class... parameters) {
		Class className = null;
		Method method = null;

		try {
			className = Class.forName(registerClassName);
			try {
				method = className.getMethod(methodName, parameters); // non static method er jonno
			} catch (Exception e) {
				method = className.getDeclaredMethod(methodName, parameters); // static method er jonno
			}
			method.setAccessible(false);
			return method;
		} catch (Exception e) {
			logger.error("Reflection Error : " + e.getMessage());
			return null;
		}
	}
}
