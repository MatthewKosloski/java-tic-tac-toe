//********************************************************************
// MyUtils.java
//
// A simple utility enumated class that possess 
// several static methods.
//********************************************************************

import java.util.Random;

public enum MyUtils {;

	private static Random generator = new Random();

	public static int range(int min, int max) {
		return generator.nextInt(max + 1) + min;
	}

}