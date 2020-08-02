/**
 * @(#)ProductList.java
 *
 *
 * @author
 * @version 1.00 2020/2/25
 */

import java.util.ArrayList;


/**
 * creates an ArrayList of Products
 */
public class ProductList 
{
	private ArrayList<Product> productList;
	
	/**
	 *constructs an empty ArrayList
	 */
    public ProductList() 
    {
    	productList = new ArrayList<Product>();
    }
    /**
     * gets the ProductList ArrayList
     * @return the product list
     */
    public ArrayList getProductList()
    {
    	return this.productList;
    }
    /**
     *adds a new product to the ProductList
     */
    public void addProduct(Product newProduct)
    {
    	productList.add(newProduct);
    }
    /**
     *gets the product based on the name of the product
     *@param productName the name of the product
     *@return the product based on the product name
     */
    public Product getProductByName(String productName)
    {
    	for(Product e: productList)
    	{
    		if(e.getName().equals(productName))
    		{
    			return e;
     		}
    	}
    	return null;
    }
    
    /**
     * gets the product based on the product ID
     * @param productId the id of the product
     * @return the product based on the productId
     */
    public Product getProductById(String productId)
    {
    	for(Product e: productList)
    	{
    		if(e.getProductId().equals(productId))
    		{
    			return e;
     		}
    	}
    	return null;
    }
    
    /**
     * sorts the products based on their product ID
     */
    public void sortById()
	{
    	for (int i = 1; i < productList.size(); i++)
    	{
			for (int pos = 0; pos < productList.size() - i; pos ++)
				if (productList.get(pos).compareTo(productList.get(pos+1))>0)
				{
					Product temp = productList.get(pos);
					productList.set(pos, productList.get(pos+1));
					productList.set(pos+1,temp);
				}
    	} 
    }

    /**
     * gets all product IDs and adds them to an array
     * @return the product IDs in an array format
     */
    public String[] getAllProductIds()
    {
    	String[] allProductIds = new String[productList.size()];
    	for(int i = 0; i < allProductIds.length; i++)
    	{
    		allProductIds[i] = productList.get(i).getProductId();
    	}
    	return allProductIds;
    }
    /**
     * gets all the product names and adds them to an array
     * @return the array of product names
     */
    public String[] getAllProductNames()
    {
    	String[] allProductNames = new String[productList.size()];
    	for(int i = 0; i < allProductNames.length; i++)
    	{
    		allProductNames[i] = productList.get(i).getName();
    	}
    	return allProductNames;
    }
    
    /**
     *clears all products from the ProductList ArrayList
     */
    public void clearProductsFromInventory()
    {
    	productList.clear();
    }
    /**
     *removes a product from the ArrayList
     *@param item is an item in the ArrayList that is being removed
     */
    public boolean removeProduct(Product item)
    {
    	int x = productList.indexOf(item);
    	if(x >= 0)
    	{
    		productList.remove(x);
    		return true;
    	}
    	else
    		return false;
    }
    /**
     *saves the ProductList ArrayList to a file
     */
    public String saveToFile()
	{
    	String output = "";
	 	for(Product e: productList)
	 	{
	 		output += e.saveToFile() + "\n";
	 	}
	 	return output;
	}
    /**
     * prints the ProductList ArrayList
     */
    public String toString()
    {
    	 String output = " ";
    	 for(Product e: productList)
    	 {
    	 	output += e.toString() + "\n";
    	 }
    	 	return output;
    }
    
}
