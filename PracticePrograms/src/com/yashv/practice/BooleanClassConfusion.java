package com.yashv.practice;

public class BooleanClassConfusion {
	public static void main(String[] args) {

		/**
		 * `Boolean` constructor interprets only `true` or `"true"` as truthy values
		 * anything else is interpreted as false.
		 */
		Boolean b1 = new Boolean("true");
		Boolean b2 = new Boolean("false");
		System.out.println(b1.equals(b2));

		Boolean b3 = new Boolean("hello");
		Boolean b4 = new Boolean("false");
		System.out.println(b3.equals(b4));

		Boolean b5 = new Boolean("false");
		Boolean b6 = new Boolean(true);
		System.out.println(b5.equals(b6));
		System.out.println("b1:" + b1 + "\nb2:" + b2 + "\nb3:" + b3 + "\nb4:" + b4 + "\nb5:" + b5 + "\nb6:" + b6);
	}
}
