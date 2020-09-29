/*
 * Christian Batach
 * CS 3560 - 01
 * Dr. Sun
 * Sept 29, 2020
 */
interface Question {
	
	//returns a String that explains whether students can choose only one or multiple answers
	public String setQuestion();
	
	//assigns a type for each kind of question: 0 for single choice and 1 for multiple choice
	public int getType();
	
	//returns a string that specifies what the specific choices each letter contains
	public String specifyChoice();
	
}
