import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class Scheduler 
{
    // In main() after you create a Registry object, create a Scheduler object and pass in the courses ArrayList/TreeMap
	// If you do not want to try using a Map then uncomment
	// the line below and comment out the TreeMap line
	
	//ArrayList<Student> students;
	
	TreeMap<String,ActiveCourse> schedule; 
	String [] [] a = {{"   ","     ","Mon","       ","Tue","       ","Wed","       ","Thur","       ","Fri"},
					 {"800","    ","      ","    ","      ","    ","      ","    ","      ","    ","      "},
					 {"900","    ","      ","    ","      ","    ","      ","    ","      ","    ","      "},
					 {"1000","    ","      ","    ","      ","    ","      ","    ","      ","    ","      "},
					 {"1100","    ","      ","    ","      ","    ","      ","    ","      ","    ","      "},
					 {"1200","    ","      ","    ","      ","    ","      ","    ","      ","    ","      "},
					 {"1300","    ","      ","    ","      ","    ","      ","    ","      ","    ","      "},
					 {"1400","    ","      ","    ","      ","    ","      ","    ","      ","    ","      "},
					 {"1500","    ","      ","    ","      ","    ","      ","    ","      ","    ","      "},
					 {"1600","    ","      ","    ","      ","    ","      ","    ","      ","    ","      "}};	
	public Scheduler(TreeMap<String,ActiveCourse> courses) { //constructor: takes in TreeMap courses of type Activecourse
		schedule = courses;
	}
	
	public void setDayAndTime(String courseCode, String day, int startTime, int duration){
		boolean courseFound=false;
		for(Entry<String, ActiveCourse> e : schedule.entrySet()) { //iterates thru TreeMap schedule
			ActiveCourse ac= e.getValue(); 
			
			//THROWS EXCEPTIONS
			if((e.getKey()).equalsIgnoreCase(courseCode)) {  //finds the matching activecourse with code 
				if(!((day.equalsIgnoreCase("Mon")) || (day.equalsIgnoreCase("Tue")) || (day.equalsIgnoreCase("Wed")) ||(day.equalsIgnoreCase("Thur")) || (day.equalsIgnoreCase("Fri")))  ) {
					throw new InvalidDayException(); //throws exception if entered invalid day
				} 
				if(startTime<800 || (startTime+(duration-1)*100)>1700) {
					throw new InvalidTimeException(); //throws if invalid start Time
				}	
				if(duration<1 || duration>3) {
					throw new InvalidDurationException(); //throws if invalid lecture duration 
				}
				
				//IF EXSISTING COURSE IN SCHEDULE, THEN DELETES IT by making it empty "    "
				if(!(ac.getLectureDay()).equals("") ) { 
					clearSchedule(courseCode);
					for(int i=0; i<a.length; i++) { //iterates row numbers 
						for(int j=0; j<a[i].length; j++) { //iterates column numbers
							if(a[i][j].equalsIgnoreCase(courseCode)) {
								a[i][j]=("      ");
							}
						}
					}		
				}
				
				//UPDATES OBJECTS startTime, duration, day				
				ac.setLectureStart(startTime);  //sets variables 
				ac.setLectureDuration(duration);
				ac.setLectureDay(day);
				courseFound=true; //if valid course entered 
				

				int firstNum=0;
				int secondNum=0;
				//FINDS STARTTIME MATCH IN ARRAY, GETS ARRAY ELEMENT INDEX
				for(int i=0; i<a.length; i++) { //loops array rows
					 if(a[i][0].equalsIgnoreCase(ac.getLectureStart())) { //finds the matching class time 
						 firstNum=i; //gets the first index of array	 
					 }	 
				 }
				//FINDS DAY MATCH IN ARRAY, GETS ARRAY ELEMENT INDEX				 
				 for(int i=0; i<11; i++) {
					 if(a[0][i].equalsIgnoreCase(ac.getLectureDay())) {
						 secondNum=i; //gets second index of array 	
					 }
				 }
					
				 boolean courseAlreadyThere=false;
					//CHECKS IF COURSE ALREADY IN ARRAY 
				 //checks if course already in block if so, it makes courseAlreadyThere true, then if flase it shoudl continue resttttttt adding courses					
					int lectureTime = Integer.parseInt(ac.getLectureDuration());//gets this lecture's duration number as INT
					int timeCountCheck=firstNum;
					if(lectureTime>1) {
						 for(int i=0; i<lectureTime; i++) { 
							 if(!(a[timeCountCheck][secondNum].equals("      "))) {
								 courseAlreadyThere=true;
							 }
							 timeCountCheck++;
						 }
					 }
					
					//IF COURSE NOT ALREADY THERE, ADDS COURSE TO NEEDED BLOCKS based on duration
					if (courseAlreadyThere==false) {
						a[firstNum][secondNum]=ac.getCourseCode();
						int timeCount=firstNum+1; //+1 So doesn't add to the existing course array block
						 if(lectureTime>1) {
							 for(int i=0; i<lectureTime-1; i++) { 
								 a[timeCount][secondNum]=ac.getCourseCode(); //added the additional hour classes in array block 
								 timeCount++;
							 }
						 }
					}
					else { //if course already in array then overlapping, throws exception
						 throw new LectureTimeCollisionException();
					 }
		}	
		}	
		if(courseFound==false) { //if course not valid, exception thrown
			throw new UnknownCourseException(courseCode);
		}
	}

	public void clearSchedule(String courseCode){
		for(Entry<String, ActiveCourse> e : schedule.entrySet()) { //iterates through TreeMap schedule
			ActiveCourse a= e.getValue(); 
			if((e.getKey()).equalsIgnoreCase(courseCode)) {  //finds the object w matching coursecode 
				a.clearS();    //calls this method to set specific variable names to 0	
			}	
		}	
		//finds array element with matching code and removes it by setting it empty "     "	
		for(int i=0; i<a.length; i++) { //iterates row numbers 
			for(int j=0; j<a[i].length; j++) { //iterates column numbers
				if(a[i][j].equalsIgnoreCase(courseCode)) { 
					a[i][j]=("      ");		
				}	
			}	
		}	
	}
	
	//Prints the timetable 	
	public void printSchedule(){
		 for(int i=0; i<a.length; i++) { //iterates row numbers 
				for(int j=0; j<a[i].length; j++) { //iterates column numbers
					System.out.print(a[i][j]);  //prints each element in array 
				}
				System.out.println(" ");
		 }				
	} 
}
//ADDTIONAL CLASSES ADDED TO CREATE CUSTOM EXCEPTIONS (these extends Runtime Exception)
class UnknownCourseException extends RuntimeException{
	public UnknownCourseException() {}
	public UnknownCourseException (String message) {
		super(message);
	}
}

class InvalidDayException extends RuntimeException{
	public InvalidDayException() {}
		public InvalidDayException (String message) {
			super(message);
		}
}
class InvalidTimeException extends RuntimeException{
	public InvalidTimeException() {}
		public InvalidTimeException (String message) {
			super(message);
		}
}
class InvalidDurationException extends RuntimeException{
	public InvalidDurationException() {}
		public InvalidDurationException (String message) {
			super(message);
		}
}
class LectureTimeCollisionException extends RuntimeException{
	public LectureTimeCollisionException() {}
		public LectureTimeCollisionException (String message) {
			super(message);
		}
}
