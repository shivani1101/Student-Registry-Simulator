import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class StudentRegistrySimulator 
{
  public static void main(String[] args) {
  
	  try {
		  Registry registry = new Registry();    
	  
	  
	  Scheduler scheduler = new Scheduler(registry.getcoursesMap()); //created scheduler object pa
	// courses TreeMap from regisrty 
	
	  Scanner scanner = new Scanner(System.in);
	  System.out.print(">");
	  
	  while (scanner.hasNextLine())
	  {
		  String inputLine = scanner.nextLine();
		  if (inputLine == null || inputLine.equals("")) continue;
		  
		  Scanner commandLine = new Scanner(inputLine);
		  String command = commandLine.next();
		  
		  if (command == null || command.equals("")) continue;
		  
		  else if (command.equalsIgnoreCase("L") || command.equalsIgnoreCase("LIST"))
		  {
			  registry.printAllStudents();
		  }
		  else if (command.equals("Q") || command.equals("QUIT"))
			  return;
		  
		  else if (command.equalsIgnoreCase("REG"))
		  {
			  String name = null;
			  String id   = null;
			  if (commandLine.hasNext())
			  {
				 name = commandLine.next();
				 // check for all alphabetical
				 String lcase = name.toLowerCase();
				 if (!isStringOnlyAlphabet(lcase))
				 {
				   System.out.println("Invalid Characters in Name " + name);
				   continue;
				 }
			  }
			  if (commandLine.hasNext())
			  {
				 id = commandLine.next();
				 // check for all numeric
				 if (!isNumeric(id))
				 {
				   System.out.println("Invalid Characters in ID " + id);
				   continue;
				 }
				 if (!registry.addNewStudent(name,id))
					 System.out.println("Student " + name + " already registered");
			  }
			 
		  }
		  else if (command.equalsIgnoreCase("DEL"))
		  {
			  if (commandLine.hasNext())
			  {
				 String id = commandLine.next();
				 // check for all numeric
				 
				 if (!isNumeric(id))
				   System.out.println("Invalid Characters in student id " + id);
				 registry.removeStudent(id);
			  }
		  }
		  else if (command.equalsIgnoreCase("PAC"))
		  {
			  registry.printActiveCourses();
		  }		  
		  else if (command.equalsIgnoreCase("PCL"))
		  {
			  String courseCode = null;
			  if (commandLine.hasNext())
			  {
				 courseCode = commandLine.next();
			     registry.printClassList(courseCode);
			  }
		  }
		  else if (command.equalsIgnoreCase("PGR"))
		  {
			  String courseCode = null;
			  if (commandLine.hasNext())
			  {
				 courseCode = commandLine.next();
			     registry.printGrades(courseCode);
			  }
		  }
		  else if (command.equalsIgnoreCase("ADDC"))
		  {
			  String courseCode = null;
			  String id         = null;
			  
			  if (commandLine.hasNext())
			  {
				 id = commandLine.next();
			  }
			  if (commandLine.hasNext())
			  {
				 courseCode = commandLine.next();
				 registry.addCourse(id, courseCode);
			  }
			  
		  }
		  else if (command.equalsIgnoreCase("DROPC"))
		  {
			  String courseCode = null;
			  String id         = null;
			  
			  if (commandLine.hasNext())
			  {
				 id = commandLine.next();
			  }
			  if (commandLine.hasNext())
			  {
				 courseCode = commandLine.next();
				 registry.dropCourse(id, courseCode);
			  }
			  
		  }
		  
		  else if (command.equalsIgnoreCase("PSC"))
		  {
			  String studentId = null;
			  if (commandLine.hasNext())
			  {
				 studentId = commandLine.next();
			     registry.printStudentCourses(studentId);
			  }
		  }
		  else if (command.equalsIgnoreCase("PST"))
		  {
			  String studentId = null;
			  if (commandLine.hasNext())
			  {
				 studentId = commandLine.next();
			     registry.printStudentTranscript(studentId);
			  }
		  }
		  else if (command.equalsIgnoreCase("SFG"))
		  {
			  String courseCode = null;
			  String id         = null;
			  String grade      = null;
			  double numGrade = 0;
			  
			  if (commandLine.hasNext())
			  {
				 courseCode = commandLine.next();
			  }
			  if (commandLine.hasNext())
			  {
				 id = commandLine.next();
			  }
			  if (commandLine.hasNext())
			  {
				  grade = commandLine.next();
				  if (!isNumeric(grade)) continue;
				  numGrade = Integer.parseInt(grade);
				  registry.setFinalGrade(courseCode, id, numGrade);
			  }
			  
		  }
		  else if (command.equalsIgnoreCase("SCN"))
		  {
			  String courseCode = null;
			  if (commandLine.hasNext())
			  {
				 courseCode = commandLine.next();
			     registry.sortCourseByName(courseCode);
			  }
		  }
		  else if (command.equalsIgnoreCase("SCI"))
		  {
			  String courseCode = null;
			  if (commandLine.hasNext())
			  {
				 courseCode = commandLine.next();
				 registry.sortCourseById(courseCode);
			  }
		  }
 
		  //added 3 new commands 
		  	else if(command.equalsIgnoreCase("SCH")) { //Schedules a course for a certain day, start time and duration
		  		try {
		  			String courseCode = null;
			  		String day = null;
			  		int startTime=0;
			  		int duration=0;
			  		
			  		if (commandLine.hasNext())
					  {
						 courseCode = commandLine.next();
					  }
					 if (commandLine.hasNext())
					  {
						 day = commandLine.next();
					  }
					 if (commandLine.hasNext())
					  {
						 startTime = commandLine.nextInt();
					  }
					 if (commandLine.hasNext())
					  {
						 duration = commandLine.nextInt();
					  }
					 scheduler.setDayAndTime(courseCode, day, startTime, duration);	
		  		}
		  		catch(InvalidDayException e) { //caught all exceptions in scheduler
		  		  System.out.println("Invalid Lecture Day");
		  		}
		  		catch(UnknownCourseException e) {
		  		  System.out.println("Unknown course: "+ e);
		  		}
		  		catch(InvalidTimeException e) {
		  		  System.out.println("Invalid Lecture Start Time");
		  		}
		  		catch(InvalidDurationException e) {
		  		  System.out.println("Invalid Lecture Duration");
		  		}
		  		catch(LectureTimeCollisionException e) {
		  		  System.out.println("Lecture Time Collision");
		  		}
		  	}
		  	
		  	else if(command.equalsIgnoreCase("CSCH")) { //Clears the schedule of the given course
		  		String courseCode = null;
		  		if (commandLine.hasNext())
				  {
					 courseCode = commandLine.next();
				  }
		  		scheduler.clearSchedule(courseCode);
		  	}
		  	
		  	else if(command.equalsIgnoreCase("PSCH")) { //Prints the entire schedule
		  		scheduler.printSchedule();
		  	} 
		  System.out.print("\n>"); 
	  }
	  }
	  catch (FileNotFoundException e) {  //caught all exceptions in registry
		System.out.println("students.txt File not Found ");
		
	   }
	  catch(IOException e ) {
		  System.out.println("I/O ");
	  }
	  catch (NoSuchElementException e) { 
		  System.out.println("Bad File Format students.txt");
	  }   	
  }
  
  private static boolean isStringOnlyAlphabet(String str) 
  { 
      return ((!str.equals("")) 
              && (str != null) 
              && (str.matches("^[a-zA-Z]*$"))); 
  } 
  
  public static boolean isNumeric(String str)
  {
      for (char c : str.toCharArray())
      {
          if (!Character.isDigit(c)) return false;
      }
      return true;
  }
  
  // A2 - use double.parseDouble in A2
}