package lexer;

import lexer.token.Token;

public class SLexer {

	private static Lexer lex;
	
	public static void init(String[] args) {		
		try {
			lex = new Lexer(args[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Token getToken() {
		try {
			return lex.getToken();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
