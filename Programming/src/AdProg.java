import java.util.ArrayList;
import java.util.Scanner;


public class AdProg {

	public static void main(String[] args) {
		
		System.out.println("Welcome to Advanced Programming");
		Scanner input = new Scanner(System.in);
		System.out.print("Number of people: ");
		int num_people = input.nextInt();
		
		
		ArrayList<Person> people = new ArrayList<Person>();
	
		for(int i=0; i < num_people; i++)
		{ 
			System.out.print("Enter Age: ");
			int yourage = input.nextInt();
			
			System.out.print("Enter First name: ");
			String yourfname = input.next();
			
			System.out.print("Enter Last name: ");
			String yourlname = input.next();
			
			Person individual = new Person(yourfname, yourlname, yourage);
			
			people.add(individual);
			
		}
		
		for (Person person : people) {
			
			System.out.println(person.getFirstName() + " " + person.getLastName() + ": " + person.getAge());
			
		}
		
		double sum = 0; 
		for (Person person : people )
		{ 
			sum += person.getAge();
			
		}
		System.out.println("The Average is " + sum/ people.size());

	} 
}