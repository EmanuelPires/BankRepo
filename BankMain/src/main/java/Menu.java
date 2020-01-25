import java.util.Scanner;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;

public class Menu {

	public static Customer curCustomer = null;

	public void startMenu() {

		System.out.println("Welome to our banking up");
		System.out.println("If you are a customer type login if not type register");

		System.out.println("If you're an employee or administrator type employee or admin");

		Scanner scan = new Scanner(System.in);
		String option = scan.nextLine();
		
		switch (option) {
		case "login":
			System.out.println("Enter your name");
			String name = scan.nextLine();
			System.out.println("Enter your password");
			String password = scan.nextLine();
			AccountManagment.login(name, password);
			
			break;
		case "register":
			System.out.println("Enter your name");
			name = scan.nextLine();
			System.out.println("Enter your password");
			password = scan.nextLine();
			AccountManagment.register(name, password);
			
		
		}

	}

	public static void menu2() {
		System.out.println("You are logged in!");
		//System.out.println("Your account information is listed below");
		//System.out.println("Name: " + Driver.curCustomer.getName());
		//System.out.println("Email: " + Driver.curCustomer.getEmail());
		//System.out.println("Address: " + Driver.curCustomer.getAddress());
		//System.out.println("Phone Number: " + Driver.curCustomer.getPhoneNumber());
		
		if(!Driver.curCustomer.getAccountEnabled()) {
			System.out.println("You want to apply for a account or quit");
			
			Scanner scan = new Scanner(System.in);
			String option2=scan.nextLine();
			switch (option2) {
			case "apply":
				IOMethods.writePendinFile();
				
				break;

			default:
				break;
			}
			
		}
//		System.out.println("What would you like to do? deposit or withdrawel");
//		Scanner scan = new Scanner(System.in);
//		String choice = scan.nextLine();
//		switch(choice) {
//		case "deposit":
//			break;
//		case "withdrawel":
//			break;
//		default: System.out.println("Hey type correctly");
//		break;
//		}
		
	}


}
