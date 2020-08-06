/*
 * Rutvika Pravin Patil
 * 2426 SY COMP B2
 * 
 * ASSIGNMENT:File Organization
 */



package fileorganization;


import java.io.Serializable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;


class Student implements Serializable//Serializable is Interface
{
	
	String name;
	int marks;
	int roll;
	
	Student(int r,int m,String s)//constructor
	{
		roll=r;
		marks=m;
		name=s;
	}
}





public class mainclass {
	
	public static void readF()
	{
		try
		{   //creation of object of fileinputstream && objectinputstream
			FileInputStream file=new FileInputStream("FILE.txt");
		     ObjectInputStream objip=new ObjectInputStream(file);
             //Vector
		     Vector<Student> deserial=(Vector<Student>)objip.readObject();
		     //iterator 
		     Iterator<Student> iter =deserial.iterator();
		     
		     
		     while(iter.hasNext())
		     {
		    	    Student s=iter.next();
		    	    System.out.println("\n>>Name = " + s.name);
					System.out.println(" Roll no = " + s.roll);
					System.out.println(" Marks " + s.marks);
		     }
		     
		     file.close();
		     objip.close();
		     //fileclosed
		     
		     System.out.println("File Data Read Completed");
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		} catch (ClassNotFoundException et) {

			et.printStackTrace();
		}
	}
	
