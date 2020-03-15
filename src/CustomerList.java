/**
 * @(#)CustomerList.java
 *
 *
 * @author 
 * @version 1.00 2020/2/25
 */
import java.util.ArrayList;
/**
 *
 */
public class CustomerList 
{
	private ArrayList<Customer> customerList;
	
    public CustomerList() 
    {
    	customerList = new ArrayList<Customer>();
    }
    
    public ArrayList getCustomerList()
    {
    	return this.customerList;
    }
    
    public void addCustomer(Customer newCustomer)
    {
    	customerList.add(newCustomer);
    }
    
    public boolean removeCustomer(Customer person)
    {
    	int x = customerList.indexOf(person);
    	if(x <= 0)
    	{
    		customerList.remove(x);
    		return true;
    	}
    	else
    		return false;
    }
    
    public void clearCustomerList()
    {
    	customerList.clear();
    }
    
    
    public Customer getCustomerByName(String customerName)
    {
    	for(Customer e: customerList)
    	{
    		if(e.getName().equals(customerName))
    		{
    			return e;
     		}
    	}
    	return null;
    }
    
    public String getAllCustomerNames()
    {
    	String allCustomerNames = " ";
    	for(Customer e: customerList)
    	{
    		allCustomerNames += e.getName() + " ";
    	}	
    	return allCustomerNames;
    }
    
    public ArrayList<Product> productsAssignedToCustomer(String customerName)
    {
    	for(Customer e: customerList)
    	{
    		if(e.getName().equals(customerName))
    		{
    			return e.getCart();
    		}
    	}
   
    }
   
    public String toString()
    {
    	String output = " ";
   	 	for(Customer e: customerList)
   	 	{
   	 		output += e.toString() + "\n";
   	 	}
   	 	return output;
    }
    

    public String saveToFile()
    {
    	String output = " ";
   	 	for(Customer e: customerList)
   	 	{
   	 		output += e.saveToFile() + "\n";
   	 	}
   	 	return output;
    }
    
    public void sortByName()
	 {
	 	for(int i = 1; i < customerList.size(); i++)
	 	{
	 		for(int pos = 0; pos < customerList.size()-i; pos++)
	 		{
	 			if(customerList.get(pos).compareTo(customerList.get(pos + 1)) > 0)
	 			{
	 				Customer temp = customerList.get(pos);
	 				customerList.set(pos, customerList.get(pos+1));
	 				customerList.set(pos+1, temp);
	 			}
	 		}
	 	}
	 }
    
}


