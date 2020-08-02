/**
 * @(#)ClientMenu.java
 *
 *
 * @author
 * @version 1.00 2020/2/25
 */
import javax.swing.*;
import java.util.Scanner;
import java.io.*;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
/**
 * Main class that runs the GUI front end and works with the object classes and list classes
 */
public class ClientMenu 
{
	
	// creates a ProductList ArrayList
	static ProductList inventory = new ProductList();
	//creates a CustomerList ArrayList
	static CustomerList transactions = new CustomerList();
	// variable for calculating business revenue
	static double BUSINESS_REVENUE = 0;
	// variable for calculating business cost
	static double BUSINESS_COST = 0;
	/**
	 * Runs the dispatch methods and the load from file methods
	 * @param args string array that stores arguments 
	 */
	public static void main(String[] args)
	{
		//MUST RUN THE LOAD FROM FILES!!!
		loadProductsFromFile();
		loadTransactionsFromFile();
		runProgram();
		
	}
	/**
	 * runs the front end GUI using JOptionPane for the client to manipulate the Customer and Product list and object classes
	 */
	public static void runProgram()
	{
		ArrayList<Product> inventoryProducts = inventory.getProductList();
		// calculates the business cost(should not change unless adding product, or changing quantity of product)
		for(Product e: inventoryProducts)
		{
			BUSINESS_COST += e.getProductionCostPerUnit() * e.getQuantity();
		}
		boolean done = false;
		while(!done)
		{
			// main menu of option for the client to choose from
			String mainScreen = "1 - Add Transaction" + "\n" + "2 - Return Transaction " + "\n"
					+ "3 - Delete Old Transactions " + "\n" + "4- Edit Transaction Information" + "\n" 
					+ "5 - Add Product to Inventory" + "\n" + "6 - Edit Product In Inventory" + "\n" 
					+ "7 - Delete Product in Inventory" + "\n"+ "8 - View all Products in Inventory" + "\n" 
					+ "9 - View all Customer Transactions" + "\n"+ "10 - Sort Products in Inventory by ID"
					+ "\n" + "11 - View Daily Report" + "\n" + "12 - Log Out";
			String input = JOptionPane.showInputDialog(mainScreen);
			// variable to store client choice on main menu
			int n;
			try 
			{
				//stores client choice on main menu
				n = Integer.parseInt(input);
			} 
			catch (Exception ex) 
			{
				// if number not saved properly, it should break back to the main menu 
				// saves the Product obkects and Customer objects to a text file
				saveProductsToFile();
				saveTransactionsToFile();
				done = false;
				break;
			}
			// switch runs based on chosen number by client
			switch(n)
			{
			case 1://ADD TRANSACTION
				try
				{
					String answer = JOptionPane.showInputDialog("Are you a new Customer?(Y/N): ");
				
					if(answer.equals("n"))// NOT A NEW CUSTOMER(UPDATING TRANSACTION ACCOUNT CART)
					{
						String name = JOptionPane.showInputDialog("What is your Name?: ");
						Customer customer = transactions.getCustomerByName(name);

						if(customer.getName() == null)// If the name was typed incorrectly
						{
							JOptionPane.showMessageDialog(null, "Invalid Customer Name entered. Plz try again");
						}
						else if(customer.getName().equals(name))
						{
							// adds the product that the customer wants to purchase to the Customer object
							//Updates the Customer's cart within his/her account if they have already bought that product
							addProductToCart(customer);
						}
					
					}
					else // IS A NEW CUSTOMER(CREATING NEW TRANSACTION ACCOUNT IN ORDER TO ADD PRODUCTS TO CART)
					{
						// input fields to add information
						JTextField inputCustomerName = new JTextField();
						JTextField inputCustomerGender = new JTextField();
						JTextField inputCustomerLocation = new JTextField();
						JTextField inputCustomerPhoneNum = new JTextField();
						JTextField inputCustomerEmail = new JTextField();
						JTextField inputCustomerCreditCardNum = new JTextField();
						Object[] inputCustomerInfo = {"Name: ", inputCustomerName, "Gender: ", inputCustomerGender, "Location: ", inputCustomerLocation, "Phone No: ", inputCustomerPhoneNum, 
								"Email: ", inputCustomerEmail, "Credit Card Number", inputCustomerCreditCardNum, };
						int option = JOptionPane.showConfirmDialog(null, inputCustomerInfo, "Enter all your values",JOptionPane.OK_CANCEL_OPTION);
						if(option == JOptionPane.OK_OPTION)
						{
							// Create new customer based on input given
							String customerName = inputCustomerName.getText();
							String customerGender = inputCustomerGender.getText();
							String customerLocation = inputCustomerLocation.getText();
							String customerPhoneNum = inputCustomerPhoneNum.getText();
							String customerEmail = inputCustomerEmail.getText();
							String customerCreditCardNum = inputCustomerCreditCardNum.getText();
							Customer customer = new Customer(customerName, customerGender, customerLocation, customerPhoneNum, customerEmail, customerCreditCardNum);
						
							// Add product to customer cart
							//Updates the Customer's cart within his/her account if they have already bought that product
							//Adds a new product to the customer's cart within his/her account if they have never bought that product
							addProductToCart(customer);
						
							// Add customer to transaction list
							transactions.addCustomer(customer);
						
						}
					}
				}
				catch(Exception ex)// error in adding a product to a customer's cart within a transaction account
				{
					JOptionPane.showMessageDialog(null,"error adding products to the cart");
					ex.printStackTrace();
				}
				break;
			case 2://RETURNING PRODUCTS FROM TRANSACTION ACCOUNT
				try
				{
					// drop down menu of the customer names
					String[] customerNames = transactions.getAllCustomerNames();
					String theCustomer = (String)JOptionPane.showInputDialog(
							null,
							"Select a Customer",
							"Customer Name",
							JOptionPane.QUESTION_MESSAGE,
							null,						// icon if you have one
							customerNames,
							customerNames[0]);
					Customer transactionCustomer = transactions.getCustomerByName(theCustomer);
					String[] productInTheCart = transactionCustomer.getCartProductNames();
					// drop down menu of product names within the cart
					String productToDelete = (String)JOptionPane.showInputDialog(
							null,
							"Select a Product",
							"Product Name",
							JOptionPane.QUESTION_MESSAGE,
							null,						// icon if you have one
							productInTheCart,
							productInTheCart[0]);
					if(inventory.getProductByName(productToDelete) == null)
					{
						// if the product is not in the cart(if deleted earlier by client)
						JOptionPane.showMessageDialog(null, "Cannot return product because it is not sole anymore");
						break;
					}
					// gets the product and returns the quantity and updates business revenue
					Product deleteProduct = transactionCustomer.getProductPurchasedByName(productToDelete);
					int quantityToRemove = Integer.parseInt(JOptionPane.showInputDialog("How much do you want to return: "));
					if(quantityToRemove == deleteProduct.getQuantity()) // if return all of a certain product in the cart
					{
						BUSINESS_REVENUE = BUSINESS_REVENUE - (quantityToRemove * deleteProduct.getPrice());
						transactionCustomer.removeProductPurchased(deleteProduct);
						Product inventoryProduct = inventory.getProductByName(productToDelete);
						inventoryProduct.setQuantity(inventoryProduct.getQuantity() + quantityToRemove);
					}
					else // if not returning all of a certain product in the cart
					{
						BUSINESS_REVENUE = BUSINESS_REVENUE - (quantityToRemove * deleteProduct.getPrice());
						deleteProduct.setQuantity(deleteProduct.getQuantity() - quantityToRemove);
						Product inventoryProduct = inventory.getProductByName(productToDelete);
						inventoryProduct.setQuantity(inventoryProduct.getQuantity() + quantityToRemove);
					}
				}
				catch(Exception ex)// error in loading menu
				{
					JOptionPane.showMessageDialog(null,"error deleting customer purchases");
					ex.printStackTrace();
				}
				break;
			case 3://DELETE CUSTOMER TRANSACTION ACCOUNT(IF THE CUSTOMER NO LONGER IS BUYING PRODUCTS FROM THE STORE)
				try
				{
					//drop down menu of the customer names
					String[] customerTransactionName = transactions.getAllCustomerNames();
					if(customerTransactionName.length == 0)
					{
						JOptionPane.showMessageDialog(null, "No Customer Transaction Info to delete");
					}
					else
					{
						String customerString = (String)JOptionPane.showInputDialog(
								null,
								"Select a Customer",
								"Customer Name",
								JOptionPane.QUESTION_MESSAGE,
								null,						// icon if you have one
								customerTransactionName,
								customerTransactionName[0]);
						// removes the customer from the transactions ArrayList
						Customer customerTransaction = transactions.getCustomerByName(customerString);
						transactions.removeCustomer(customerTransaction);
						transactions.saveToFile();
					}
				}
				catch(Exception ex)// error in loading menu
				{
					JOptionPane.showMessageDialog(null,"error deleting customer from the transactions list");
					ex.printStackTrace();
				}
				break;
			case 4: // EDIT CUSTOMER TRANSACTION ACCOUNT INFORMATION
				try
				{
					String[] customerTransactionInfo = transactions.getAllCustomerNames();
					// no customers within the transaction ArrayList
					if(customerTransactionInfo.length == 0)
					{
						JOptionPane.showMessageDialog(null, "No Customer Transaction Info to edit");
					}
					else
					{
						// drop down menu of customer names
						String s = (String)JOptionPane.showInputDialog(
								null,
								"Select a Customer",
								"Customer Name",
								JOptionPane.QUESTION_MESSAGE,
								null,						// icon if you have one
								customerTransactionInfo,
								customerTransactionInfo[0]);
						//brings the customer information in order to be able to edit it
						JTextField EditCustomerName = new JTextField();
						JTextField EditCustomerGender = new JTextField();
						JTextField EditCustomerLocation = new JTextField();
						JTextField EditCustomerPhoneNum = new JTextField();
						JTextField EditCustomerEmail= new JTextField();
						JTextField EditCustomerCreditCardNum= new JTextField();
				
						Customer person = transactions.getCustomerByName(s);
						EditCustomerName.setText(person.getName());
						EditCustomerName.setEditable(false);
						EditCustomerGender.setText(person.getGender());
						EditCustomerLocation.setText(person.getLocation());
						EditCustomerPhoneNum.setText(String.valueOf(person.getPhoneNum()));
						EditCustomerEmail.setText(person.getEmail());
						EditCustomerCreditCardNum.setText(String.valueOf(person.getCreditCardNum()));
						Object[] EditedNewCustomer = {"Gender: ", EditCustomerGender, "Location: ", EditCustomerLocation, "Phone Number: ", EditCustomerPhoneNum, 
								"Email: ", EditCustomerEmail, "Credit Card No: ", EditCustomerCreditCardNum, }; 
				
						int chooseCustomer = JOptionPane.showConfirmDialog(null, EditedNewCustomer, "Enter all your values",
								JOptionPane.OK_CANCEL_OPTION);
						if(chooseCustomer == JOptionPane.OK_OPTION)
						{
							// save new information to customer object
							person.setPhoneNum(EditCustomerPhoneNum.getText());
							person.setGender(EditCustomerGender.getText());
							person.setLocation(EditCustomerLocation.getText());
							person.setCreditCardNum(EditCustomerCreditCardNum.getText());
							person.setEmail(EditCustomerEmail.getText());
							person.saveToFile();
						}
					}
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null,"error loading customer information");
					ex.printStackTrace();
				}
				break;
			case 5: //ADD PRODUCT TO INVENTORY
				try
				{
					// provides text boxes in order to input data
					JTextField inputName = new JTextField();
					JTextField inputQuantity = new JTextField();
					JTextField inputGenderType = new JTextField();
					JTextField inputAgeDemographic = new JTextField();
					JTextField inputPrice = new JTextField();
					JTextField inputProductId = new JTextField();
					JTextField inputProductionCostPerUnit = new JTextField();
				
					Object[] inputNewProduct = {"Name: ", inputName, "Quantity: ", inputQuantity, "Gender Type: ", inputGenderType, "Age Demographic: ", inputAgeDemographic, 
							"Price: ", inputPrice, "Product ID: ", inputProductId, "Production Cost per unit", inputProductionCostPerUnit};
					int choice = JOptionPane.showConfirmDialog(null, inputNewProduct, "Enter all your values",JOptionPane.OK_CANCEL_OPTION);
					if(choice == JOptionPane.OK_OPTION)
					{
						String productName = inputName.getText();
						int productQuantity = Integer.parseInt(inputQuantity.getText());
						String productGenderType = inputGenderType.getText();
						String productAgeDemographic = inputAgeDemographic.getText();
						double productPrice = Double.parseDouble(inputPrice.getText());
						String productId = inputProductId.getText();
						double productProductionCost = Double.parseDouble(inputProductionCostPerUnit.getText());
						Product item = new Product(productName, productQuantity, productPrice, productAgeDemographic, productGenderType, productId, productProductionCost);
						item.saveToFile();
						inventory.addProduct(item);
						// adds the product to the business cost
						BUSINESS_COST = BUSINESS_COST + (item.getProductionCostPerUnit() * item.getQuantity());
					}
				}
				catch(Exception ex)// error in loading menu
				{
					JOptionPane.showMessageDialog(null,"Error adding product to the inventory");
					ex.printStackTrace();
				}
				break;
			case 6: // EDIT PRODUCT IN INVENTORY
				try
				{
					// drop down menu of product names
					String[] productInventory = inventory.getAllProductNames();
					String str = (String)JOptionPane.showInputDialog(
							null,
							"Select a Product",
							"Product Name",
							JOptionPane.QUESTION_MESSAGE,
							null,						// icon if you have one
							productInventory,
							productInventory[0]);
					// shows product data in order to edit
					JTextField editProductName = new JTextField();
					JTextField editProductQuantity = new JTextField();
					JTextField editProductGenderType = new JTextField();
					JTextField editProductAgeDemographic = new JTextField();
					JTextField editProductPrice = new JTextField();
					JTextField editProductId= new JTextField();
					JTextField editProductionCost = new JTextField();
				
					Product editProduct = inventory.getProductByName(str);
					editProductName.setText(editProduct.getName());
					editProductName.setEditable(false);
					editProductQuantity.setText(String.valueOf(editProduct.getQuantity()));
					editProductGenderType.setText(editProduct.getGenderType());
					editProductAgeDemographic.setText(editProduct.getAgeDemographic());
					editProductPrice.setText(String.valueOf(editProduct.getPrice()));
					editProductId.setText(editProduct.getProductId());
					int previousProductQuantity = editProduct.getQuantity();
					int newProductQuantity = 0;
					editProductionCost.setText(String.valueOf(editProduct.getProductionCostPerUnit()));
					Object[] EditedNewProduct = {"Quantity: ", editProductQuantity, "Gender Type: ", editProductGenderType, "Age Demographic: ", editProductAgeDemographic, 
							"Price: ", editProductPrice, "Product ID: ", editProductId, };
					int edit = JOptionPane.showConfirmDialog(null, EditedNewProduct, "Enter all your values",
						JOptionPane.OK_CANCEL_OPTION);
					if(edit == JOptionPane.OK_OPTION)
					{
						// temporarily removes product from business cost
						BUSINESS_COST = BUSINESS_COST - (editProduct.getProductionCostPerUnit() * editProduct.getQuantity());
						// saves new information to the product object
						editProduct.setQuantity(Integer.parseInt(editProductQuantity.getText()));
						newProductQuantity = editProduct.getQuantity();
						editProduct.setGenderType(editProductGenderType.getText());
						editProduct.setAgeDemographic(editProductAgeDemographic.getText());
						editProduct.setPrice(Double.parseDouble(editProductPrice.getText()));
						editProduct.setProductId(editProductId.getText());
						editProduct.setProductionCostPerUnit(Double.parseDouble(editProductionCost.getText()));
						editProduct.saveToFile();
					}
					if(previousProductQuantity > newProductQuantity)
					{
						// if client lowers product quantity the business cost should not decrease 
						BUSINESS_COST = BUSINESS_COST + (editProduct.getProductionCostPerUnit() * previousProductQuantity);
					}
					else
					{
						// if quantity has increased, then the business cost should increase by the amount that it has increased
						BUSINESS_COST = BUSINESS_COST + (editProduct.getProductionCostPerUnit() * editProduct.getQuantity());
					}
				}
				catch(Exception ex)// error in loading menu
				{
					JOptionPane.showMessageDialog(null,"error loading chosen product");
					ex.printStackTrace();
					break;
				}
				break;
			case 7: //DELETE PRODUCT IN INVENTORY
				// drop down menu of product names
				String[] productInInventory = inventory.getAllProductNames();
				String productName = (String)JOptionPane.showInputDialog(
	                    null,
	                    "Select a Product",
	                    "Product Name",
	                    JOptionPane.QUESTION_MESSAGE,
	                    null,						// icon if you have one
	                    productInInventory,
	                    productInInventory[0]);
				//finds product object based on name given
				Product deletedProduct = inventory.getProductByName(productName);
				// Deletes the Product from the Cart of the Customer when the Client removes a product from the inventory
				// Allows the Client to see Customer Purchases based on Products currently being sold
				ArrayList<Customer> allCustomerTransactionAccounts = transactions.getCustomerList();
				for(int i = 0; i < allCustomerTransactionAccounts.size(); i++)
				{
					for(int j = 0; j < allCustomerTransactionAccounts.get(i).getCart().size(); j++)
					{
						if(allCustomerTransactionAccounts.get(i).getCart().get(j).equals(deletedProduct))
						{
							allCustomerTransactionAccounts.get(i).removeProductPurchased(deletedProduct);
						}
					}
				}
				// removes the product from the inventory ArrayList
				inventory.removeProduct(deletedProduct);
				break;
			case 8: // VIEW ALL PRODUCTS IN INVENTORY
				try 
				{
					// prints all the products in the inventory ArrayList
					String output = "";
					ArrayList<Product> myProducts = inventory.getProductList();
					for(Product e: myProducts)
					{
						output += e.toString() + "\n";
					}
					JOptionPane.showMessageDialog(null, output);
				}
				catch(Exception ex)// error in loading menu
				{
					JOptionPane.showMessageDialog(null,"error in showing the inventory");
					ex.printStackTrace();
				}
				break;
			case 9://VIEW ALL CUSTOMER TRANSACTIONS
				try 
				{	
					String[] customerTransactionNames = transactions.getAllCustomerNames();
					// if no customers in the transactions ArrayList
					if(customerTransactionNames.length == 0)// No Customer transactions accounts within the program
					{
						JOptionPane.showMessageDialog(null, "no customer transactions made");
					}
					else // if there are customer transactions accounts within the program
					{
						// drop down menu of customer names
						String customerName = (String)JOptionPane.showInputDialog(
								null,
								"Select a Customer",
								"Customer Name",
								JOptionPane.QUESTION_MESSAGE,
								null,						// icon if you have one
								customerTransactionNames,
								customerTransactionNames[0]);
						// finds customer object based on name given
						Customer customer = transactions.getCustomerByName(customerName);
						// prints the products that the customer has bought with that transaction account
						JOptionPane.showMessageDialog(null, customer.cartToString());
					}
				}
				catch(Exception ex)// error loading menu
				{
					JOptionPane.showMessageDialog(null,"error loading customer's cart");
					ex.printStackTrace();
				}
				break;
			case 10: //SORT PRODUCTS
				// uses a bubble sort algorithm to sort products by ID
				inventory.sortById();
				break;
			case 11://DAILY REPORT
				// shows the profits made for that day
				JOptionPane.showMessageDialog(null, "Business Profit For That Day: " + (BUSINESS_REVENUE - BUSINESS_COST));
				break;
			case 12://LOG OUT
				// saves the inventory ArrayList and transactions ArrayList to a text file
				saveProductsToFile();
				saveTransactionsToFile();
				JOptionPane.showMessageDialog(null, "You have been logged out");
				// closes the program
				done = true;
				break;
			default: // IF NOT PART OF THE MAIN MENU
				JOptionPane.showMessageDialog(null, "Incorrect Choice");
				
			}
		}
		
	}

	/**
	 * gets the Transaction.txt file and then convents the data into Customer objects 
	 * and then stores it into the Transactions ArrayList.
	 * @return the transactions ArrayList
	 */
	public static CustomerList loadTransactionsFromFile()
	{
		Scanner inside;
		try 
		{
			//the text file that that loads the Customer Objects is the transactions.txt
			//MUST HAVE TWO EMPTY LINES AT THE BOTTOM OF TEXT FILE
			inside = new Scanner(new File("transactions.txt"));
			while(inside.hasNext())
			{
				//read in each instance field
				String customerName = inside.nextLine();
				String customerGender = inside.nextLine();
				String customerLocation = inside.nextLine();
				String customerPhoneNum = inside.nextLine();
				String customerEmail = inside.nextLine();
				String customerCreditCardNum = inside.nextLine();
				Customer person = new Customer(customerName, customerGender, customerLocation, customerPhoneNum, 
						customerEmail, customerCreditCardNum);
				// Adds the customer information to the transactions ArrayList
				transactions.addCustomer(person);
				// Scans the line with the products that the customer has purchased
				Scanner scanCart = new Scanner(inside.nextLine());
				while(scanCart.hasNext())
				{
					// Scans the cart of purchases within the Transaction account
					String cartProductName = scanCart.next(); 
					int cartProductQuantity = scanCart.nextInt();
					Product cartProduct = inventory.getProductByName(cartProductName).deepCopy();
					person.addProduct(cartProduct, cartProductQuantity);	
				}
				inside.nextLine();			}
		}
		catch(Exception ex)
		{
			//<essage if there was an error loading the customers from the text file
			JOptionPane.showMessageDialog(null,"error loading customers");
			ex.printStackTrace();
		}
		return transactions;
		
	}
	
	/**
	 * loads the inventory.txt file and converts the data into Product Objects which are then stored into the inventory ArrayList
	 * @return the inventory ArrayList
	 */
	public static ProductList loadProductsFromFile()
	{
		Scanner in; 
		try 
		{
			//the text file that that loads the Product Objects is the inventory.txt
			//MUST HAVE TWO EMPTY LINES AT THE BOTTOM OF TEXT FILE
			in = new Scanner(new File("inventory.txt"));
			while(in.hasNext())
			{
				//read in each instance field
				String productName = in.nextLine();
				double productPrice = in.nextDouble();
				in.nextLine();
				int productQuantity = in.nextInt();
				in.nextLine();
				String productGenderType = in.nextLine();
				String productAgeDemographic = in.nextLine();
				String productId = in.nextLine();
				double productionCost = in.nextDouble();
				in.nextLine();
				in.nextLine();
				inventory.addProduct(new Product(productName, productQuantity, productPrice, productAgeDemographic, 
						productGenderType, productId, productionCost));
			}
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null,"error loading products");
			ex.printStackTrace();
		}
		//saveProductsToFile();
		return inventory;
		
	}
	/**
	 * Adds the products selected to the customer's cart
	 * @param customer the Customer object which the product is going to 
	 */
	public static void addProductToCart(Customer customer)
	{
		// Drop down menu of the products that are in the inventory at the moment
		String[] allProducts = inventory.getAllProductNames();
		String chooseProduct = (String)JOptionPane.showInputDialog(
                null,
                "Select a Product",
                "Product Name",
                JOptionPane.QUESTION_MESSAGE,
                null,						// icon if you have one
                allProducts,
                allProducts[0]);
		int quantity = Integer.parseInt(JOptionPane.showInputDialog("Quantity of Product: "));
		Product inventoryProduct = inventory.getProductByName(chooseProduct);
		Product cartProduct = inventoryProduct.deepCopy();
		// Update quantity for both inventory and cart 
		inventoryProduct.setQuantity(inventoryProduct.getQuantity() - quantity);
		if(inventoryProduct.getQuantity() <= 0)
		{
			int actualQuantity = quantity + inventoryProduct.getQuantity();
			customer.addProduct(cartProduct,actualQuantity);
			inventoryProduct.setQuantity(0);
			BUSINESS_REVENUE = BUSINESS_REVENUE + (actualQuantity * inventoryProduct.getPrice());
			JOptionPane.showMessageDialog(null, "no more of product: " + inventoryProduct.getName());
		}
		else
		{
			customer.addProduct(cartProduct, quantity);
			BUSINESS_REVENUE = BUSINESS_REVENUE + (quantity * inventoryProduct.getPrice());
		}
	}
	/**
	 * saves the Customer Objects to the transactions.txt file
	 */
	public static void saveTransactionsToFile()
	{
		try
	    {
			// uses the PrintWriter object to save the Customer objects to the transactions text file
			PrintWriter outFile = new PrintWriter(new File("transactions.txt"));
	        outFile.print(transactions.saveToFile());
	        outFile.close();
	    }
	    catch (IOException e)
	    {
	        System.out.println (e);
	    }
	 }
	/**
	 * saves the Product objects to the inventory.txt file
	 */
	public static void saveProductsToFile()
	{
		try
	    {
			// saves the PrintWriter object to the inventory text file
			PrintWriter outFile = new PrintWriter(new File("inventory.txt"));
	        outFile.print(inventory.saveToFile());
	        outFile.close();
	    }
	    catch (IOException e)
	    {
	        System.out.println (e);
	    }
	 }
}
