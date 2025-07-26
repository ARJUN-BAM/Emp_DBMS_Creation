package database_employee;

import java.util.Arrays;
import java.util.Scanner;



public class DbEmpolyees {
	private int count;
	private String userName;
	private String passWord;
	private Empolyees[] emp = new Empolyees[10];
	private Empolyees[][] rollback = new Empolyees[10][];
	private int[] countRollback = new int[10];
	private int savepoints = 1;
	private Scanner sc = new Scanner(System.in);
	private boolean connection;
	private static DbEmpolyees ins ;
	private boolean intialSavepoint = false;
	
	

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
	
	
	
	public int checkSwap(Empolyees emp1 , Empolyees emp2,String s)
	{
		s = s.toLowerCase();
		switch(s)
		{
			case "name":
				return emp1.name.toLowerCase().compareTo(emp2.name.toLowerCase());
				
			case "empid":
				return checkAtt(emp1.empid,emp2.empid);
			case "deptno":
				return checkAtt(emp1.deptNo,emp2.deptNo);
			case "managerid":
				return checkAtt(emp1.managerId,emp2.managerId);
//			case "hiredate":
//				break;
			case "sal":
				return checkAtt(emp1.sal,emp2.sal);
			case "comm":
				return checkAtt(emp1.comm,emp2.comm);
			case "jobrole":
				return emp1.jobRole.toLowerCase().compareTo((emp2.jobRole).toLowerCase());
			default:
				System.out.println("No such Attribute Found!!!");
				return -1;
		}
	}
	
	public void bubbleSort(String s)
	{
		Empolyees[] temp = new Empolyees[count];
		for(int i=0;i<count;i++)
			temp[i] = emp[i];
		for(int i = 0;i<temp.length;i++)
		{
			for(int j=0;j<temp.length-1-i;j++)
			{
				if(checkSwap(temp[j],temp[j+1],s)>0)
				{
					Empolyees var = temp[j];
					temp[j] = temp[j+1];
					temp[j+1]=var;
				}
			}
		}
		for(int i = 0;i<count;i++)
		{
			System.out.println(temp[i]);
		}
	}
	
	public int checkAtt(double emp1,double emp2)
	{
		if(emp1<emp2)
			return 0;
		else if(emp1>emp2)
			return 1;
		else
			return -1;
	}
	
	
	
	public void deleteById(int id)
	{
		int index = searchByEmpId(id);
		if(index>0)
		{
			Empolyees[] temp = new Empolyees[count-1];
			for(int i=0;i<count;i++)
				if(i!=index)
					temp[i]=emp[i];
			System.out.println("The data of the empolyee: "+emp[index].name+" with id: "+emp[index].empid+" is deleted!!");
			countRollback[savepoints] = count-1;
			rollback[savepoints++] = emp;
			emp = temp;
			count--;
		}
		else
		{
			System.out.println("Empolyee not found!!!");
		}
		
	}
	
	public void setIntialCommit()
	{
		if(connection)
		{
			if(!intialSavepoint)
			{
				rollback[0] = emp;
				countRollback[0] = count;
			}
			else
				System.out.println("Intial savepoint is already saved!!");
		}
		else
			System.out.println("Need login Credentials!!!");
	}
	public void gitLog()
	{
		if(connection)
		{
			for(int i = 0;i<savepoints;i++)
				if(rollback[i]!=null&&countRollback[i]>0)
				{
					System.out.println("Savepoint "+(i+1)+ " "+countRollback[i]);
				}
		}
		else
			System.out.println("LOgin Credentials Needed");
	}
	public void rollback()    //Only works when a record is delete. Can't rollback a added value yet.
	{
		if(connection)
		{
			System.out.print("Enter steps for rollback: ");
			int step = sc.nextInt();
			System.out.println(savepoints);
			if(step<savepoints)
			{
				emp = rollback[savepoints-step];
				count = countRollback[savepoints-step-1];
				System.out.println("Successfully Rollbacked!!!");
				for(int i=savepoints-step;i<savepoints;i++)
				{
					rollback[i] = null;
					countRollback[i] = 0;
					savepoints--;
				}
				System.out.println(count);
			}
			else
				System.out.println("can't rollback more than maximum savepoints: "+(savepoints-1));
		}
		else
		System.out.println("Login Credential Needed!!!");
	}
	
	
	public void commit()
	{
		if(connection)
		{
			Arrays.fill(null, rollback);
			int temp = countRollback[0];
			Arrays.fill(null, countRollback);
			countRollback[0] = temp;
			rollback[0] = emp;
			savepoints = 1;
			System.out.println("The Transaction done are committed Successfully!!!");
		}
		else
		{
			System.out.println("NEED LOGIN CREDENTIALS!!!");
		}
	}
}
