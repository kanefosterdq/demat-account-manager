package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountPassword {

	public static String passwordValidator() {
		System.out.println("\nPassword ");
		ScannerCtrl.getScannerInstance().nextLine();
		String password = ScannerCtrl.getScannerInstance().nextLine();
		
		if(!isValidPassword(password)) {
			System.err.println("Password must contain at least 8 characters and at most 20 characters.\r\n"
					+ "It must contain at least one digit.\r\n"
					+ "It must contain at least one upper case alphabet.\r\n"
					+ "It must contain at least one lower case alphabet.\r\n"
					+ "It must contain at least one special character which includes !@#$%&*()-+=^.\r\n"
					+ "It should not contain any white space.");	
			passwordValidator();
		}
		return password;
		
	}
	public static boolean isValidPassword(String password)
    {

        String regex = "^(?=.*[0-9])"
                       + "(?=.*[a-z])(?=.*[A-Z])"
                       + "(?=.*[@#$%^&+=])"
                       + "(?=\\S+$).{8,20}$";
 
        Pattern p = Pattern.compile(regex);
 
        if (password == null) {
            return false;
        }
 
        Matcher m = p.matcher(password);
 
        return m.matches();
    }

}
