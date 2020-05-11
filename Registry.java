import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
public class Registry
{
   private TreeMap<String,Student> students = new TreeMap<>();
   private TreeMap<String, ActiveCourse> courses  = new TreeMap<>();
   
   
   public void readFile() throws IOException, NoSuchElementException{ //NoSuchElementException for badFile
		//DEAR TA, PLEASE CHANGE STUDENTS.TXT PATH ACCORDING TO YOURS	 
	   File studentsFile=new File("students.txt");
	   Scanner in=new Scanner (studentsFile);

	   while (in.hasNextLine()){
		   String name=in.next(); //reads fist word as name 
		   String id=in.next();  //reads second word as id
		   students.put(id,new Student(name, id)); //adds object to students TreeMap 
	   }   		
   }
  
   public Registry() throws IOException    //modified constructor method to throw IO Exceptions
   {
	   // Add some students //
	   //Student s1 = new Student("JohnOliver", "34562");
	   //Student s2 = new Student("HarryWindsor", "38467");
	   //Student s3 = new Student("SophieBrown", "98345");
	   //Student s4 = new Student("FaisalQuereshi", "57643");
	   //Student s5 = new Student("GenghisKhan", "25347");
	   //Student s6 = new Student("SherryTu", "46532");
	   //students.add(s1);
	   //students.add(s2);
	   //students.add(s3);
	   //students.add(s4);
	   //students.add(s5);
	   //students.add(s6);
	
	   readFile(); //calls helper method to read file line by line 
	   ArrayList<Student> list = new ArrayList<Student>();
	   
	   // Add some active courses with students
	   String courseName = "Computer Science II";
	   String courseCode = "CPS209";
	   String descr = "Learn how to write complex programs!";
	   String format = "3Lec 2Lab";
	   
	   list.add(students.get("38467")); //did this based on index beacuse we did not keep track of s1,s2etc...
	   list.add(students.get("98345"));
	   list.add(students.get("57643"));

	   courses.put(courseCode,new ActiveCourse(courseName,courseCode,descr,format,"W2020",list));
	   
	   // Add course to student list of credit courses

	   (students.get("38467")).addCourse(courseName,courseCode,descr,format,"W2020", 0);
	   (students.get("98345")).addCourse(courseName,courseCode,descr,format,"W2020", 0); 
	   (students.get("57643")).addCourse(courseName,courseCode,descr,format,"W2020", 0); 
	  
	   // CPS511
	   list.clear();
	   courseName = "Computer Graphics";
	   courseCode = "CPS511";
	   descr = "Learn how to write cool graphics programs";
	   format = "3Lec";

	   list.add(students.get("34562")); 
	   list.add(students.get("25347"));
	   list.add(students.get("46532"));
	   
	   courses.put(courseCode,new ActiveCourse(courseName,courseCode,descr,format,"F2020",list));

	   (students.get("34562")).addCourse(courseName,courseCode,descr,format,"W2020", 0); 
	   (students.get("25347")).addCourse(courseName,courseCode,descr,format,"W2020", 0); 
	   (students.get("46532")).addCourse(courseName,courseCode,descr,format,"W2020", 0);
	   
	   // CPS643
	   list.clear();
	   courseName = "Virtual Reality";
	   courseCode = "CPS643";
	   descr = "Learn how to write extremely cool virtual reality programs";
	   format = "3Lec 2Lab";

	   list.add(students.get("34562")); //did this based on index beacuse we did not keep track of s1,s2etc...
	   list.add(students.get("38467"));
	   list.add(students.get("57643"));
	   list.add(students.get("46532"));
	   

	   courses.put(courseCode, new ActiveCourse(courseName,courseCode,descr,format,"W2020",list));
	  
	   (students.get("34562")).addCourse(courseName,courseCode,descr,format,"W2020", 0); 
	   (students.get("38467")).addCourse(courseName,courseCode,descr,format,"W2020", 0); 
	   (students.get("57643")).addCourse(courseName,courseCode,descr,format,"W2020", 0); 
	   (students.get("46532")).addCourse(courseName,courseCode,descr,format,"W2020", 0);
	   
	   //CPS706 //ADDED
	     courseName = "Computer Networks";
	     courseCode = "CPS706";
	     descr = "Learn about Computer Networking";
	     format = "3Lec 1Lab";
	     courses.put(courseCode, new ActiveCourse(courseName,courseCode,descr,format,"W2020",list));
	     
	     //CPS616 //ADDED
	     courseName = "Algorithms";
	     courseCode = "CPS616";
	     descr = "Learn about Algorithms";
	     format = "3Lec 1Lab";
	     courses.put(courseCode, new ActiveCourse(courseName,courseCode,descr,format,"W2020",list));
   }
   
