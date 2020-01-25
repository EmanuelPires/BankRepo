
public class AccountManagment {
public static Customer curCustomer = null;
	
	public static void register(String name, String password) {
		Customer newCustomer = new Customer(name, password);
		Customer.custList.add(newCustomer);
		IOMethods.writeCustomerFile();
		Driver.curCustomer = newCustomer;
	}
	
	public static void login(String name, String password) {
		int cont = 0;
		while (Customer.custList.size() < cont && (Customer.custList.get(cont).getName().equals(name) != true
				&& Customer.custList.get(cont).getPassword().equals(password) != true)) {

			cont++;

		}

		if (cont == Customer.custList.size() && Customer.custList.get(cont).getName() != name
				&& Customer.custList.get(cont).getPassword() != password) {
			System.out.println("Your username or password are incorrect");
		} else {
			Driver.curCustomer = Customer.custList.get(cont);
		}

		
	}
	
	public static void deposit() {
		
	}
	
	
}
