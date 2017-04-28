//********************************************************************
// MyUtils.java
//
// An enumated class with helper functions.
//********************************************************************

import java.util.Random;

public enum MyUtils {;

	private static Random generator = new Random();

	/*
	 * returns a random integer value between a min and max
	 */
	public static int range(int min, int max) {
		return generator.nextInt(max + 1) + min;
	}

	/*
	 * returns a boolean value indicating whether or not
	 * the two provided characters are equal (case insensitive)
	 */
	public static boolean charEqualsIgnoreCase(char a, char b) {
		return new String(a + "").equalsIgnoreCase(new String(b + ""));
	}

	/*
	 * takes a variable number of string arrays and 
	 * combines them into one, large string array
	 */
	public static String[] mergeStringArrays(String[] ... arrs) {
		int len = 0, count = 0;

		// get the sum of the array lengths
		for(int i = 0; i < arrs.length; i++) {
			len += arrs[i].length;
		}

		String[] result = new String[len];

		for(int i = 0; i < arrs.length; i++) {
			for(int j = 0; j < arrs[i].length; j++) {
				result[count] = arrs[i][j];
				count++;
			}
		}

		return result;
	}

}