import java.util.ArrayList;

/**
 * @(#)ProductList.java
 *
 *
 * @author Bhargav Akula
 * @version 1.00 2020/2/25
 */
/**
 *
 */
 
public class ProductList 
{
	private ArrayList<Product> ProductList;
	
	/**
	 *
	 */
    public ProductList() 
    {
    	ProductList = new ArrayList<Product>();
    }
    
    public ArrayList getProductList()
    {
    	return this.ProductList;
    }
    /**
     *
     */
    public void addProduct(Product newProduct)
    {
    	ProductList.add(newProduct);
    }
    /**
     *
     */
    public Product getProductByName(String productName)
    {
    	for(Product e: ProductList)
    	{
    		if(e.getName().equals(productName))
    		{
    			return e;
     		}
    	}
    	return null;
    	
    }
    
    public Product getProductById(String productId)
    {
    	for(Product e: ProductList)
    	{
    		if(e.getProductId().equals(productId))
    		{
    			return e;
     		}
    	}
    	return null;
    }
    public void sortById()
	 {
	 	for(int i = 1; i < ProductList.size(); i++)
	 	{
	 		for(int pos = 0; pos < ProductList.size()-i; pos++)
	 		{
	 			if(ProductList.get(pos).compareTo(ProductList.get(pos+1)) > 0)
	 			{
	 				Product temp = ProductList.get(pos);
	 				ProductList.set(pos, ProductList.get(pos+1));
	 				ProductList.set(pos+1, temp);
	 			}
	 		}
	 	}
	 }

    /**
     *
     */
    public String getAllProductIds()
    {
    	String allProductIds = " ";
    	for(Product e: ProductList)
    	{
    		allProductIds += e.getProductId() + " ";
    	}	
    	return allProductIds;
    }
    
    public String[] getAllProductNames()
    {
    	String[] allProductNames = new String[ProductList.size()];
    	for(int i = 0; i < allProductNames.length; i++)
    	{
    		allProductNames[i] = ProductList.get(i).getName();
    	}
    	return allProductNames;
    }
    
    /**
     *
     */
    public void clearProductsFromInventory()
    {
    	ProductList.clear();
    }
    /**
     *
     */
    public boolean removeProduct(Product item)
    {
    	int x = ProductList.indexOf(item);
    	if(x <= 0)
    	{
    		ProductList.remove(x);
    		return true;
    	}
    	else
    		return false;
    }
    /**
     *
     */
    public String saveToFile()
	{
    	String output = " ";
	 	for(Product e: ProductList)
	 	{
	 		output += e.saveToFile() + "\n";
	 	}
	 	return output;
	}
    /**
     * 
     * @return
     */
    public int totalCost()
    {
    	int totalCost = 0;
    	for(Product e: ProductList)
    	{
    		totalCost += e.getQuantity() * e.getPrice();
    	}
    	return totalCost;
    }
    /**
     * 
     */
    public String toString()
    {
    	 String output = " ";
    	 for(Product e: ProductList)
    	 {
    	 	output += e.toString() + "\n";
    	 }
    	 	return output;
    }
    
}
