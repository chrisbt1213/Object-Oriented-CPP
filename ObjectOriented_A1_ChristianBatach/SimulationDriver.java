/*
 * Christian Batach
 * CS 3560 - 01
 * Dr. Sun
 * Sept 29, 2020
 */
import java.util.*;

public class SimulationDriver {
	
	private static Random rand = new Random();
	//STUDENT_COUNT is the max amount of students to be surveyed, final amount of surveyed studetns could be less
	private final static int STUDENT_COUNT = 10;
	/* Generate array  of unique student IDs
	 * "+ 1" is added so that the minimum number of students added is 1 */
	int intRandom = rand.nextInt(STUDENT_COUNT) + 1;
	private Student students = new Student(intRandom);	
		
	public static VotingService initializeService() {
		return new VotingService();
	}
	
	public void runMultipleChoice() {
		VotingService newService = initializeService();
		//User inputs question
		Question guestionGiven = new MultipleChoice("Which are the best fast food restaurants?");
		newService.configureQuestion(guestionGiven);
		
		//Get amount of choices (for now the choices must be specified inside MultipleChoice class)
		List<String> choiceList = new LinkedList<String>();
		choiceList.add("A");
		choiceList.add("B");
		choiceList.add("C");
		choiceList.add("D");
		choiceList.add("E");
		
		//Generate list of students with unique IDs
		String[] listOfStudents = students.studentID();
		//Submits list of Students and the choices they can make to VotingService
		newService.configureAnswers(listOfStudents, choiceList);
		//Call Voting Service to display the results
		newService.displayResutls();	
	}
	
	public void runSingleChoice() {
		VotingService newService = initializeService();
		//User inputs question
		Question guestionGiven = new SingleChoice("IsPopeyes' chicken sandwich better than Chick-fil-A's chicken sandwich?");
		newService.configureQuestion(guestionGiven);
		
		//Get amount of choices (for now the choices must be specified inside SingleChoice class)
		List<String> choiceList = new LinkedList<String>();
		choiceList.add("A");
		choiceList.add("B");
		
		//Generate list of students with unique IDs
		String[] listOfStudents = students.studentID();
		//Submits list of Students and the choices they can make to VotingService
		newService.configureAnswers(listOfStudents, choiceList);
		//Call Voting Service to display the results
		newService.displayResutls();	
		
	}
	
	public static void main(String[] args) {

		SimulationDriver driver = new SimulationDriver();
		driver.runMultipleChoice();
		System.out.println("\n- - - - - - - - - - - - - - - - - ");
		driver.runSingleChoice();
	}
	
}
