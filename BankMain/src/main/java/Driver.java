
public class Driver {

public static Customer curCustomer;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Customer firstCustomer = new Customer("Jason", "password");
//		Customer secondCustomer = new Customer("John", "password");
//		Customer.custList.add(firstCustomer);
//		Customer.custList.add(secondCustomer);
//		IOMethods.writeCustomerFile();
		
		 
		
		IOMethods.readCustFile();
		//System.out.println(Customer.custList.get(0));
		Menu menu = new Menu();
		menu.startMenu();
		menu.menu2();
	}

}
