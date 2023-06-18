package driver;

import java.util.InputMismatchException;
import java.util.Scanner;

import controller.AccountInfo;
import controller.ScannerCtrl;
import controller.UserLogin;

public class AppDriver {

	public void start() {

		System.out.println("\n------------Amazon Demat---------------");
		System.out.println("1. Create account\n");
		System.out.println("2. Login\n");
		
		int userChoice = 0;
		
		try {
			userChoice = ScannerCtrl.getScannerInstance().nextInt();
		} catch (InputMismatchException ime) {
			ime.printStackTrace();
		}

		System.out.println();
		switch (userChoice) {
		case 1:
			AccountInfo.getUserDetails();

		case 2:
			System.out.println("\nLogin please...\n");
			UserLogin.getCredentials();
			break;

		default:
			System.out.println("Invalid choice");
		}
	}

}
