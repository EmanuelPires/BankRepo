import java.util.Scanner;

public class AccountManagment {
public static Customer curCustomer = null;
public   static int custIndex=0;
	
	public static void register(String name, String password) {
		Customer newCustomer = new Customer(name, password);
		Customer.custList.add(newCustomer);
		IOMethods.writeCustomerFile();
		Driver.curCustomer = newCustomer;
	}
	
	public static void login(String name, String password) {
		int cont = 0;
		while (Customer.custList.size() >=cont && (Customer.custList.get(cont).getName().equals(name) != true
				|| Customer.custList.get(cont).getPassword().equals(password) != true)) {

			cont++;

		}

		if (cont == Customer.custList.size() && Customer.custList.get(cont).getName() != name
				&& Customer.custList.get(cont).getPassword() != password) {
			System.out.println("Your username or password are incorrect");
		} else {
			//System.out.println(cont);
			custIndex=cont;
			Driver.curCustomer = Customer.custList.get(cont);
			System.out.println("From AcctMgmt curCustomer = " + Driver.curCustomer.toString());
		}

		
	}
	
	public static void empLogin(String name, String password) {
		
		int cont = 0;
		while (Employee.empList.size() < cont && (Employee.empList.get(cont).getName().equals(name) != true
				&& Employee.empList.get(cont).getPassword().equals(password) != true)) {

			cont++;

		}

		if (cont == Employee.empList.size() && Employee.empList.get(cont).getName() != name
				&& Employee.empList.get(cont).getPassword() != password) {
			System.out.println("Your username or password are incorrect");
		} else {
			Driver.curEmployee = Employee.empList.get(cont);
		}
		System.out.println("You're logged in!");
		System.out.println(Driver.curEmployee.toString());
		
	}
	
	public static void deposit(double deposit) {
		
		double balance = Driver.curCustomer.getBalance();
		balance += deposit;
		Driver.curCustomer.setBalance(balance);
		System.out.println("Your new balance is: "+Driver.curCustomer.getBalance());
		int cont1=0;
		while ((Customer.custList.get(cont1).getName().equals(Driver.curCustomer.getName()) != true
				&& Customer.custList.get(cont1).getPassword().equals(Driver.curCustomer.getPassword()) != true)) {
			cont1++;
		}
		Customer.custList.set(cont1, Driver.curCustomer);
		IOMethods.writeCustomerFile();
				
		
	}
	public static void withdraw(double amount) {
		
		if (Driver.curCustomer.getBalance()>=amount) {
			
			Driver.curCustomer.setBalance(Driver.curCustomer.getBalance() - amount);
			
			System.out.println("Your new balance is "+ Driver.curCustomer.getBalance() );
			System.out.println(custIndex);
			Customer.custList.set(custIndex, curCustomer);
			IOMethods.writeCustomerFile();
		}
		
		else System.out.println("insufficient funds");
		
	}
	
	public static void appoveAccounts() {
		System.out.println("Entering approve accounts");
		IOMethods.readPending();
		//System.out.println("Here are the pending accounts approve or deny");
		//System.out.println("This is from Account Methods"+Customer.pendingList.get(0).toString() );
		
		if(Customer.pendingList.size()>=0) {
		for(int i =0; i<Customer.pendingList.size(); i ++) {
			System.out.println(Customer.pendingList.get(i).toString());
			
			Customer curCustomer = Customer.pendingList.get(i);
			Scanner scan = new Scanner(System.in);
			String choice = scan.nextLine();
			if(choice.equals("approve")) {
				int cont1=0;
				while ((Customer.custList.get(cont1).getName().equals(curCustomer.getName()) != true
						&& Customer.custList.get(cont1).getPassword().equals(curCustomer.getPassword()) != true)) {
					cont1++;
				}
				System.out.println(cont1);
				curCustomer.setEnabledTrue();
				Customer.custList.set(cont1, curCustomer);
				
				
				IOMethods.writeCustomerFile();
				Customer.pendingList.remove(i);
				//System.out.println(Customer.pendingList.get(i).toString());
				
				IOMethods.removePendingCustomer();
				System.out.println("the customer is removed");
			}
			
			
		}
	}else {
		System.out.println("there's not pending accounts");
	}
		
	}
	
	
}
