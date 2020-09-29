/*
 * Christian Batach
 * CS 3560 - 01
 * Dr. Sun
 * Sept 29, 2020
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.math.*;

public class VotingService {
	
	private List<String> choices;
	private String[] students;
	private String question;
	private int type;
	private String specifics;
	
	private HashMap<String, List<List<String>>> mapInitialAnswers = new HashMap<String, List<List<String>>>();
	private HashMap<String, List<String>> mapLastSubmissions = new HashMap<String, List<String>>();
	
	private int A = 0, B = 0, C = 0, D = 0, E = 0;

	
	public void configureQuestion(Question question) {
		this.question = question.setQuestion();
		type = question.getType();
		specifics = question.specifyChoice();
	}
	
	public void configureAnswers(String[] students, List<String> choices) {
		this.students = students;
		this.choices = choices;
		
	}
	
	//maps each student ID with an initial random choice of answer or answers
	private HashMap<String, List<List<String>>> initialAnswers() {
		for (String student : students) {
			List<List<String>> randomPicks = getChoices(choices);
			mapInitialAnswers.put(student, randomPicks);
		}
		return mapInitialAnswers;
		
	}
	
	/* Assigns the last submission of each student with their respective ID. 
	 * Because of the different dataTypes/Objects I chose, I had to make a separate case for 
	 * each type of question.
	 */
	private HashMap<String, List<String>> getLastAnswer() {
		if(type==1) {
			for (Map.Entry<String, List<List<String>>> entry : mapInitialAnswers.entrySet()) {
				// Since the last list of multiple choices is a list of Strings, a variable of type List<>String is created
				List<String> lastMultiplecChoice = new ArrayList<String>();
				// Get the last list in the list of multiple choices and assign it to 
				lastMultiplecChoice = entry.getValue().get(entry.getValue().size()-1);
				// Map the last list to the respective student
				mapLastSubmissions.put(entry.getKey(), lastMultiplecChoice);
				}
		}
		else {
			for (Map.Entry<String, List<List<String>>> entry : mapInitialAnswers.entrySet()) {
				// Since the last list of multiple choices is a list of Strings, a variable of type List<>String is created
				List<String> lastSingleChoice = new ArrayList<String>();
				lastSingleChoice = entry.getValue().get(entry.getValue().size()-1);
				/* Since the last element of a list is a String, but the values for mapLastSubmissions are of type List<String>
				 * I created a new variable of type List<String> to add that element to mapLastSubmissions */
				List<String> anotherNew = new ArrayList<String>();
				anotherNew.add(lastSingleChoice.get(lastSingleChoice.size()-1));
				mapLastSubmissions.put(entry.getKey(), anotherNew);
				}
		}
	
		return mapLastSubmissions;
		    
	}
	
	//Counts how many times each choice was chosen by all the students
	private void countFrequencies() {
		for (Map.Entry<String, List<String>> entry : mapLastSubmissions.entrySet()) {
			if (entry.getValue().contains("A")){
				A++;
			}
			if (entry.getValue().contains("B")){
				B++;
			}
			if (entry.getValue().contains("C")){
				C++;
			}
			if (entry.getValue().contains("D")){
				D++;
			}
			if (entry.getValue().contains("E")){
				E++;
			}
		}
	}

	// Given a list of choices, generate a random list of multiple choices for each student
	private List<List<String>> getChoices(List<String> choiceList) {
		Random randNum = new Random();
		List<List<String>> randomChoices = new ArrayList<List<String>>();
		
		if (type == 1) {
			/* For multiple choices (type == 1) the max amount of choices each students can make equals
			 * the size of the list of choices so that is added to the upper bound of nextInt
			 * +1 is added as to always have at least one choice */
			for (int i=0; i<randNum.nextInt(choiceList.size())+1; i++) {
				List<String> randomPicks = pickNRandom(choiceList, randNum.nextInt(choiceList.size()) + 1);
				randomChoices.add(randomPicks);
			}
		}
		else {
			/* For single choice (type == 0) a student should only change their mind from one answer to the other
			 * (i.e. if true was chosen, they can change their mind to false and vice versa). So, the upper bound of random
			 * generated answers for single choice is only 1 and +1 is added as to always have at least one choice */
			for (int i=0; i<randNum.nextInt(1)+1; i++) {
				List<String> randomPicks = pickNRandom(choiceList, randNum.nextInt(choiceList.size()) + 1);
				randomChoices.add(randomPicks);
			}
		}
		
		return randomChoices;
	}
	
	/* pickRandom is called inside getChoices to get a list of random shuffled choices
	 * it's assigned to copy as to leave the original list intact */
	private static List<String> pickNRandom(List<String> lst, int n) {
	    List<String> copy = new ArrayList<String>(lst);
	    Collections.shuffle(copy);
	    return copy.subList(0, n);
	}
	
	//returns the output of the generated results
	public void displayResutls() {
		System.out.println("\n"+question);

		System.out.println(specifics);
		
		if(type==1) {
			for (Map.Entry<String, List<List<String>>> e : initialAnswers().entrySet()) 
	            System.out.println("Student with ID: " + e.getKey() + " picked these answers: " + e.getValue().toString()); 
			
			System.out.println("\nHere are final submissions of each student:\n");
			
			for (Map.Entry<String, List<String>> e : getLastAnswer().entrySet()) 
	        System.out.println("Student with ID: " + e.getKey() + " final choice is: " + e.getValue().toString()); 
			
			countFrequencies();
			
			System.out.println("\nHere are the statistcs:");
			
			
			double percent = getLastAnswer().size();

			System.out.println("A : " + A + " -> " + (Math.round((A/percent)*100)) + "% of students chose this option" );
			System.out.println("B : " + B + " -> " + (Math.round((B/percent)*100)) + "% of students chose this option" );
			System.out.println("C : " + C + " -> " + (Math.round((C/percent)*100)) + "% of students chose this option" );
			System.out.println("D : " + D + " -> " + (Math.round((D/percent)*100)) + "% of students chose this option" );
			System.out.println("E : " + E + " -> " + (Math.round((E/percent)*100)) + "% of students chose this option" );
		}
		else {
			for (Map.Entry<String, List<List<String>>> e : initialAnswers().entrySet()) 
	            System.out.println("Student with ID: " + e.getKey() + " picked these answers: " + e.getValue().toString()); 
			
			System.out.println("\nHere are final submissions of each student:\n");
			
			for (Map.Entry<String, List<String>> e : getLastAnswer().entrySet()) 
	        System.out.println("Student with ID: " + e.getKey() + " final choice is: " + e.getValue().toString()); 
			
			countFrequencies();
			
			System.out.println("\nHere are the statistcs:");
			
			double percent = A+B+C+D+E;

			System.out.println("A : " + A + " -> " + (Math.round((A/percent)*100)) + "% of students chose this option" );
			System.out.println("B : " + B + " -> " + (Math.round((B/percent)*100)) + "% of students chose this option" );
		}
		
	}
	

}
