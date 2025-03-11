package com.yashv.practice;

interface ExampleInterface1 {
	/* public static final */ String PI = "3.14";

	/* public */ default String getPi() {
		return PI;
	}

	/* public */ static String getMessage() {
		return "This is static function 1.";
	}

	/* public abstract */ void overrideMe();
}

interface ExampleInterface2 {
	String PI = "22/7";

	default String getPi() {
		return PI;
	}

	/* public */ static String getMessage() {
		return "This is static function 2.";
	}
}

class ExampleImplClass implements ExampleInterface1, ExampleInterface2 {
	// we can also make these fields static just like the parent interfaces
	String PI = "PI", PI1 = ExampleInterface1.PI, PI2 = ExampleInterface2.PI;

	public void overrideMe() {
		System.out.println("This is the Overridden method.");
	}

	@Override
	public String getPi() {
		// RESOLVING DIAMOND PROBLEM
		return ExampleInterface1.super.getPi();
	}
}

public class InterfaceExample {
	public static void main(String[] args) {
		ExampleImplClass obj = new ExampleImplClass();
		obj.overrideMe();

		// Static interface methods are not inherited to the class.
		System.out.println(ExampleInterface1.getMessage());

		System.out.println(obj.getPi());

		// Interface fields (which are public static final) are inherited to class
		System.out.println(obj.PI);
		System.out.println(obj.PI1);
		System.out.println(obj.PI2);
	}
}
