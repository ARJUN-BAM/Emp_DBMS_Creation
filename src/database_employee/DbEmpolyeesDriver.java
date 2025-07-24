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
		db.addEmp("Blake", 7698, 30, 7839, "01/05/1981", 2850.0,0.9, "Manager");
		db.addEmp("Clark", 7782, 10,7839, "09/06/1981", 2450.0,0.0, "Manager");
		db.addEmp("Scott", 7788, 20, 7566, "19/04/1987",3000.6,0.0, "Analyst");
		db.addEmp("King", 7839,10,-1, "17/11/1981", 5000.0,0.0, "President");
		db.addEmp("Turner", 7446,30,7698, "08/09/1981", 1500.0,0.0, "Salesman");
		db.addEmp("Adams", 7676,20, 7788, "23/05/1987", 1100.0,0.0, "Clerk");
		db.addEmp("James", 7900,30,7698, "03/12/1981",950.0,0.0, "Clerk");
		db.addEmp("Ford", 7902,28,7566, "03/12/1981", 3000.0,0.0,"Analyst");
		db.addEmp("Miller", 7934, 18, 7782, "23/01/1982", 1300.0,0.0, "Clerk");
		db.bruteOff();
		int choice;
		mainLoop:
		do{
			//shubham pull request hdshdahas
			System.out.println("-----------------------------------------");
			System.out.println("         WELCOME TO THE DATABASE         ");
			System.out.println("-----------------------------------------");

			System.out.println();
			System.out.println("Choose Options: ");
			System.out.println("1. LOGIN ");
			System.out.println("2. CHECK DATABASE");
			System.out.println("3. ADD AN EMPOLYEE");
			System.out.println("4. FIND EMPOLYEE WITH MAX SALARY");
			System.out.println("5. Quit");
			System.out.println();
			System.out.print("Option: ");
			choice = sc.nextInt();
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
				db.addEmp(ed[0],Integer.parseInt(ed[1]),Integer.parseInt(ed[2]),Integer.parseInt(ed[3]),ed[4],Math.round(Float.parseFloat(ed[5]) * 100.0) / 100.0,Math.round(Float.parseFloat(ed[6]) * 100.0) / 100.0,ed[7]);
				break;
			case 4:
				db.findMaxSal();
				break;
			case 5:
				break mainLoop;
			default:
				System.out.println("ENTER A VALID OPTION!!!");
			}

		}while(choice != 5);

		System.out.println("Thank you for the interaction");

	}
}
