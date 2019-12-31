package org.xtext.example.mydsl.debugger.processing;

import java.util.Arrays;
import java.util.List;

public class Calculator {
	final static String[] arithmeticOperators = new String[] {"+", "-", "*", "/"};
	
	public static boolean isArithmetic(String operator) {
		final List<String> list = Arrays.asList(Calculator.arithmeticOperators);
		return list.contains(operator);
	}
	
	public static int arithmeticCalculate(int left, String operator, int right) {
		int result = 0;

		switch(operator) {
			case "+":
				result = left + right;
				break;
			case "-":
				result = left - right;
				break;
			case "*":
				result = left * right;
				break;
			case "/":
				result = left / right;
				break;
		}
		return result;
	}
	
	public static double arithmeticCalculate(double left, String operator, double right) {
		double result = 0.00;
		
		switch(operator) {
			case "+":
				result = left + right;
				break;
			case "-":
				result = left - right;
				break;
			case "*":
				result = left * right;
				break;
			case "/":
				result = left / right;
				break;
		}
		return result;
	}
}
