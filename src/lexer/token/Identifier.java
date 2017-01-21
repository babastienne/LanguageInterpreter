package lexer.token;

public class Identifier implements Token {
	private String value;
	
	public Identifier(String i) {
		this.value = i;
	}
	
	public String toString(){
		return "Identifier";
	}
	
	public String getValue() {
		return this.value;
	}
}
