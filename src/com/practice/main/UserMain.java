package com.practice.main;
import com.practice.methods.Methods;
import com.practice.pojo.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserMain {

	public static void main(String args[]){
		
		Scanner sc = new Scanner(System.in);

		tier0(sc);
		// bean is for processing method
		// pojo is just to parse the data
	}
	public static void tier0(Scanner sc)
	{
		String user = "";
		String pw = "";
		
		//create method object to call dbcontroller
		Methods m = new Methods();
		
		//menu
		System.out.println("1) Login");
		System.out.println("2) Sign Up");
		System.out.println("3) Exit");
		
		System.out.println("Enter you choice: ");
		int input; 
		input = sc.nextInt();
		sc.nextLine();
		switch(input) {
		   case 1 :
			   //login
			   System.out.println("Enter username : ");
			   user=sc.nextLine();
			   System.out.println("Enter password : ");
			   pw=sc.nextLine();
			   boolean check = m.login(user,pw);
			   if(check==true)
			   {
				   System.out.println("Login Successfully\n");
				   tier1(sc);
			   }
			   else
			   {
				   System.out.println("Incorrect user or password\n");
				   tier0(sc);
			   }
		      break;
		   case 2 :
			   //sign up
			   System.out.println("Enter username : ");
			   user=sc.nextLine();
			   System.out.println("Enter password : ");
			   pw=sc.nextLine();
			   int tf = m.signUp(user, pw);
			   if(tf > 0)
				   System.out.println("Successfully signed up.\n");
			   else
				   System.out.println("Failed to register.\n");
			   tier0(sc);
		      break;  
		   case 3 :
			   //exit
			   System.out.println("Program ended");
			   break;
		   default :
	            System.out.println("Invalid input\n");
	            tier0(sc);
		}
	}
	public static void tier1(Scanner sc)
	{
		//list to store all results
    	List<User> result = new ArrayList<User>();
    	
    	//to call the dao for results
    	Methods met = new Methods();
    	
		String user = "";
		String newPW = "";
		
		//create method object to call dbcontroller
		Methods m = new Methods();
				
		//menu
		System.out.println("1) Change Password");
		System.out.println("2) Deactivate Account");
		System.out.println("3) Activate Account");
		System.out.println("4) Show All User");
		System.out.println("5) Show Activated User");
		System.out.println("6) Show Deactivated User");
		System.out.println("7) Previous Menu");
		System.out.println("8) Exit");
		System.out.println("Enter you choice: ");
		int input; 
		input = sc.nextInt();
		sc.nextLine();
		switch(input) {
        case 1 :
        	System.out.println("Enter username : ");
			user=sc.nextLine();
        	System.out.println("Enter new password: ");
        	newPW = sc.nextLine();
        	int tf = m.updatePW(user, newPW);
        	if(tf > 0)
        		System.out.println("Password Updated Successfully\n");
        	else
        		System.out.println("Failed to update password\n");
        	tier1(sc);
           break;
        case 2 :
        	System.out.println("Enter username : ");
        	user=sc.nextLine();
        	if(met.deactivateUser(user)>0)
        		System.out.println(user + " has been deactivated\n");
        	else
        		System.out.println("Fail to deactivate " + user + "\n");
        	tier1(sc);
           break;
        case 3 :
        	System.out.println("Enter username : ");
        	user=sc.nextLine();
        	if(met.activateUser(user)>0)
        		System.out.println(user + " has been activated\n");
        	else
        		System.out.println("Fail to activate " + user + "\n");
        	tier1(sc);
           break;
        case 4 :
        	//show all user        	
    		result = met.showAllUser();
    		if(result.isEmpty())
    		{
    			System.out.println("No Records found\n");
    		}
    		else
    		{
    			//to print the results
    			for(User up: result)
        		{
        			System.out.println("ID: " + up.getId());
        			System.out.println("User: " + up.getUsername());
        			System.out.println("Password: " + up.getPassword());
        			System.out.println("State:" + up.getState());
        			System.out.println("");
        		}
    		}
    		tier1(sc);
           break;
        case 5 :
        	//show all activated user
    		result = met.showActivate();
    		if(result.isEmpty())
    		{
    			System.out.println("No Records found\n");
    		}
    		else
    		{
    			//to print the results
    			for(User up: result)
    			{
    				System.out.println("ID: " + up.getId());
    				System.out.println("User: " + up.getUsername());
    				System.out.println("Password: " + up.getPassword());
    				System.out.println("");
    			}
    		}
    		tier1(sc);
        	break;
        case 6 :
        	//show all deactivated user
        	result = met.showDeactivate();
        	if(result.isEmpty())
    		{
    			System.out.println("No Records found\n");
    		}
    		else
    		{
    			//to print the results
    			for(User up: result)
    			{
    				System.out.println("ID: " + up.getId());
    				System.out.println("User: " + up.getUsername());
    				System.out.println("Password: " + up.getPassword());
    				System.out.println("");
    			}
    		}
        	tier1(sc);
        	break;
        case 7 :
        	//previous menu
        	tier0(sc);
            break;
        case 8 :
        	//exit
        	System.out.println("Program ended");
        	break;
        default :
            System.out.println("Invalid input\n");
            tier1(sc);
     }
	}
}
