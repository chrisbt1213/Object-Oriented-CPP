/*
 * Christian Batach
 * CS 3560 - 01
 * Dr. Sun
 * Sept 29, 2020
 */
import java.util.UUID;

public class Student {
	
	private static String[] studentID;
	int numberOfStudents;
	
	
	public Student (int numberOfStudents) {
		
		//number of students is specified in SimulationDrive as final static int STUDENT_COUNT
		this.numberOfStudents = numberOfStudents;
		studentID = new String[numberOfStudents];
	}
	
	//assigns a random unique identifies for each student and returns an array of all the IDs
	public static String[] studentID() {

	      for (int i = 0; i < studentID.length; i++) {
	         studentID[i] = UUID.randomUUID().toString();
	      }
	      return studentID;
	      
	}
	
}
