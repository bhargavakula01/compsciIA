import java.util.ArrayList;

public class Customer 
{
	private String name;
	private String gender;
	private String location;
	private int phoneNum;
	private String email;
	private int creditCardNum;
	private ArrayList<Product> cart;
	
	
	public Customer(String name, String gender, String location, int phoneNum, String email, int creditCardNum)
	{
		this.name = name;
		this.gender = gender;
		this.location = location;
		this.phoneNum = phoneNum;
		this.email = email;
		this.creditCardNum = creditCardNum;
		cart = new ArrayList<Product>();
	}
	
	public void addProduct(Product customerPurchase, int quantityOfPurchace)
	{
		customerPurchase.setQuantity(quantityOfPurchace);
		cart.add(customerPurchase);
	}
	
	public ArrayList<Product> getCart()
	{
		return this.cart;
	}
	
	public Product getProductPurchacedbyName(String productName)
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
	
	public boolean removeProductPurchased(Product productToDelete)
    {
    	int x = cart.indexOf(productToDelete);
    	if(x <= 0)
    	{
    		cart.remove(x);
    		return true;
    	}
    	else
    		return false;
    }

	/**
	 * @return the name
	 */
	public String getName() 
	{
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}


	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}


	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the phoneNum
	 */
	public int getPhoneNum() {
		return phoneNum;
	}

	/**
	 * @param phoneNum the phoneNum to set
	 */
	public void setPhoneNum(int phoneNum) {
		this.phoneNum = phoneNum;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}


	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}


	/**
	 * @return the creditCardNum
	 */
	public int getCreditCardNum() {
		return creditCardNum;
	}


	/**
	 * @param creditCardNum the creditCardNum to set
	 */
	public void setCreditCardNum(int creditCardNum) {
		this.creditCardNum = creditCardNum;
	}
	
	/**
	 * @param o
	 * @return whehther the two product objects are equal
	 */
	public boolean equals(Object o)
	{
		Customer other = (Customer) o;
		return this.name.equals(other.name);
	}
	/**
	 * @param o
	 * @return the difference in the prices
	 */
	public int compareTo(Object o)
	{
		Customer other = (Customer) o;
		return this.name.compareTo(other.name);
	}
	/**
	 * @return a Customer object
	 */
	public String toString()
	{
		return name + " " + gender + " " + location + " " + phoneNum + " " + email + " " + creditCardNum + " " + cart;
	}
	
	public String saveToFile()
	{
		return name + "\n" + gender + "\n" + location + "\n" + phoneNum + "\n" + email + "\n" + creditCardNum;
	}
	
	
	

}

