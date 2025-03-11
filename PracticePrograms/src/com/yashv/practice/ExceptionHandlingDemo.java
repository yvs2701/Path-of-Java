package com.yashv.practice;

//Custom checked exception
class InvalidInputException extends Exception {
	public InvalidInputException(String message) {
		super(message);
	}
}

//Custom unchecked exception
class CalculationException extends RuntimeException {
	public CalculationException(String message) {
		super(message);
	}
}

public class ExceptionHandlingDemo {

	// checked exception
	public static void validateInput(int number) throws InvalidInputException {
		if (number < 0) {
			throw new InvalidInputException("Number cannot be negative");
		}
	}

	// unchecked exception
	public static int divide(int a, int b) {
		if (b == 0) {
			throw new CalculationException("Division by zero is not allowed");
		}
		return a / b;
	}

	public static void main(String[] args) {
		try {
			// Checked exception handling
			int number = -5;
			System.out.println("Validating input...");
			validateInput(number); // This will throw InvalidInputException
			System.out.println("Input is valid");

		} catch (InvalidInputException e) {
			System.out.println("Caught Checked Exception: " + e.getMessage());
		}

		try {
			// handling or specifying unchecked exception, using `throws`, is optional
			// Unchecked exception handling
			int result = divide(10, 0); // This will throw CalculationException
			System.out.println("Division Result: " + result);

		} catch (CalculationException e) {
			System.out.println("Caught Unchecked Exception: " + e.getMessage());
		}

		System.out.println();
	}
}
