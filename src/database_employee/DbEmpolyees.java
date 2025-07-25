package database_employee;

import java.util.Scanner;

public class DbEmpolyees {
	private int count;
	private String userName;
	private String passWord;
	private Empolyees[] emp = new Empolyees[10];
	private Scanner sc = new Scanner(System.in);
	private boolean connection;
	private static DbEmpolyees ins ;
	
	
	

	private DbEmpolyees(String userName, String passWord) {
		super();
		this.userName = userName;
		this.passWord = passWord;
		System.out.println("Database is Created!!!");
		
	}
	
	public static DbEmpolyees getInstance(String userName, String passWord) //FOr Creating a SingleTon class for database;
	{
		if(ins == null)
		{
			ins= new DbEmpolyees(userName,passWord);
			return ins;
		}
		return ins;
	}
	
	public void logIn()
	{
		if(connection)
		{
			System.out.println("You are loggedIn Already!!");
			return;
		}
		int chance = 3;
		do {
			System.out.print("Enter UserName: ");
			String userName = sc.next();
			System.out.print("Enter PassWord: ");
			String passWord = sc.next();
			if(this.userName.equals(userName)&&this.passWord.equals(passWord))
			{
				connection = true;
				
				System.out.println("Database is Logged In");
				break;
			}
			else
			{
				chance--;
				System.out.println("Wrong Credentials!! try again "+chance+" chances remaining!");
			}
		}while(chance>0);
		if(chance==0)
		{
			Empolyees[] temp = new Empolyees[10];
			emp = temp;
			count=0;
			System.out.println("All the data has been deleted due to privacy purpose!!!");
		}
	}
	
	public void showDb()
	{
		if(connection)
		{
			if(count>0)
			{
				for(int i = 0;i<count;i++)
				{
					if(emp[i]!=null)
						System.out.println(emp[i]);
				}
			}
			else
			{
				System.out.println("No Data into the Database!!!");
			}
		}
		else
			System.out.println("Login Credential Required!!");
	}
	
	
	public void addEmp(String name, int empid, int deptNo, int managerId, String hireDate, double sal, double comm,
			String jobRole)
	{
		if(empid>0&&connection)
		{
			if(searchByEmpId(empid)==-1)
			{
				if(count>(0.75*emp.length))
				{
					Empolyees[] temp = new Empolyees[emp.length*2];
					for(int i=0;i<count;i++)
						temp[i] = emp[i];
					emp = temp;
				}
				emp[count++] = new Empolyees(name,empid,deptNo,managerId,hireDate,sal,comm,jobRole);
				System.out.println("Data Added!!");
			}
			else {
				System.out.println("Empolyee with same ID Exist!!!!");
			}
		}
		else {
			System.out.println("No empolyee data is provided or Connection error!");
		}
	}
	
	public int searchByEmpId(int empid)
	{
		if(!connection)
			return -1;
		for(int i = 0;i<count;i++)
		{
			if(emp[i].empid==empid)
				return i;
		}
		return -1;
	}
	
	public boolean checkConnection()
	{
		return connection;
	}
	public void bruteOn()
	{
		connection = true;
	}
	public void bruteOff()
	{
		connection = false;
	}
	
	public void findMaxSal()
	{
		if(!connection)
		{
			System.out.println("need login");
			return;
		}
		double max = 0;
		int index = 0;
		for(int i = 0; i<count;i++)
		{
			if(max<emp[i].sal)
			{
				max = emp[i].sal;
				index = i;
			}
		}
		System.out.println("empolyee with the highest salary: ");
		System.out.println(emp[index]);
	}
	
	
	
	public void sortTable(String s)
	{
		s = s.toLowerCase();
		switch(s)
		{
			case "name":
				break;
			case "empid":
				break;
			case "deptno":
				break;
			case "managerid":
				break;
			case "hiredate":
				break;
			case "sal":
				break;
			case "comm":
				break;
			case "jobrole":
				break;
			default:
				System.out.println("No such Attribute Found!!!");
				break;
		}
	}
	
	public void bubbleSort()
	{
		for(int i = 0;i<emp.length;i++)
		{
			for(int j=0;j<emp.length-1-i;j++)
			{
				if()
			}
		}
	}
	
	
	
	
	
	
}
