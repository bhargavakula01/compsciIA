/**
 * @(#)CustomerList.java
 *
 *
 * @author 
 * @version 1.00 2020/2/25
 */
import java.util.ArrayList;
/**
 *creates an arrayList of customers
 */
public class CustomerList 
{
	private ArrayList<Customer> customerList;
	/**
	 * creates a customerList ArrayList
	 */
    public CustomerList() 
    {
    	customerList = new ArrayList<Customer>();
    }
    /**
     * gets the customerList ArrayList
     * @return the customerList ArrayList
     */
    public ArrayList getCustomerList()
    {
    	return this.customerList;
    }
    /**
     * adds a new customer to the ArrayList
     * @param newCustomer a new customer object
     */
    public void addCustomer(Customer newCustomer)
    {
    	customerList.add(newCustomer);
    }
    /**
     * removes a customer from the ArrayList
     * @param person the customer object going to be removed from the arrayList
     * @return true or false based on whether the object is removed
     */
    public boolean removeCustomer(Customer person)
    {
    	int x = customerList.indexOf(person);
    	if(x >= 0)
    	{
    		customerList.remove(x);
    		return true;
    	}
    	else
    		return false;
    }
    /**
     * clears the customerList
     */
    public void clearCustomerList()
    {
    	customerList.clear();
    }
    /**
     * gets the customer object based on its name
     * @param customerName the name of the customer
     * @return the customer object(or null) based on the name input
     */
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
    /**
     * gets an array of customer names
     * @return an array of customer names
     */
    public String[] getAllCustomerNames()
    {
    	String[] allCustomerNames = new String[customerList.size()];
    	for(int i = 0; i < allCustomerNames.length; i++)
    	{
    		allCustomerNames[i] = customerList.get(i).getName();
    	}
    	return allCustomerNames;
    }
    /**
     * gets the products that are assigned to the customer in the form of an ArrayList
     * @param customerName the name of the customer
     * @return an ArrayList of products that are checkedout to the customer
     */
    public ArrayList<Product> productsAssignedToCustomer(String customerName)
    {
    	for(Customer e: customerList)
    	{
    		if(e.getName().equals(customerName))
    		{
    			return e.getCart();
    		}
    	}
    	return null;  // Customer not in list
    }
    /**
     * prints out the customerList ArrayList
     * @return the customerList ArrayList
     */
    public String toString()
    {
    	String output = " ";
   	 	for(Customer e: customerList)
   	 	{
   	 		output += e.toString() + "\n";
   	 	}
   	 	return output;
    }
    /**
     * saves the customerList arrayList to the file
     * @return a file with the customerList ArrayList
     */
    public String saveToFile()
    {
    	String output = "";
   	 	for(Customer e: customerList)
   	 	{
   	 		output += e.saveToFile() + "\n";
   	 	}
   	 	return output;
    }
    /**
     * sorts the customer objects based on the customer name
     */
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


