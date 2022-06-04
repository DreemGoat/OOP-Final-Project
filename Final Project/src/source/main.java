package source;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang.StringUtils;

public class main {

	public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<Stock> stocks = new ArrayList<Stock>();
		People peeps = null;
		ShopService service = new ShopService();
		boolean flag = false;
		boolean flag2 = false;
		boolean flag3 = true;
		try{
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection connection= DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\dep50\\eclipse-workspace\\Database.accdb");//Establishing Connection
            System.out.println("Connected Successfully"); //Microsoft Access connection confirmation
            System.out.println("welcome to Jojamart, would you like to register a new account or login to an existing one?: (1. register | 2.login) ");
            int pick = input.nextInt();
            if (pick ==1) {
            	service.Register(connection);
            } else if (pick ==2) {
            	peeps = service.Login(connection);
            }

            int option = 0;
            while (flag2 == false) {
            	System.out.print("Hello, please enter what you would like to do today. 1 Buying | 2 Inputting:");
            	option = input.nextInt();
            	if(option==2 && peeps!=null && peeps.getType()!=2) {
            		System.out.println("Inputting is not allowed for customers");
            	} else {
            		flag2 = true;
            	}

            }
            		while(flag3 == true) { //loop in case users still want to use the code
            			if(option==1) {
            				service.Buy(connection);
            			}

            			if(option ==2) {
            				service.input(connection);
            			}
            			System.out.println("do you still wish to proceed?"); 
            			String opt = input.nextLine();
            			if(StringUtils.isEmpty(opt)) {
            				opt = input.nextLine();
            			}
            			if (opt.equals("yes")) {
            				flag3 = true;
            			} else if (opt.equals("no")) {
            				flag3 = false;
            			}
            		}
            		System.out.println("Thank you for using our services");
            input.close();
        }catch(Exception e){ //error handling in case Java can't connect to Microsoft Access
        	e.printStackTrace();
            System.out.println("Error in connection");

        }
	}}
