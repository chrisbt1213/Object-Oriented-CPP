/*
 * Christian Batach
 * CS 3560 - 01
 * Dr. Sun
 * Sept 29, 2020
 */
public class MultipleChoice implements Question{
	
	private String declaredQuestion;
	private final int TYPE = 1;
	
	public MultipleChoice(String declaredQuestion) {
		this.declaredQuestion = declaredQuestion;
	}
	
	@Override
	public String setQuestion() {
		return "This is a Multiple Choice Question, you may select all that apply:\n\n" + declaredQuestion +"\n";
	}
	
	@Override
	public int getType() {
		return TYPE;
	}
	
	@Override
	public String specifyChoice() {
		return "A. Chick-fil-A | B.In-N-OUt | C.TacoBell | D.Popeyes | E.Jack In The Box\n";
	}

}
