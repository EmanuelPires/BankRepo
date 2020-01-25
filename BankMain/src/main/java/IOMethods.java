import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class IOMethods {
	
	private static final File CUSTFILE = new File("customerList.txt");
	
	private static final File PendingFile=new File("pendingReguest.txt");		

	public static void writeCustomerFile() {
		ObjectOutputStream objectOut;
		
		
			try {
				objectOut = new ObjectOutputStream(new FileOutputStream(CUSTFILE));
				objectOut.writeObject(Customer.custList);
				objectOut.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	
	}
	
	public static void readCustFile() {
		ObjectInputStream objectIn;
		try {
			objectIn = new ObjectInputStream(new FileInputStream(CUSTFILE));
			Customer.custList = (ArrayList<Customer>)objectIn.readObject();
			objectIn.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void writePendinFile() {
		ObjectOutputStream obj;
		try {
			Customer.pendingList.add(Driver.curCustomer);
			obj = new ObjectOutputStream(new FileOutputStream(PendingFile));
			obj.writeObject(Customer.pendingList);
			obj.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
		}
		
		
	}

