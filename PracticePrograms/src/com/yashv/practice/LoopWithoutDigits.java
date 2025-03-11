package com.yashv.practice;

public class LoopWithoutDigits {
	/*
	 * Write a program to print 1 to 100 numbers. Make sure to NOT use any digits in the code
	 */
	
	public static void main (String[] args) {
		/* OR WE COULD HAVE USED COMMAND LINE ARGUMENTS AS START AND END.
			int start = args[0], end = args[1]; */
		int start = 'b' - 'a', end = 'd';
		for (int i = start; i <= end; i++)
			System.out.print(i + " ");
		System.out.println("\n---");
	}
}
