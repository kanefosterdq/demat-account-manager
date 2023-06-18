package controller;

public class Tax {

	// Security transfer tax 
	static double sttPercent = 0.1;

	public static double calcSTT(double total) {
		return (sttPercent / 100) * total;
	}
}
