public class Product 
{
	private int quantity;
	private String name;
	private String ageDemographic;
	private double price;
	private String genderType;
	private String productId;
	
	/**
	 * @return the productId
	 */
		public String getProductId() 
		{
			return productId;
		}

	/**
	 * @param productId the productId to set
	 */
		public void setProductId(String productId) 
		{
			this.productId = productId;
		}
	
	/**
	 * @param quantity
	 * @param name
	 * @param age_demographic
	 * @param price
	 * @param genderType
	 */
	public Product(int quantity, String name, String ageDemographic, double price, String genderType, String productId) 
	{
		this.quantity = quantity;
		this.name = name;
		this.ageDemographic = ageDemographic;
		this.price = price;
		this.genderType = genderType;
		this.productId = productId;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the age_deomgraphic
	 */
	public String getAgeDemographic() {
		return ageDemographic;
	}

	/**
	 * @param ageDemographic the ageDemographic to set
	 */
	public void setAgeDemographic(String ageDemographic) 
	{
		this.ageDemographic = ageDemographic;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the genderType
	 */
	public String getGenderType() {
		return genderType;
	}

	/**
	 * @param genderType the genderType to set
	 */
	public void setGenderType(String genderType) {
		this.genderType = genderType;
	}
	/**
	 * @return the Product object
	 */
	
	public String toString()
	{
		return "Name: " + name + " " + "Quantity: " + quantity + " " + "Price: " + price + " " + "Age Demographic: " + ageDemographic + " " + "Gender Type: " + genderType + "  " + "Product ID: " + productId;
	}
	
	/**
	 * @param o
	 * @return whehther the two product objects are equal
	 */
	public boolean equals(Object o)
	{
		Product other = (Product) o;
		return this.name.equals(other.name);
	}
	/**
	 * 
	 * @param o
	 * @return the difference in the productIDs
	 */
	public int compareTo(Object o)
	{
		Product other = (Product) o;
		return this.productId.compareTo(other.productId);
	}
	
	public String saveToFile()
	{
		return getName() + "\n" + "$" + getPrice() + "\n" + getQuantity() + "\n" + getGenderType() + "\n" + getAgeDemographic() +  "\n" + getProductId() + "\n";
	}
	
}



