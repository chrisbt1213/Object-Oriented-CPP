/*
 * Christian Batach
 * CS 3560 - 01
 * Dr. Sun
 * Sept 29, 2020
 */
public class SingleChoice implements Question {
	
	private String declaredQuestion;
	private final int TYPE = 0;
	
	public SingleChoice(String declaredQuestion) {
		this.declaredQuestion = declaredQuestion;
	}
	
	@Override
	public String setQuestion() {
		return "This is a Single Choice Question, you may only select ONE best answer:\n\n" + declaredQuestion+"\n";
		
	}
	
	@Override
	public int getType() {
		return TYPE;
	}
	
	@Override
	public String specifyChoice() {
		return "A. True | B. False\n";
	}
}
