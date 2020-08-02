/**
 * @(#)Product.java
 *
 *
 * @author 
 * @version 1.00 2020/2/25
 */
/**
 * Creates a product Object
 */
public class Product 
{
	private int quantity;
	private String name;
	private String ageDemographic;
	private double price;
	private String genderType;
	private String productId;
	private double productionCostPerUnit;
	
	
	/**
	 * creates a product object with a given name, quantity, price, age demographic, gender type, and product ID
	 * @param productName the name of the product
	 * @param qroductQuantity the quantity of the product in the inventory
	 * @param productAgeDemographic the age demographic that the product is made for
	 * @param productPrice the price of the product
	 * @param productGenderType the type of gender that should use the product
	 * @param productProductId the id of the product
	 * @param productProductionCostPerUnit the production cost for producing a unit of a product
	 */
	public Product(String productName, int productQuantity, double productPrice, String productAgeDemographic, String productGenderType, String productProductId, double productProductionCostPerUnit) 
	{
		quantity = productQuantity;
		name = productName;
		ageDemographic = productAgeDemographic;
		price = productPrice;
		genderType = productGenderType;
		productId = productProductId;
		productionCostPerUnit = productProductionCostPerUnit;
	}
	
	/**
	 * gets the cost of producing a unit of a product
	 * @return the production cost of a unit of a product
	 */
	public double getProductionCostPerUnit() {
		return productionCostPerUnit;
	}

	/**
	 * sets the production cost of a unit of a product to the Product object
	 * @param productProductionCostPerUnit the production cost of a unit of a certain product
	 */
	public void setProductionCostPerUnit(double productProductionCostPerUnit) {
		productionCostPerUnit = productProductionCostPerUnit;
	}

	/**
	 * gets the product ID
	 * @return the productId
	 */
	public String getProductId() 
	{
		return productId;
	}

	/**
	 * sets the product ID
	 * @param productProductId the productId to set
	 */
	public void setProductId(String productProductId) 
	{
		productId = productProductId;
	}
	
	/**
	 * gets the quantity of product in the inventory. 
	 * In the Customer object's cart, this method will get the amount of product that the customer has purchased overall 
	 * @return the quantity of the product
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * sets the quantity of product
	 * In the Customer object's cart, this method will set the amount of product that the customer has purchased overall 
	 * @param productQuantity the quantity to set
	 */
	public void setQuantity(int productQuantity) {
		quantity = productQuantity;
	}

	/**
	 * gets the name of the product
	 * @return the name of the product
	 */
	public String getName() {
		return name;
	}

	/**
	 * sets the name of the product
	 * @param productName the name to set
	 */
	public void setName(String productName) {
		name = productName;
	}

	/**
	 * gets the age demographic of the product
	 * @return the age demographic
	 */
	public String getAgeDemographic() {
		return ageDemographic;
	}

	/**
	 * sets the age demographic of the product
	 * @param productAgeDemographic the age demographic to set
	 */
	public void setAgeDemographic(String productAgeDemographic) 
	{
		ageDemographic = productAgeDemographic;
	}

	/**
	 * gets the price of the product
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * sets the product price
	 * @param productPrice the price to set
	 */
	public void setPrice(double productPrice) {
		price = productPrice;
	}

	/**
	 * gets the gender type of the product
	 * @return the gender type
	 */
	public String getGenderType() {
		return genderType;
	}

	/**
	 * sets the gender type of the product
	 * @param productGenderType the gender type to set
	 */
	public void setGenderType(String productGenderType) {
		genderType = productGenderType;
	}
	
	/**
	 *gets the product objects and prints it out 
	 * @return the Product object
	 */
	
	public String toString()
	{
		return name + " " + quantity + " " + price + " " + ageDemographic + " " + genderType + "  " + productId + " " + productionCostPerUnit;
	}
	
	/**
	 * checks whether two products are equal to each other
	 * @param o an object that is casted to a product object
	 * @return whether the two product objects are equal
	 */
	public boolean equals(Object o)
	{
		Product other = (Product) o;
		return this.name.equals(other.name);
	}
	/**
	 * compares the product IDs of two products and returns the difference
	 * @param o a object that is casted to a product object
	 * @return the difference in the productIDs
	 */
	public int compareTo(Object o)
	{
		Product other = (Product) o;
		return this.productId.compareTo(other.productId);
	}
	
	/**
	 * saves a product object to a file
	 * @return a string version of the product object
	 */
	public String saveToFile()
	{
		return getName() + "\n" + getPrice() + "\n" + getQuantity() + "\n" + getGenderType() 
				+ "\n" + getAgeDemographic() +  "\n" + getProductId() + "\n" + getProductionCostPerUnit() + "\n";
	}
	
	/**
	 * makes a deep copy of a product object for the customer cart
	 * reference from url: https://stackoverflow.com/questions/78536/deep-cloning-objects
	 * @return a copy of the product object
	 */
	public Product deepCopy()
	{
		Product duplicate = new Product(this.name, this.quantity, this.price, this.ageDemographic, 
				this.genderType, this.productId, this.productionCostPerUnit);
		return duplicate;
	}
	
}



