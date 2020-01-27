import java.util.Scanner;

public class AccountManagment {
	public static Customer curCustomer = null;
	public static int custIndex = 0;
	public static int empIndex = 0;

	public static void register(String name, String password) {
		Customer newCustomer = new Customer(name, password);
		Customer.custList.add(newCustomer);
		IOMethods.writeCustomerFile();
		Driver.curCustomer = newCustomer;
	}

	public static void updateAccount() {
		int cont1 = 0;
		while ((Customer.custList.get(cont1).getName().equals(Driver.curCustomer.getName()) != true
				&& Customer.custList.get(cont1).getPassword().equals(Driver.curCustomer.getPassword()) != true)) {
			cont1++;
		}
		Customer.custList.set(cont1, Driver.curCustomer);
		IOMethods.writeCustomerFile();
	}

	
	public static void userNotFound() {
		System.out.println("Username or password not found");
		Menu.startMenu();
		
	}
	
	public static void custEmpNotfound() {
		System.out.println("Customer not found");
		Menu.menu3();
	}
	
	public static void custAdNotFound() {
		System.out.println("Account not found");
		Menu.menu4();
	}
	
	public static void employeeNotFound() {
		System.out.println("Employee not found");
		Menu.startMenu();
	}
	
	
	//CUSTOMER LOGIN THIS IS RIGHT!
	public static void login(String name, String password) {
		int cont = 0;
		while (Customer.custList.size() >= cont && (Customer.custList.get(cont).getName().equals(name) != true
				|| Customer.custList.get(cont).getPassword().equals(password) != true)) {
			
			cont++;
			if(cont==Customer.custList.size()) {
				System.out.println("You're username or password was not found");
				Menu.startMenu();
			}
		}

		
		
			 System.out.println(cont);
			custIndex = cont;
			Driver.curCustomer = Customer.custList.get(cont);
			 System.out.println("From AcctMgmt curCustomer = " +
			 Driver.curCustomer.toString());
		

	}

	public static void empLogin(String name, String password) {

		int cont = 0;
		System.out.println(Employee.empList.get(cont).toString());
		
		
		while (Employee.empList.size() >=cont && (Employee.empList.get(cont).getName().equals(name) != true
				|| Employee.empList.get(cont).getPassword().equals(password) != true)) {

			cont++;
			if(cont>= Employee.empList.size()) {
				//System.out.println("Is it getting here?");
				employeeNotFound();
			}

		}

		if (cont == Employee.empList.size() && Employee.empList.get(cont).getName() != name
				|| Employee.empList.get(cont).getPassword() != password) {
			System.out.println("Your username or password are incorrect");
		} else {
			// System.out.println(cont);
			empIndex = cont;
			Driver.curEmployee = Employee.empList.get(cont);
			// System.out.println("From AcctMgmt curCustomer = " +
			// Driver.curCustomer.toString());
		}

	}

	public static void deposit(double deposit) {

		double balance = Driver.curCustomer.getBalance();
		balance += deposit;
		Driver.curCustomer.setBalance(balance);
		System.out.println("Your new balance is: " + Driver.curCustomer.getBalance());
		int cont1 = 0;
		while ((Customer.custList.get(cont1).getName().equals(Driver.curCustomer.getName()) != true
				&& Customer.custList.get(cont1).getPassword().equals(Driver.curCustomer.getPassword()) != true)) {
			cont1++;
		}
		Customer.custList.set(cont1, Driver.curCustomer);
		IOMethods.writeCustomerFile();

	}

	public static void withdraw(double amount) {

		if (Driver.curCustomer.getBalance() >= amount) {

			double balance = Driver.curCustomer.getBalance();
			balance -= amount;
			Driver.curCustomer.setBalance(balance);
			System.out.println("Your new balance is: " + Driver.curCustomer.getBalance());
			int cont1 = 0;
			while ((Customer.custList.get(cont1).getName().equals(Driver.curCustomer.getName()) != true
					&& Customer.custList.get(cont1).getPassword().equals(Driver.curCustomer.getPassword()) != true)) {
				cont1++;
			}
			Customer.custList.set(cont1, Driver.curCustomer);
			IOMethods.writeCustomerFile();
		}

		else
			System.out.println("insufficient funds");

	}

