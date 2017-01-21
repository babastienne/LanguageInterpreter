package lexer.token;

public class Litteral implements Token {
	
	private int value;
	
	public Litteral(int i) {
		this.value = i;
	}
	
	public String toString(){
		return "Digit";
	}
	
	public int getValue() {
		return this.value;
	}
}
