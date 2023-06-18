package controller;

import java.util.Scanner;

public class ScannerCtrl {
	
	public static Scanner scanner;
	public static Scanner getScannerInstance() {
		if(scanner == null) 
			scanner = new Scanner(System.in);
		return scanner;
	}
}
