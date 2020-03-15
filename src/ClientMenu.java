import javax.swing.*;
import java.util.Scanner;
import java.io.*;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.HashMap;

public class ClientMenu 
{
	
	static ProductList inventory = new ProductList();
	static CustomerList transactions = new CustomerList();
	static HashMap<String, Customer> listOfCustomers = new HashMap<String, Customer>();
	
	public static void main (String[] args)
	{
		//inventory = loadProductsFromFile();
		dispatch();
		
	}
	
	public static void dispatch()
	{
		boolean done = false;
		while(!done)
		{
			String mainScreen = "1 - Add Transaction" + "\n" + "2 - Return Transaction " + "\n"
					+ "3 - Delete Old Transactions " + "\n" + "4 - Add Product to Inventory" + "\n"
					+ "5 - Edit Product In Inventory" + "\n" + "6 - Delete Product in Inventory" + "\n"
					+ "7 - View all Products in Inventory" + "\n" + "8 - View all Customer Transactions" + "\n"
					+ "9 - Sort Products in Inventory by ID" + "\n" + "10 - View Daily Report" + "\n" + "11 - Log Out";
			String input = JOptionPane.showInputDialog(mainScreen);
			int n = Integer.parseInt(input);
			
			switch(n)
			{
			case 1://ADD TRANSACTION
				ArrayList<Customer> listCustomer = transactions.getCustomerList();
				for(Customer object: listCustomer)
				{
					listOfCustomers.put(object.getName(), object);
				}
				
				String answer = JOptionPane.showInputDialog("Are you a new Customer?(Y/N): ");
				
				if(answer.equals("y"))
				{
					String name = JOptionPane.showInputDialog("What is your Name?: ");
					Customer customer = listOfCustomers.get(name);
					String[] allProducts = inventory.getAllProductNames();
					String chooseProduct = (String)JOptionPane.showInputDialog(
		                    null,
		                    "Select a Product",
		                    "Product Name",
		                    JOptionPane.QUESTION_MESSAGE,
		                    null,						// icon if you have one
		                    allProducts,
		                    allProducts[0]);
					int quantity = Integer.parseInt(JOptionPane.showInputDialog("Quantity of Product:"));
					Product chosenProduct = inventory.getProductByName(chooseProduct);
					customer.addProduct(chosenProduct, quantity);
				}
				else
				{
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
						String customerName = inputCustomerName.getText();
						String customerGender = inputCustomerGender.getText();
						String customerLocation = inputCustomerLocation.getText();
						int customerPhoneNum = Integer.parseInt(inputCustomerPhoneNum.getText());
						String customerEmail = inputCustomerEmail.getText();
						int customerCreditCardNum = Integer.parseInt(inputCustomerCreditCardNum.getText());
						Customer person = new Customer(customerName, customerGender, customerLocation, customerPhoneNum, customerEmail, customerCreditCardNum);
						listOfCustomers.put(person.getName(), person);
						String[] allProducts = inventory.getAllProductNames();
						String chooseProduct = (String)JOptionPane.showInputDialog(
			                    null,
			                    "Select a Product",
			                    "Product Name",
			                    JOptionPane.QUESTION_MESSAGE,
			                    null,						// icon if you have one
			                    allProducts,
			                    allProducts[0]);
						int quantityOfProduct = Integer.parseInt(JOptionPane.showInputDialog("Quantity of Product:"));
						Product inventoryProduct = inventory.getProductByName(chooseProduct);
						int quantity = inventoryProduct.getQuantity() - quantityOfProduct;
						Product productChosen = inventory.getProductByName(chooseProduct);
						person.addProduct(productChosen, quantityOfProduct);
						inventoryProduct.setQuantity(quantity);
						transactions.addCustomer(person);
						saveTransactionsToFile();
						
					}
				}
				break;
			case 2://Return TRANSACTION
				String transactionName = JOptionPane.showInputDialog("What is the Customer Name given for Transaction?:");
				Customer transactionCustomer = listOfCustomers.get(transactionName);
				String productToDelete = JOptionPane.showInputDialog("Type the Name of product that you want to return: ");
				Product deleteProduct = transactionCustomer.getProductPurchacedbyName(productToDelete);
				transactionCustomer.removeProductPurchased(deleteProduct);
				break;
				
			case 3://DELETE TRANSACTION
				String findTransaction = JOptionPane.showInputDialog("What is the Customer Name given for Transaction?: ");
				Customer customerTransaction = listOfCustomers.get(findTransaction);
				transactions.removeCustomer(customerTransaction);
				transactions.saveToFile();
				saveTransactionsToFile();
			
			case 4: //ADD PRODUCT TO INVENTORY
				JTextField inputName = new JTextField();
				JTextField inputQuantity = new JTextField();
				JTextField inputGenderType = new JTextField();
				JTextField inputAgeDemographic = new JTextField();
				JTextField inputPrice = new JTextField();
				JTextField inputProductId = new JTextField();
				
				Object[] inputNewProduct = {"Name: ", inputName, "Quantity: ", inputQuantity, "Gender Type: ", inputGenderType, "Age Demographic: ", inputAgeDemographic, 
						"Price: ", inputPrice, "Product ID: ", inputProductId, };
				int choice = JOptionPane.showConfirmDialog(null, inputNewProduct, "Enter all your values",JOptionPane.OK_CANCEL_OPTION);
				if(choice == JOptionPane.OK_OPTION)
				{
					String productName = inputName.getText();
					int productQuantity = Integer.parseInt(inputQuantity.getText());
					String productGenderType = inputGenderType.getText();
					String productAgeDemographic = inputAgeDemographic.getText();
					double productPrice = Double.parseDouble(inputPrice.getText());
					String productId = inputProductId.getText();
					Product item = new Product(productQuantity, productName, productAgeDemographic, productPrice, productGenderType, productId);
					item.saveToFile();
					inventory.addProduct(item);
					saveProductsToFile();			
				}
				break;
			case 5: // EDIT PRODUCT IN INVENTORY
				String[] productInventory = inventory.getAllProductNames();
				String s = (String)JOptionPane.showInputDialog(
	                    null,
	                    "Select a Product",
	                    "Product Name",
	                    JOptionPane.QUESTION_MESSAGE,
	                    null,						// icon if you have one
	                    productInventory,
	                    productInventory[0]);
				JTextField EditProductName = new JTextField();
				JTextField EditProductQuantity = new JTextField();
				JTextField EditProductGenderType = new JTextField();
				JTextField EditProductAgeDemographic = new JTextField();
				JTextField EditProductPrice = new JTextField();
				JTextField EditProductId= new JTextField();
				
				Product editProduct = inventory.getProductByName(s);
				EditProductName.setText(editProduct.getName());
				EditProductName.setEditable(false);
				EditProductQuantity.setText(String.valueOf(editProduct.getQuantity()));
				EditProductGenderType.setText(editProduct.getGenderType());
				EditProductAgeDemographic.setText(editProduct.getAgeDemographic());
				EditProductPrice.setText(String.valueOf(editProduct.getPrice()));
				EditProductId.setText(editProduct.getProductId());
				Object[] EditedNewProduct = {"Quantity: ", EditProductQuantity, "Gender Type: ", EditProductGenderType, "Age Demographic: ", EditProductAgeDemographic, 
						"Price: ", EditProductPrice, "Product ID: ", EditProductId, }; 
				
				int edit = JOptionPane.showConfirmDialog(null, EditedNewProduct, "Enter all your values",
						JOptionPane.OK_CANCEL_OPTION);
				if(edit == JOptionPane.OK_OPTION)
				{
					editProduct.setQuantity(Integer.parseInt(EditProductQuantity.getText()));
					editProduct.setGenderType(EditProductGenderType.getText());
					editProduct.setAgeDemographic(EditProductAgeDemographic.getText());
					editProduct.setPrice(Double.parseDouble(EditProductPrice.getText()));
					editProduct.setProductId(EditProductId.getText());
					editProduct.saveToFile();
					saveProductsToFile();
				}
				break;
			case 6: //DELETE PRODUCT IN INVENTORY
				String[] productinInventory = inventory.getAllProductNames();
				String productName = (String)JOptionPane.showInputDialog(
	                    null,
	                    "Select a Product",
	                    "Product Name",
	                    JOptionPane.QUESTION_MESSAGE,
	                    null,						// icon if you have one
	                    productinInventory,
	                    productinInventory[0]);
				Product deletedProduct = inventory.getProductByName(productName);
				inventory.removeProduct(deletedProduct);
				inventory.saveToFile();
				saveProductsToFile();
				break;
			case 7: // VIEW ALL PRODUCTS IN INVENTORY
				String output = " ";
				ArrayList<Product> myProducts = inventory.getProductList();
				for(Product e: myProducts)
				{
					output += e.toString() + "\n";
				}
				JOptionPane.showMessageDialog(null, output);
				break;
			case 8://VIEW ALL CUSTOMER TRANSACTIONS
				String out = " ";
				ArrayList<Customer> myTransactions = transactions.getCustomerList();
				for(Customer e: myTransactions)
				{
					out += e.toString() + "\n";
				}
				JOptionPane.showMessageDialog(null, out);
				break;
			case 9: //SORT PRODUCTS
				inventory.sortById();
				break;
			case 10://DAILY REPORT
				
			case 11://LOG OUT
				saveProductsToFile();
				JOptionPane.showMessageDialog(null, "You have been logged out");
				done = true;
				break;
			default:
				System.out.println("Incorrect Choice");
				
			}
		}
		
	}
	
	/*
	public static ProductList loadProductsFromFile() // need to check!!!
	{
		JFileChooser chooser = new JFileChooser();
		if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
		{
			File infile = chooser.getSelectedFile();
		}
		Scanner in = new Scanner(infile); // need to check this line of code with someone!!!
		while(in.hasNext())
		{
			//read in each instance field
			String productName = in.nextLine();
			int productQuantity = in.nextInt();
			in.nextLine();
			String productGenderType = in.nextLine();
			String productAgeDemographic = in.nextLine();
			double productPrice = in.nextDouble();
			String productId = in.nextLine();
			inventory.addProduct(new Product(productQuantity, productName, productAgeDemographic, productPrice, productGenderType, productId));
		}
		return inventory;
	}
	
	public static CustomerList loadTransactionsFromFile() // need to check!!!
	{
		JFileChooser chooser = new JFileChooser();
		if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
		{
			File infile = chooser.getSelectedFile();
		}
		Scanner in = new Scanner(infile); // need to check this line of code with someone!!!
		while(in.hasNext())
		{
			//read in each instance field
			String customerName = in.nextLine();
			String customerGender = in.nextInt();
			String customerLocation = in.nextLine();
			int customerPhoneNum = in.nextInt();
			in.hasNextLine();
			String customerEmail = in.nextLine();
			int customerCreditCardNum = in.nextInt();
			in.nextLine();
			transactions.addCustomer(new Customer(customerName, customerGender, customerLocation, customerPhoneNum, customerEmail, customerCreditCardNum));
		}
		return transactions;
	}
	*/
	public static void saveTransactionsToFile()
	{
		try
	    {
			PrintWriter outFile = new PrintWriter(new File("transactions.txt"));
	        outFile.print(transactions.saveToFile());
	        outFile.close() ;
	    }
	    catch (IOException e)
	    {
	        System.out.println (e);
	    }
	 }
	
	public static void saveProductsToFile()
	{
		try
	    {
			PrintWriter outFile = new PrintWriter(new File("inventory.txt"));
	        outFile.print(inventory.saveToFile());
	        outFile.close() ;
	    }
	    catch (IOException e)
	    {
	        System.out.println (e);
	    }
	 }
	
	
	/*
	public void handleTransactions(Customer person, Product purchase)
	{
		for(Customer e: customerList)
		{
			if(e.equals(person))
			{
				e.addProduct(purchase);
			}
		}
	}
	 */
}
