package lexer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lexer.token.Define;
import lexer.token.EOF;
import lexer.token.Equals;
import lexer.token.Identifier;
import lexer.token.If;
import lexer.token.LPar;
import lexer.token.Litteral;
import lexer.token.Op;
import lexer.token.RPar;
import lexer.token.Token;

public class Lexer {
	private FileReader in;
	private int i; // current ASCII character (coded as an integer)
	
	public Lexer(String filename) throws IOException {
		File file = new File(filename);
		try {
			in = new FileReader(file);
			i = in.read(); 		// initialize lexer
		} catch (FileNotFoundException e) {
			System.err.println("File : " + filename + " not found");
			throw e; 			// pass the exception up the stack
		} catch (IOException e){
			in.close(); 		// close the reader
			throw e; // pass the exception up the stack
		}
	}
	
	public List<Token> lex() throws UnexpectedCharacter, IOException {
		// return the list of tokens recorded in the file
		List<Token> tokens = new ArrayList<Token>();
		
		try {
			Token token = getToken();
	
			while (! (token instanceof EOF)) {
				tokens.add(token);
				token = getToken();
			}
			tokens.add(token); 		// this is the EOF token
		} catch (IOException e){
				in.close(); 		// close the reader
				throw e; 			// pass the exception up the stack
		}
		return tokens;
	}
	
	protected Token getToken() throws UnexpectedCharacter, IOException {
		switch (i){
		case '(' :
			i = in.read();
			return new LPar();
		case ')' :
			i = in.read();
			return new RPar();
		case '=' :
			i = in.read();
			if(i == '=') {
				i = in.read();
				return Op.COMPARE;
			} else {
				return new Equals();
			}
		
		// operators
		case '+' :
			i = in.read();
			return Op.PLUS;
		case '-' :
			i = in.read();
			return Op.MINUS;
		case '/' :
			i = in.read();
			return Op.DIVIDE;
		case '*' :
			i = in.read();
			return Op.TIMES;
		case '<' :
			i = in.read();
			return Op.LESS;

		case ' ' :
			i = in.read();
			return getToken();
		case '\t' :
			i = in.read();
			return getToken();
		case 13 :
			i = in.read();
			return getToken();
		case 10 :
			i = in.read();
			return getToken();
		case -1 : 
			in.close();
			return new EOF();
		default : 
			// Litteral
			if(Character.isDigit(i)) {
				if(i == '0') {
					i = in.read();
					return new Litteral(0);
				}
				String s ="";
				s += (i - '0');
				i = in.read();
				while(Character.isDigit(i)) {
					s += (i - '0');
					i = in.read();
				}
				
				return new Litteral(Integer.parseInt(s));
			}
			
			// Identifier
			if(Character.isAlphabetic(i)) {
				StringBuilder string = new StringBuilder();
				
				string.append((char) i);
				i = in.read();
				while(Character.isDigit(i) || Character.isAlphabetic(i)) {
					string.append((char) i);
					i = in.read();
				}
				// the last i contains a space, tab, a \n or a \r
				
				if(string.toString().equals("if")) {
					return new If();
				} else if(string.toString().equals("define")) {
					return new Define();
				} else {
					return new Identifier(string.toString());						
				}
			}
			
			throw new UnexpectedCharacter(i);
		}
	}
}


