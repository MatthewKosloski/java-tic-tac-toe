//********************************************************************
// MyUtils.java
//
// A simple utility enumated class that possess 
// several static utility methods.
//********************************************************************

import java.util.Random;

public enum MyUtils {;

	private static Random generator = new Random();

	public static int range(int min, int max) {
		return generator.nextInt(max + 1) + min;
	}

	public static boolean charEqualsIgnoreCase(char a, char b) {
		return new String(a + "").equalsIgnoreCase(new String(b + ""));
	}

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

	// int[] arr = {3, 3, 1, 2};
	public static int getIndexOfLargestInt(int[] arr, int predicate) {

		// Remove predicate ints from array
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] == predicate) {
				arr[i] = 0;
			}
		}

		int highestIndex = 0;
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] > arr[highestIndex]) {
				highestIndex = i;
			}
		}
		return highestIndex;
	}

}