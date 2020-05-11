import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// Active University Course
 
public class ActiveCourse extends Course
{
	public  ArrayList<Student> students; // map id to name
	private String             semester;
	private int lectureStart=0;  //QUESTION 1: added 3 new instance variables 
	private int lectureDuration=0;
	private String lectureDay="";
    
   public ActiveCourse(String name, String code, String descr, String fmt,String semester,ArrayList<Student> students)
   {
	   super(name, code, descr, fmt);
	   this.semester = semester;
	   this.students = new ArrayList<Student>(students);
   }
   
   public String getSemester()
   {
	   return semester;
   }
   
   public void printClassList()
   {
	   for (int i = 0; i < students.size(); i++)
	   {
		   System.out.println(students.get(i).toString());
	   }
   }
   
   public void printGrades()
   {
	   for (int i = 0; i < students.size(); i++)
	   {
		   Student s = students.get(i);
		   System.out.println(s.getId() + " " + s.getName() + " " + s.getGrade(getCode()));
	   }
   }
   
   public String getDescription()
   {
	   return super.getDescription() + " " + semester + " Enrolment: " + students.size() +  "\n";
   }
   
   public String getCourseDescription()
   {
	   return getDescr();
   }
    
   public double getGrade(String studentId)
   {
	   for (int i = 0; i < students.size(); i++)
	   {
		   if (studentId.equals(students.get(i).getId()))
		   {
			   return students.get(i).getGrade(getCode());
		   }
	   }
	   return 0;
   }
   
   public boolean enrolled(String studentId)
   {
	   for (int i = 0; i < students.size(); i++)
	   {
		   if (studentId.equals(students.get(i).getId()))
		     return true;
	   }
	   return false;
   }
   
   public void remove(String id)
   {
	   for (int j = 0; j < students.size(); j++)
	   {
   		   Student s = students.get(j);
   		   if (s.getId().equals(id))
   		   {
   		     students.remove(j);
   		     return;
   		   }
 	   }
    }
   
      
   public void sortByName()
   {
 	  Collections.sort(students, new NameComparator());
   }
   
   private class NameComparator implements Comparator<Student>
   {
   	public int compare(Student a, Student b)
   	{
   	  return a.getName().compareTo(b.getName());	  
   	}
   }
   
   public void sortById()
   {
 	  Collections.sort(students, new IdComparator());
   }
   
   private class IdComparator implements Comparator<Student>
   {
   	public int compare(Student a, Student b)
   	{
   	  return a.getId().compareTo(b.getId());	  
   	}
   }
   
   
 
	//****ADDED METHODS*****
	public void setLectureStart(int startTime) {
		this.lectureStart=startTime;
	}
	public void setLectureDuration(int duration) {
		this.lectureDuration=duration;
	}
	public void setLectureDay(String day) {
		this.lectureDay=day;
	}
	
	public String getLectureStart() {
		return(Integer.toString(lectureStart)); //casts int to String and returns 
	}
	public String getLectureDuration() {
		return(Integer.toString(lectureDuration)); //casts int to String and returns 
	}
	public String getLectureDay() {
		return(lectureDay); //already string no need to cast
	}
	
	public String getCourseCode() {
		return(getCode());
	}
	public void clearS() {
		lectureDay="";
		lectureStart=0;
		lectureDuration=0;
		
	}
}
