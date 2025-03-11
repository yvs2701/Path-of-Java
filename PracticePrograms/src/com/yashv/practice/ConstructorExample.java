package com.yashv.practice;

class ExampleSuperClass {
	String field;

	public ExampleSuperClass() {
		this.field = "'";
	}

	// Parameterized constructor
	public ExampleSuperClass(String field) {
		this.field = "\"" + field;
	}
}

class ExampleClass extends ExampleSuperClass {
	// explicit default (no-args) constructor
	public ExampleClass() {
		// super(); // compiler adds this implicitly
		field += "'";
	}

	// Parameterized constructor
	ExampleClass(String field) {
		super(field); // constructor chaining (can also be used with `this(...)`)
		this.field += "\"";
	}

	// copy constructor
	ExampleClass(ExampleClass src) {
		field = src.field;
	}
}

public class ConstructorExample {
	public static void main(String[] args) {
		ExampleClass exClass = new ExampleClass();
		System.out.println("Field value = " + exClass.field);


		exClass = new ExampleClass("Custom value");
		System.out.println("Field value = " + exClass.field);
		System.out.println();


		ExampleClass shallowCopy = exClass;
		System.out.println("Field value = " + exClass.field);
		System.out.println("The two objects are references to same location:\n" + "Source object hascode: "
				+ exClass.hashCode() + "\nTarget object hashcode: " + shallowCopy.hashCode());
		System.out.println();


		ExampleClass deepCopy = new ExampleClass(exClass);
		System.out.println("Field value = " + exClass.field);
		System.out.println("The two objects are deep copies and don't point to same location:\n"
				+ "Source object hascode: " + exClass.hashCode() + "\nTarget object hashcode: " + deepCopy.hashCode());
		System.out.println();
	}
}
