package database_employee;
import java.util.*;

public class DbEmpolyeesDriver {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		DbEmpolyees db = DbEmpolyees.getInstance("scott", "tiger");
		db.bruteOn();
		db.addEmp("Smith", 7369, 20, 7902, "17/12/1980", 800.0, 0, "Clerk");
		db.addEmp("Allen", 7499, 30, 7698, "20/02/1981", 1600.0, 300.0, "Salesman");
		db.addEmp("Ward", 7521, 30, 7698, "22/20/1981", 1250.0, 500.0, "Salesman");
		db.addEmp("Jones", 7566, 20, 7839,"02/04/1981", 2975.0, 0, "Manager");
		db.addEmp("Smith", 7360, 20, 7902, "17/12/1980", 800.0, 0, "Salesman");
		db.addEmp("Blake", 7698, 30, 7839, "01/05/1981", 2850.0,99.9, "Manager");
		db.addEmp("Clark", 7782, 10,7839, "09/06/1981", 2450.0,0.0, "Manager");
		db.addEmp("Scott", 7788, 20, 7566, "19/04/1987",3000.6,0.0, "Analyst");
		db.addEmp("King", 7839,10,-1, "17/11/1981", 5000.0,0.0, "President");
		db.addEmp("Turner", 7446,30,7698, "08/09/1981", 1500.0,0.0, "Salesman");
		db.addEmp("Adams", 7676,20, 7788, "23/05/1987", 1100.0,0.0, "Clerk");
		db.addEmp("James", 7900,30,7698, "03/12/1981",950.0,0.0, "Clerk");
		db.addEmp("Ford", 7902,28,7566, "03/12/1981", 3000.0,0.0,"Analyst");
		db.addEmp("Miller", 7934, 18, 7782, "23/01/1982", 1300.0,0.0, "Clerk");
		db.bruteOff();
		int choice=-1;
		mainLoop:
		do {
			System.out.println();
			System.out.println();
			System.out.println("-----------------------------------------");
			System.out.println("     WELCOME TO THE DATABASE HOMEPAGE    ");
			System.out.println("-----------------------------------------");
			
			System.out.println();
			System.out.println("Choose Options: ");
			System.out.println("1. LOGIN ");
			System.out.println("2. CHECK DATABASE");
			System.out.println("3. ADD AN EMPOLYEE");
			System.out.println("4. Operation and Features");
			System.out.println("5. Quit");
			System.out.println();
			System.out.print("Option: ");
			if(sc.hasNextInt())
			{
				choice = sc.nextInt();
			}
			else {
				System.out.println("Invalid input! Try entering number from 1-5");
				sc.nextLine();
				continue;
			}
			switch(choice)
			{
			case 1:
				db.logIn();
				break;
			case 2:
				db.showDb();
				break;
			case 3:
				if(!db.checkConnection())
				{
					System.out.println("Login Credential Required!!!");
					break;
				}
				System.out.println("Insert Empolyee Information: [name empid deptNo managerId hireDate sal comm jobRole]");
				sc.nextLine();
				String s = sc.nextLine();
				String[] ed = s.split(" ");
				if(ed.length!=8)
				{
					System.out.println("Record is not validly Inserted!!! Try again");
					continue;
				}
				db.addEmp(ed[0],Integer.parseInt(ed[1]),Integer.parseInt(ed[2]),Integer.parseInt(ed[3]),ed[4],Math.round(Float.parseFloat(ed[5]) * 100.0) / 100.0,
						Math.round(Float.parseFloat(ed[6]) * 100.0) / 100.0,ed[7]);
				break;
			case 4:
				newFeatures(db,sc);
				break;
			case 5:
				break mainLoop;
			default:
				System.out.println("ENTER A VALID OPTION!!!");
			}
			
		}while(choice != 5);
		sc.close();
		System.out.println("Thank you for the interaction");
		
	}
	
	
	
	
	
	public static void newFeatures(DbEmpolyees db,Scanner sc)
	{
		if(db.checkConnection())
		{
			int choice=-1;
			do {
				System.out.println();
				System.out.println("-----------------------------------------");
				System.out.println("       CRUDE OPERATIONS ON DATABASE      ");
				System.out.println("-----------------------------------------");
				
				System.out.println();
				System.out.println("Choose Options: ");
				System.out.println("1. FIND EMPOLYEE WITH MAX SALARY");
				System.out.println("2. SORT RECORDS BY ATTRIBUTE");
				System.out.println("3. ");
				System.out.println("4. ");
				System.out.println("5. ");
				System.out.println("6. BACK");
				System.out.println();
				System.out.print("Option: ");
				if(sc.hasNextInt())
				{
					choice = sc.nextInt();
				}
				else {
					System.out.println("Invalid input! Try entering number from 1-5");
					sc.nextLine();
					continue;
				}
				switch(choice)
				{
				case 1:
					db.findMaxSal();
					break;
				case 2:
					try {
						System.out.println("Enter the Attribute: [name,empid,deptno,managerid,sal,comm,jobrole]");
						String s = sc.next();
						db.bubbleSort(s);
						break;
					}
					catch(InputMismatchException ex)
					{
						System.out.println("Wrong input Try Again!!!");
						continue;
					}
				case 3:
					System.out.println("Currently Working on it");
					break;
				case 4:
					System.out.println("Currently Working on it");
					break;
				case 5:
					System.out.println("Currently Working on it");
					break;
				case 6:
					break;
				default:
					System.out.println("ENTER A VALID OPTION!!!");
				}
			}while(choice!=6);
		}
		else
			System.out.println("LOGIN Credential required!!!");
	}
}
