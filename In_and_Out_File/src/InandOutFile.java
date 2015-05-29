import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * CS3A File IO Assignment
 * @author Seneca Meeks 
 *
 */
public class InandOutFile 
{
	public static void main(String[] args) 
	{
		try
		{
			//String content = " "; 
			// String stuff = " "; 
			File data = new File("/Users/student/Downloads/all_students_jan.csv");
			Scanner file = new Scanner (data);
			Student person; 
			ArrayList<Student> student_list = new ArrayList<Student>(); 
			while (file.hasNextLine()) 
			{
				String line = file.nextLine();
				//System.out.println(line);
				String[] record = line.split(",");
				person = new Student(record[0], record[1], record[2], record[3], record[4], record[5], record[6], record[7], record[8], record[9], record[10]);
				student_list.add(person); 
			}

			//lastname(student_list); 
			//twin(student_list); 
			menu(student_list); 
		}//try


		catch(FileNotFoundException f)
		{
			System.out.println("file not found...dumbass");
		}

		//what do you want to do?

	}
	/**
	 * Menu function that prints out a list of options for user  
	 * @param student_list the list of students
	 */
	public static void menu(ArrayList<Student> student_list){
		while(true){
			Scanner scan = new Scanner(System.in);  
			ArrayList<String> menu = new ArrayList<String>();
			int selection = 0; 

			menu.add("(1) List a student by only last name with associated data"); //done
			menu.add("(2) All student with the same last name with same birthday (twins)"); //needs work
			menu.add("(3) All students with same birth month "); //done
			menu.add("(4) All student by a specific House"); //done
			menu.add("(5) A sorted list of students by last name"); //need to do
			menu.add("(6) A report by gender (totals only)"); //done 
			menu.add("(7) Total number of fourth-grade vs 7th grade"); //done
			menu.add("(8) All students born in 2001"); //done
			menu.add("(9) First name frequency (top 10)");
			menu.add("(10) Students by grade"); //done
			System.out.println("This is the Dalton Student Information System! Here is the menu:");
			for (int i = 0; i < menu.size(); i++) {
				System.out.println(menu.get(i));
			}
			System.out.println("Please enter the associating number of the operation you want. ");
			selection = scan.nextInt();	
	
			if(selection == 1){ //works 
				lastname(student_list); 
			}
			if(selection == 2){ //works 

				twin(student_list); 
			}
			if(selection == 3){ //works 
				String m;
				System.out.println("Please type the number of the month you would like to see.");
				m = scan.next(); 
				month(student_list, m);
			}
			if(selection == 4){ //works 
				String h;
				System.out.println("Please enter the name, or part of the name, of the house you would like to see. (Use proper capitalization)");
				h = scan.next(); 
				house(student_list, h);
			}
			if(selection == 5){  //works  
				sortedLast(student_list); 
			}
			if(selection == 6){ //works 
				gender(student_list);
			}
			if(selection == 7){ //works 
				FourVsK(student_list);
			}
			if(selection == 8){ //works 
				year(student_list);
			}
			if(selection == 9){ //works 
				freq(student_list);
			}
			if(selection == 10){ //works 
				grade(student_list);
			}

			}

		}

	/**
	 * Prints out last name of all Students
	 * @param student_list the list of students
	 */
	public static void lastname(ArrayList<Student> student_list){
		for (int i = 0; i < student_list.size(); i++) {
			System.out.println(student_list.get(i).getLastName());
		}

	}
	
	/**
	 * Prints out all of the twins 
	 * @param student_list the list of students
	 */
	public static void twin(ArrayList<Student> student_list)
	{
		ArrayList<Student> clonelist = new ArrayList<Student>(student_list); 
		int size = clonelist.size();
		for (int ii = 0; ii < size;ii++){
			for(int i = 0; i < size;i++){
				if(clonelist.get(ii).getBirthDay().equals(clonelist.get(i).getBirthDay()) && 
						clonelist.get(ii).getBirthMonth().equals(clonelist.get(i).getBirthMonth()) &&
						clonelist.get(ii).getBirthYear().equals(clonelist.get(i).getBirthYear()) && 
						clonelist.get(ii).getLastName().equals(clonelist.get(i).getLastName()) && ii != i){
					//gets info
					System.out.println(student_list.get(ii).getFirstName() + " " + student_list.get(ii).getLastName());
					//String content = student_list.get(ii).getFirstName() + student_list.get(ii).getLastName(); 

					System.out.println(student_list.get(i).getFirstName() + " " + student_list.get(i).getLastName());
					//String stuff = student_list.get(i).getFirstName() + student_list.get(i).getLastName();  
					//removes from list
					clonelist.remove(ii);
					clonelist.remove(i);
					size= size -2;


				}

			}
		}
	}

