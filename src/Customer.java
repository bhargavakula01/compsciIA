/**
 * @(#)Customer.java
 *
 *
 * @author 
 * @version 1.00 2020/2/25
 */
import java.util.ArrayList;
/**
 * Creates a Customer object
 */
public class Customer 
{
	private String name;
	private String gender;
	private String location;
	private String phoneNum;
	private String email;
	private String creditCardNum;
	private ArrayList<Product> cart;
	
	/**
	 * Constructs a customer object with a name, gender, location, phone number, email, credit card number, and a cart of purchases
	 * @param customerName the name of the customer
	 * @param customerGender the customer gender
	 * @param customerLocation the place where the customer lives
	 * @param customerPhoneNum the customer's phone number
	 * @param customerEmail the customer's email
	 * @param customerCreditCardNum the customer's credit card number
	 */
	public Customer(String customerName, String customerGender, String customerLocation, String customerPhoneNum, String customerEmail, String customerCreditCardNum)
	{
		this.name = customerName;
		this.gender = customerGender;
		this.location = customerLocation;
		this.phoneNum = customerPhoneNum;
		this.email = customerEmail;
		this.creditCardNum = customerCreditCardNum;
		cart = new ArrayList<Product>();
	}
	/**
	 * adds a product to the customer's cart
	 * @param productPurchase the products that the customer is purchasing
	 * @param quantityOfPurchace the amount of product that the customer is purchasing
	 */
	public void addProduct(Product productPurchase, int quantityOfPurchase)
	{
		String productName = productPurchase.getName();
		Product inCart = this.getProductPurchasedByName(productName); //checking to see if product is in the cart
		if(inCart == null) // Product not in the cart
		{
			productPurchase.setQuantity(quantityOfPurchase);
			cart.add(productPurchase);
		}
		else // Product in the Cart
		{
			int cartQuantity = inCart.getQuantity();
			inCart.setQuantity(cartQuantity + quantityOfPurchase);
		}
		
	}
	
	/**
	 * gets the ArrayList of products that the customer is purchasing
	 * @return ArrayList of products that the customer is purchasing
	 */
	public ArrayList<Product> getCart()
	{
		return this.cart;
	}
	/**
	 * formats the cart product objects so that they can be saved to a text file
	 * @return a string on the product name and then its quantity
	 */
	public String cartSaveToFile()
	{
		String output = "";
		for(Product e: cart)
		{
			output += e.getName() + " " + e.getQuantity() + " ";
		}
		return output;
	}
	/**
	 * formats the cart product objects so that they can be printed out by the driver
	 * @return a string on the product name and then its quantity
	 */
	public String cartToString()
	{
		String output = "";
		for(Product e: cart)
		{
			output += e.getName() + " " + e.getQuantity() + " ";
		}
		return output;
	}
	/**
	 * creates an array of the product names from the cart of purchases
	 * @return an array of product names that the customer has purchased
	 */
	public String[] getCartProductNames()
	{
		String[] allCartProductNames = new String[this.getCart().size()];
    	for(int i = 0; i < allCartProductNames.length; i++)
    	{
    		allCartProductNames[i] = this.getCart().get(i).getName();
    	}
    	return allCartProductNames;
	}
	/**
	 * gets the product based on the name of the product
	 * @param productName the name of the product that the customer is purchasing
	 * @return whether the product is the customer cart
	 */
	public Product getProductPurchasedByName(String productName)
	{
		for(Product e: cart)
    	{
    		if(e.getName().equals(productName))
    		{
    			return e;
     		}
    	}
    	return null;
	}
	/**
	 * allows for the customer to return a product
	 * @param productToDelete the product that the customer wants to return
	 * @return whether the product was removed from the cart
	 */
	public boolean removeProductPurchased(Product productToDelete)
    {
    	int x = cart.indexOf(productToDelete);
    	if(x >= 0)
    	{
    		cart.remove(x);
    		return true;
    	}
    	else
    		return false;
    }

	/**
	 * gets the name of the customer
	 * @return the name of the customer
	 */
	public String getName() 
	{
		return name;
	}


	/**
	 * sets the customer name
	 * @param customerName customer name to set
	 */
	public void setName(String customerName) {
		name = customerName;
	}


	/**
	 * gets the gender of the customer
	 * @return the customer gender
	 */
	public String getGender() {
		return gender;
	}


	/**
	 * sets the gender of the customer
	 * @param customerGender the customer gender to set
	 */
	public void setGender(String customerGender) {
		gender = customerGender;
	}


	/**
	 * gets the customer location
	 * @return the customer location 
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * sets the customer location
	 * @param custoemrLocation the customer location to set
	 */
	public void setLocation(String customerLocation) {
		location = customerLocation;
	}

	/**
	 * gets the customer phone number
	 * @return the phoneNum of the customer
	 */
	public String getPhoneNum() {
		return phoneNum;
	}

	/**
	 * sets the customer phone number
	 * @param customerPhoneNum the customer phone number to set
	 */
	public void setPhoneNum(String customerPhoneNum) {
		phoneNum = customerPhoneNum;
	}

	/**
	 * gets the customer email address
	 * @return the email of the customer
	 */
	public String getEmail() {
		return email;
	}


	/**
	 * sets the customer email address
	 * @param customerEmail the customer email to set
	 */
	public void setEmail(String customerEmail) {
		email = customerEmail;
	}


	/**
	 * gets the customer credit card information
	 * @return the creditCardNum of the customer
	 */
	public String getCreditCardNum() {
		return creditCardNum;
	}


	/**
	 * sets the customer credit card information
	 * @param customerCreditCardNum the customer creditCardNum to set
	 */
	public void setCreditCardNum(String customerCreditCardNum) {
		creditCardNum = customerCreditCardNum;
	}
	
	/**
	 * compares two customer objects
	 * @param o generic object that is casted into a customer object
	 * @return whether the two product objects are equal
	 */
	public boolean equals(Object o)
	{
		Customer other = (Customer) o;
		return this.name.equals(other.name);
	}
	/**
	 * compares the name of two customer objects and calculates the difference in the object names
	 * @param o a generic object that is casted into a customer object in order to be compared to
	 * @return the difference in the prices
	 */
	public int compareTo(Object o)
	{
		Customer other = (Customer) o;
		return this.name.compareTo(other.name);
	}
	/**
	 * method to print a customer object
	 * @return a Customer object
	 */
	public String toString()
	{
		return name + " " + gender + " " + location + " " + phoneNum + " " + email + " " + creditCardNum + " " + cartToString();
	}
	/**
	 * save customer object to a file
	 * @return the Customer object
	 */
	public String saveToFile()
	{
		return name + "\n" + gender + "\n" + location + "\n" + phoneNum + "\n" + email + "\n" + creditCardNum + "\n" + cartSaveToFile() + "\n";
	}
	
}