	public static void appoveAccounts() {
		System.out.println("Entering approve accounts");
		IOMethods.readPending();
		// System.out.println("Here are the pending accounts approve or deny");
		// System.out.println("This is from Account
		// Methods"+Customer.pendingList.get(0).toString() );

		if (Customer.pendingList.size() > 0) {
			for (int i = 0; i < Customer.pendingList.size(); i++) {
				System.out.println(Customer.pendingList.get(i).toString());

				Customer curCustomer = Customer.pendingList.get(i);
				Scanner scan = new Scanner(System.in);
				String choice = scan.nextLine();
				if (choice.equals("approve")) {
					int cont1 = 0;
					while ((Customer.custList.get(cont1).getName().equals(curCustomer.getName()) != true
							&& Customer.custList.get(cont1).getPassword().equals(curCustomer.getPassword()) != true)) {
						cont1++;
					}

					curCustomer.setEnabledTrue();
					Customer.custList.set(cont1, curCustomer);

					IOMethods.writeCustomerFile();
					Customer.pendingList.remove(i);
					// System.out.println(Customer.pendingList.get(i).toString());

					IOMethods.removePendingCustomer();
					System.out.println("the customer is removed");
					Menu.menu3();
				} else if (choice.equals("deny")) {
					int cont1 = 0;
					while ((Customer.custList.get(cont1).getName().equals(curCustomer.getName()) != true
							&& Customer.custList.get(cont1).getPassword().equals(curCustomer.getPassword()) != true)) {
						cont1++;
					}

					// Here we're doing the same thing but we're not
					// calling the enable function.
					// The customer gets removed from the
					// pending list but nothing else changes

					Customer.pendingList.remove(i);
					// System.out.println(Customer.pendingList.get(i).toString());

					IOMethods.removePendingCustomer();
					System.out.println("the customer is denied and removed");
					Menu.menu3();
				}

			}
		} else {
			System.out.println("there's not pending accounts");
			Menu.menu3();
		}

	}

	public static void viewCustBalance() {

		System.out.println("your balance is :" + Driver.curCustomer.getBalance());

	}

	public static void viewCustAcc(String name) {

		int cont = 0;
		while (Customer.custList.size() >cont && (Customer.custList.get(cont).getName().equals(name) != true)) {

			cont++;
			
			if(cont>=Customer.custList.size()) {
				custEmpNotfound();
			}

		}

		if (cont == Customer.custList.size() && Customer.custList.get(cont).getName() != name) {
			System.out.println("User doesn't exist");
		} else {
			// System.out.println(cont);
			custIndex = cont;
			Driver.curCustomer = Customer.custList.get(cont);
			// System.out.println("From AcctMgmt curCustomer = " +
			// Driver.curCustomer.toString());
		}

		System.out.println("This is Customer " + name + " Informations");
		System.out.println(Driver.curCustomer.toString());
		Menu.menu3();
	}
	
	public static void adlogin(String name,String pass) {
		int cont = 0;
		while (Admin.adList.size() < cont && (Admin.adList.get(cont).getName().equals(name) != true
				&& Admin.adList.get(cont).getPassword().equals(pass) != true)) {
			cont++;
		}
		if (cont == Admin.adList.size() && Admin.adList.get(cont).getName() != name
				&& Admin.adList.get(cont).getPassword() != pass) {
			System.out.println("Your username or password are incorrect");
		} else {
			Driver.curAdmin =Admin.adList.get(cont);
		}
		System.out.println("You're logged in!");
		System.out.println(Driver.curAdmin.toString());
	}
	
	public static void viewCustAccAd(String name) {
		int cont = 0;
		while (Customer.custList.size() >cont && (Customer.custList.get(cont).getName().equals(name) != true)) {

			cont++;
			if(Customer.custList.size()>=cont) {
				custAdNotFound();
			}

		}
		if (cont == Customer.custList.size() && Customer.custList.get(cont).getName() != name) {
			System.out.println("User doesn't exist");
		} else {
			// System.out.println(cont);
			custIndex = cont;
			Driver.curCustomer = Customer.custList.get(cont);
			// System.out.println("From AcctMgmt curCustomer = " +
			// Driver.curCustomer.toString());
		}
		System.out.println("This is Customer " + name + " Informations");
		System.out.println(Driver.curCustomer.toString());
		Menu.menu4();
	}
}
