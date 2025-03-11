package com.yashv.practice;

public class CachedValuesInteger {
	public static void main(String[] args) {
		/**
		 * Java caches `Integer` objects for values in the range of -128 to 127 for
		 * performance reasons. When you use `Integer` objects (not `int` primitives) in
		 * this range, Java reuses the same object references, so comparisons using `==`
		 * will return true. equals() method shall always work fine.
		 */

		Integer n1 = 127, n2 = 127, n3 = new Integer(127), n4 = new Integer(127), n5 = 129, n6 = 129;

		System.out.println("n1 == n2? " + (n1 == n2)); // true (cached objects reused)
		System.out.println("n3 == n4? " + (n3 == n4)); // false (created new objects)
		System.out.println("n5 == n6?" + (n5 == n6)); // false (no cached objects, new objects created implicitly)

		System.out.println("n1.equals(n2)? " + n1.equals(n2)); // true
		System.out.println("n1.equals(n2)? " + n1.equals(n2)); // true
		System.out.println("n1.equals(n2)?" + n1.equals(n2)); // true

	}
}