	public static void searchrec(Scanner sc)
	{
		System.out.println("Enter Roll No:");
		int rollno=sc.nextInt();
		int flag=0;
		
		try {
			//object creation fileinputstream, ObjectInputStream
			FileInputStream file = new FileInputStream("FILE.txt");
			ObjectInputStream objip=new ObjectInputStream(file);
			Vector<Student> deserial=(Vector<Student>)objip.readObject();
			//iterator
			Iterator<Student> iter=deserial.iterator();
			 
			
			Student s=new Student(0,0,"NONEXISTANT");
			while(iter.hasNext())
			{
				s=iter.next();//next rec
				
				if(s.roll==rollno)//Data match
				{
					flag=1;
					break;
				}
			}
			
			
			if(flag==1)//if found
			{
				System.out.println("\n>>Name = " + s.name);
				System.out.println(" Roll no = " + s.roll);
				System.out.println(" Marks =" + s.marks);
			}
			else
			{
				System.out.println("Record does not Exist");
			}
			
			file.close();
			objip.close();
			
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		 catch (ClassNotFoundException e) 
		{

				e.printStackTrace();
		}
	}
	
	
	
	
	public static void deleterec(Scanner sc, File f)//For record deletion
	{
		System.out.println("Enter Roll No:");
		int rollno=sc.nextInt();
		int flag=0;
		
		try
		{
			//object creation fileinputstream, ObjectInputStream
			FileInputStream file = new FileInputStream("FILE.txt");
			ObjectInputStream objip=new ObjectInputStream(file);
			Vector<Student> deserial=(Vector<Student>)objip.readObject();
			//Iterator
			Iterator<Student> iter=deserial.iterator();
			Vector<Student> v=new Vector<Student>();
			
			Student s=new Student(0,0,"NONEXISTANT");
			
			while(iter.hasNext())
			{
				s=iter.next();
				if(s.roll!=rollno)
				{
					v.addElement(s);//add to list of record
				}
				else
				{
					flag=1;
				}
			}
			
			FileOutputStream fop = new FileOutputStream(f);

			// object of object output stream
			ObjectOutputStream oops = new ObjectOutputStream(fop);

			// write in the file
			oops.writeObject(v);

			
			fop.close();
			oops.close();
			
			if(flag==0)//Not found
			{
				System.out.println(">>Record not found for Deletion");
			}
			else
			{
				System.out.println(">>Data Deleted");
			}
			file.close();
			objip.close();
			
			
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
	}
	
	
	
	
    //main
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("\n\t\tFILE ORGANIZATION");
		Vector<Student> v = new Vector<Student>();
		int c=0;
		do {
			System.out.println("\nInput Data:");
			System.out.print(">>Student Name:");
			String n= sc.nextLine();
			System.out.print(" Student roll number:");
			int r = sc.nextInt();
			System.out.print(" Student marks:");
			int m= sc.nextInt();

			// add element to the vector
			v.addElement(new Student(r,m,n));
			
			System.out.print("\n  >>Add another record?(1/0)");
			


			c= sc.nextInt();
			sc.nextLine();
		}while(c==1);
		
		
		
		//file creation
		File f = new File("FILE.txt");

		try
		{
			// object of file output stream,object outputstream
			FileOutputStream fops = new FileOutputStream(f);
			ObjectOutputStream oops = new ObjectOutputStream(fops);

			
			oops.writeObject(v);

			//close file
			fops.close();
			oops.close();

			System.out.println("Data Added Successfully!!");


		}// catch blocks
		catch(FileNotFoundException ex)
		{
			ex.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
	//}

	do
	{

		do
		{
			// menu
			System.out.println("\n\t\t\t  >>MENU<<");
			
			System.out.println("1.Read the file ");
			System.out.println("2.Search student details in the file");
			System.out.println("3.Delete a student record from the file");
			System.out.println("0.Exit");
			System.out.println("\nEnter your choice");
			c = sc.nextInt();


		}while(c<0 || c>3);

		switch(c)
		{
		case 1:
			
			mainclass.readF();
			break;
		case 2:
			
			mainclass.searchrec(sc);
			break;
		case 3:
			
			mainclass.deleterec(sc, f);
			System.out.println("\n>>Reading file again after deletion ");
			mainclass.readF();
			break;
		case 0:
		
			System.out.println(">>EXIT");
			break;

		default:
			System.out.println("\n >>Valid choice according to menu Needed");// validation
			break;
		}

	}while(c!=0);



}

}

/*
 * 
		FILE ORGANIZATION

Input Data:
>>Student Name:Rutvika
 Student roll number:2426
 Student marks:100

  >>Add another record?(1/0)1

Input Data:
>>Student Name:Muskan
 Student roll number:2401
 Student marks:99

  >>Add another record?(1/0)1

Input Data:
>>Student Name:Janavi
 Student roll number:9999
 Student marks:98

  >>Add another record?(1/0)1

Input Data:
>>Student Name:Anushka
 Student roll number:23
 Student marks:56

  >>Add another record?(1/0)0
Data Added Successfully!!

			  >>MENU<<
1.Read the file 
2.Search student details in the file
3.Delete a student record from the file
0.Exit

Enter your choice
1

>>Name = Rutvika
 Roll no = 2426
 Marks 100

>>Name = Muskan
 Roll no = 2401
 Marks 99

>>Name = Janavi
 Roll no = 9999
 Marks 98

>>Name = Anushka
 Roll no = 23
 Marks 56
File Data Read Completed

			  >>MENU<<
1.Read the file 
2.Search student details in the file
3.Delete a student record from the file
0.Exit

Enter your choice
2
Enter Roll No:
2401

>>Name = Muskan
 Roll no = 2401
 Marks =99

			  >>MENU<<
1.Read the file 
2.Search student details in the file
3.Delete a student record from the file
0.Exit

Enter your choice
2
Enter Roll No:
34
Record does not Exist

			  >>MENU<<
1.Read the file 
2.Search student details in the file
3.Delete a student record from the file
0.Exit

Enter your choice
3
Enter Roll No:
9999
>>Data Deleted

>>Reading file again after deletion 

>>Name = Rutvika
 Roll no = 2426
 Marks 100

>>Name = Muskan
 Roll no = 2401
 Marks 99

>>Name = Anushka
 Roll no = 23
 Marks 56
File Data Read Completed


			  >>MENU<<
1.Read the file 
2.Search student details in the file
3.Delete a student record from the file
0.Exit

Enter your choice
0
>>EXIT
*/