	/**
	 * Prints out list of students with birthday in the month the user enters
	 * @param student_list the list of students
	 */
	public static void month(ArrayList<Student> student_list, String m){	
		ArrayList<Student> temp = new ArrayList<Student>(); 
		for (int i = 0; i < student_list.size(); i++) {
			if(student_list.get(i).getBirthMonth().equals(m)){
				temp.add(student_list.get(i)); 
			}
		}
		for (int i = 0; i < temp.size(); i++) {
			System.out.println(temp.get(i).getFirstName() + " " + temp.get(i).getLastName());
		}
	}

	/**
	 * Prints out a list of students in the house the user enters 
	 * @param student_list the list of students
	 */
	public static void house(ArrayList<Student> student_list, String h){
		ArrayList<Student> temp = new ArrayList<Student>(); 
		for (int i = 0; i < student_list.size(); i++) {
			if(student_list.get(i).getAdvisor().contains(h)){
				temp.add(student_list.get(i)); 
			}
		}
		for (int i = 0; i < temp.size(); i++) {
			System.out.println(temp.get(i).getFirstName() + " " + temp.get(i).getLastName());
		}
	}

	/**
	 * Prints out a sorted list of students by last name
	 * @param student_list the list of students
	 */
	public static void sortedLast(ArrayList<Student> student_list){
		ArrayList<String> ln = new ArrayList<String>(); 
		for (int i = 0; i < student_list.size(); i++) {
			ln.add(student_list.get(i).getLastName() + ", " + student_list.get(i).getFirstName() + ": " + student_list.get(i).getAdvisor() );
		}
		Collections.sort(ln);
		for (int i = 0; i < ln.size(); i++) {
			System.out.println(ln.get(i));
		}

	}

	/**
	 * Prints out the number of females and males in the file
	 * @param student_list the list of students
	 */
	public static void gender(ArrayList<Student> student_list){
		int female = 0;
		int male = 0; 
		for (int i = 0; i < student_list.size(); i++) {
			if(student_list.get(i).getSex().equals("F")){
				female += 1;
			}
			else if(student_list.get(i).getSex().equals("M")){
				male += 1; 
			}

		}
		System.out.println("Females: " + female);
		System.out.println("Males: " + male);
	}

	/**
	 * Prints out the number of students in Fourth grade vs. 7th grade
	 * @param student_list the list of students
	 */
	public static void FourVsK(ArrayList<Student> student_list){
		int four = 0;
		int k = 0; 
		for (int i = 0; i < student_list.size(); i++) {
			if(student_list.get(i).getCurrentGrade().equals("4")){
				four += 1;
			}
			else if(student_list.get(i).getCurrentGrade().equals("7")){
				k += 1; 
			}

		}
		System.out.println("Fourth Grade: " + four);
		System.out.println("Seventh Grade: " + k);
	}

	/**
	 * Prints out students born in 2001
	 * @param student_list the list of students
	 */
	public static void year(ArrayList<Student> student_list){
		ArrayList<Student> temp= new ArrayList<Student>();
		for (int i = 0; i < student_list.size(); i++) {
			if(student_list.get(i).getBirthYear().equals("2001")){
				temp.add(student_list.get(i));
			}
		}
		for (int i = 0; i < temp.size(); i++) {
			System.out.println(temp.get(i).getFirstName() + " " + temp.get(i).getLastName());
		}
	}

	/**
	 * Prints out the top ten first names 
	 * @param student_list the list of students
	 */
	public static void freq(ArrayList<Student> list) {
		Map<String, Integer> f = new TreeMap<String, Integer>();
		for(Student s : list) {
			if(!f.containsKey(s.firstName)) {
				f.put(s.firstName, 1);
			} else {
				f.put(s.firstName, f.get(s.firstName)+1);
			}
		}

		f = sortByValue(f);
		//System.out.println(f);

		ArrayList<String> names = new ArrayList<String>(f.keySet()); 
		ArrayList<Integer> val = new ArrayList<Integer>(f.values()); 
		Collections.reverse(names);
		Collections.reverse(val);

		for (int i = 0; i < 10; i++) {
			System.out.println(names.get(i) + ": " + val.get(i));
		}


	}
	/**
	 * takes an unsorted hasmap and sorts it by value
	 * @param unsortMap
	 * @return sortedMap
	 */
	public static Map sortByValue(Map unsortMap) {	 
		List list = new LinkedList(unsortMap.entrySet());

		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o1)).getValue())
						.compareTo(((Map.Entry) (o2)).getValue());
			}
		});

		Map sortedMap = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}

	/**
	 * Prints out a list of students in the grade entered by user
	 * @param student_list the list of students
	 */
	public static void grade(ArrayList<Student> student_list){
		ArrayList<Student> temp= new ArrayList<Student>();
		Scanner s = new Scanner(System.in);  
		System.out.println("Enter the grade you would like to see.");
		String g = s.nextLine(); 
		for (int i = 0; i < student_list.size(); i++) {
			if(student_list.get(i).getCurrentGrade().equals(g)){
				temp.add(student_list.get(i));
			}
		}
		for (int i = 0; i < temp.size(); i++) {
			System.out.println(temp.get(i).getFirstName() + " " + temp.get(i).getLastName());
		}
	}

}