package source;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShopService implements Shop{ //ShopService class that contains the functions used in the main function
    Scanner input = new Scanner(System.in);
    boolean flag = false;
    boolean flag2 = true;
    People peeps = null;


	public People Login(Connection connection) throws SQLException { //Login code
		while(flag == false) { //Loop in case user enters the wrong username or password
    		System.out.print("Enter username:");
    		String user = input.nextLine();

    		System.out.print("Enter password: ");
    		String pass = input.nextLine();

    		String sql3 = "SELECT * FROM People where Username = ? AND Password = ?";
    		PreparedStatement preparedStatement=connection.prepareStatement(sql3);
    		preparedStatement.setString(1, user);
    		preparedStatement.setString(2, pass);
    		ResultSet result = preparedStatement.executeQuery(); //checks the database if the password and username are the same

    		if (result.next() == false) {
    			System.out.println("your Username or Password is wrong, please re-enter username or password");
    		} else {
    			do {
    				int id = result.getInt("ID");
    				String fullname = result.getString("Username");
    				String password = result.getString("Password");
    				int type = result.getInt("Type");
    				String role = "";
    				if (type == 1) {
    					role = "Customer";
    				} else if(type==2) {
    					role = "Supervisor";
    				}
    				peeps = new People(fullname, password, id, type);
    				System.out.println("User ID = "+ peeps.getID() + ", Name = "  + peeps.getName()+ ", Role = " + role);
    				flag=true; //prints out the User's details as well as ends the loop
    			} while (result.next());
    		}
    	}
		return peeps;
	}


	public void Register(Connection connection) throws SQLException{ //Registration code

    	System.out.print("Enter username:");
    	String user = input.nextLine();

    	System.out.println("Enter password: ");
    	String pass = input.nextLine();

    	System.out.println("Enter type (1. customer | 2. supervisor): ");
    	int type = input.nextInt();

    	while (type != 1 && type != 2) {
    		System.out.println("Invalid type, please re-enter (1. Customer | 2. Supervisor): ");
    		type = input.nextInt(); //in case the user enters anything other than a 1 or 2
    	}

    	String sql2 = "INSERT INTO People(Username, Password, Type) values(?,?,?)";
    	PreparedStatement preparedStatement=connection.prepareStatement(sql2);
    	preparedStatement.setString(1, user);
    	preparedStatement.setString(2, pass);
    	preparedStatement.setInt(3, type);
    	preparedStatement.executeUpdate();
    	System.out.println("New User added"); //enters the data into the Microsoft Access Database
		
	}
	
	public void Buy(Connection connection) throws SQLException {
		String sql = "SELECT * FROM Stock";
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery(sql);
		List<Stock> stocks = new ArrayList<Stock>();
		
		while (result.next()) {
			int id = result.getInt("ID");
			String item = result.getString("Item");
			int quantity = result.getInt("Quantity");
			int price = result.getInt("Price");
			Stock stock = new Stock(item, quantity, price, id);
			stocks.add(stock); //pulling data from the database
		}

		for (Stock tock : stocks) {
			System.out.println(tock.toString()); //lists the items that are available
		};
		System.out.println("What would you like to buy?(type in the ID): ");
		int iddd = input.nextInt();
		
		String sql3 = "SELECT * FROM Stock where ID = ? ";
		PreparedStatement preparedStatement=connection.prepareStatement(sql3);
		preparedStatement.setInt(1, iddd);
		ResultSet result1 = preparedStatement.executeQuery();
		result1.next();
		iddd = result1.getInt("ID");
		String item = result1.getString("Item");
		int quantity = result1.getInt("Quantity");
		int price = result1.getInt("Price"); //re-checking the database for the user's input
		Stock stock = new Stock(item, quantity, price, iddd);
		System.out.println(stock.toString());
		System.out.println("how much of " + item + " do you want to buy?");
		int amnt = input.nextInt();
		while (quantity<amnt) {
			System.out.println("The amount you want ot buy exceeds the current stock");
			System.out.println("The current amount we have is " + quantity);
			System.out.println("Please re-enter a valid amount");
			amnt = input.nextInt(); //loop that prevents the user from buying more than the available stock
		}

		String sql2 = "UPDATE Stock set Quantity = (Quantity-?) where ID = ?";
		PreparedStatement preparedStatement2=connection.prepareStatement(sql2);
		preparedStatement2.setInt(1, amnt);
		preparedStatement2.setInt(2, iddd);
		preparedStatement2.executeUpdate();
		System.out.println("Thank you for your purchase!"); 
		//edits the database by subtracting the total amount of stock by the amount that the user inputted
	}



	public void input(Connection connection) throws SQLException {
		System.out.print("input item ID: ");
		int id = input.nextInt();

		String sql3 = "SELECT * FROM Stock where ID = ? ";
		PreparedStatement preparedStatement=connection.prepareStatement(sql3);
		preparedStatement.setInt(1, id);
		ResultSet result = preparedStatement.executeQuery(); //checks if the ID is in the database
		if (result.next() == false) {
			System.out.println("Id not found, insert new item: ");
			String nemx = input.nextLine();
			String nem = input.nextLine(); //if the id is not found the user will input a new item

			System.out.println("Insert quanitity:");
			int amount = input.nextInt();

			System.out.println("Insert price: ");
			int cost = input.nextInt();

			String sql2 = "INSERT INTO Stock(Item, Quantity, Price) values(?,?,?)";
			PreparedStatement preparedStatement2=connection.prepareStatement(sql2);
			preparedStatement2.setString(1, nem);
			preparedStatement2.setInt(2, amount);
			preparedStatement2.setInt(3, cost);
			preparedStatement2.executeUpdate();
			System.out.println("Item added to database"); //adds the new item and its details into the database

		} else {
			do {
				int idd = result.getInt("ID");
				String item = result.getString("Item");
				int quantity = result.getInt("Quantity");
				int price = result.getInt("Price");
				Stock stock = new Stock(item, quantity, price, idd);
				System.out.println("edit: "+stock.toString());
				System.out.println("how much of " + item + " do you want to add?"); 
				int amnt = input.nextInt(); 

				String sql2 = "UPDATE Stock set Quantity = (Quantity+?) where ID = ?";
				PreparedStatement preparedStatement2=connection.prepareStatement(sql2);
				preparedStatement2.setInt(1, amnt);
				preparedStatement2.setInt(2, idd);
				preparedStatement2.executeUpdate();
				System.out.println("Item editted");
				//edits the database by adding the quantity of items based on the user's input
			} while (result.next());
		}
	
	}
}