   public boolean addNewStudent(String name, String id)
   {
	   if (findStudent(id) != null) return false; 	   
	   students.put(id, new Student(name, id));
	   return true;
   }
   
   public boolean removeStudent(String studentId)
   {
	   for (Entry<String, Student> e : students.entrySet()) { //iterates TreeMap
	        if((e.getKey()).equals(studentId)) { //finds matching id and removes it 
	        	students.remove(studentId);
	        	return true;
	        }
	    }  
	  return false;
   }
   
   public void printAllStudents()
   {
	   for (Entry<String, Student> e : students.entrySet()) { //iterates tree map and prints all students 
		   System.out.println("ID: " + e.getValue().getId() + " Name: " + e.getValue().getName() );   
	   }
   }
   
   private Student findStudent(String id)
   {	   
	   for (Entry<String, Student> e : students.entrySet()) {	        
		   Student s= e.getValue();
		   if((e.getKey()).equals(id)) { //getkey gets Id bc the key is the id
			   return s;        //returns the whole student object
		   }		 
	   }   
	   return null;   
   }
   
   private ActiveCourse findCourse(String code)
   {
	   for (Entry<String, ActiveCourse> e : courses.entrySet()) {	        
		   ActiveCourse key = e.getValue();
	        if((e.getKey()).equalsIgnoreCase(code)) { //returns code match
	        	return (key);
	        } 		 
	   }   
	   return null; 	   
   }
   
   public void addCourse(String studentId, String courseCode)
   {
	   Student s = findStudent(studentId);
	   if (s == null) return;
	   
	   if (s.takenCourse(courseCode)) return;
	   
	   ActiveCourse ac = findCourse(courseCode);
	   if (ac == null) return;
	   
	   if (ac.enrolled(studentId)) return;
			   
	   ac.students.add(s);   //don't have to change its accessing the student ARRAYLIST in active course not TreeMap
	   s.addCourse(ac.getName(),ac.getCode(),ac.getCourseDescription(),ac.getFormat(),ac.getSemester(),0);
 
   }
   
   public void dropCourse(String studentId, String courseCode)
   {
	   Student s = findStudent(studentId);
	   if (s == null) return;
	   
	   ActiveCourse ac = findCourse(courseCode);
	   if (ac == null) return;
	   
	   ac.remove(studentId); 
	   s.removeActiveCourse(courseCode);
   }
   
   public void printActiveCourses()
   {
	   for (Entry<String, ActiveCourse> e : courses.entrySet()) {	 //prints active courses    
		   System.out.println(e.getValue().getDescription());
	   }   
   }
   
   public void printClassList(String courseCode)
   {
	   ActiveCourse ac = findCourse(courseCode);
	   if (ac == null) return;
	   
	   ac.printClassList();   
   }
   
   public void sortCourseByName(String courseCode)
   {
	   ActiveCourse ac = findCourse(courseCode);
	   if (ac == null) return;
	   
	   ac.sortByName();
   }
   
   public void sortCourseById(String courseCode)
   {
	   ActiveCourse ac = findCourse(courseCode);
	   if (ac == null) return;
	   
	   ac.sortById();	   
   }
   
   public void printGrades(String courseCode)
   {
	   ActiveCourse ac = findCourse(courseCode);
	   if (ac == null) return;
	   
	   ac.printGrades();
   }
   
   public void printStudentCourses(String studentId)
   {
	   Student s = findStudent(studentId);
	   if (s == null) return;
	   
	   s.printActiveCourses();
   }
   
   public void printStudentTranscript(String studentId)
   {
	   Student s = findStudent(studentId);
	   if (s == null) return;
	   
	   s.printTranscript();
   }
   
   public void setFinalGrade(String courseCode, String studentId, double grade)
   {
	   Student s = findStudent(studentId);
	   if (s == null) return;
	   s.setGrade(courseCode, grade); 
	   ActiveCourse ac = findCourse(courseCode);
	   if (ac == null) return;
   }
   
   public TreeMap<String,ActiveCourse> getcoursesMap(){
	   return courses;
   }
}

	

