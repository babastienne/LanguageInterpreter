package calc;

import java.io.IOException;

import lexer.SLexer;
import lexer.UnexpectedCharacter;
import parser.Body;
import parser.ProgramError;
import parser.SyntacticError;

public class Calc {
	
	/**
	 * @param args - arg[0] is the filename of the file to interpret.
	 * @throws SyntacticError 
	 * @throws IOException 
	 * @throws UnexpectedCharacter 
	 * @throws ProgramError 
	 */
	public static void main(String[] args) throws UnexpectedCharacter, IOException, SyntacticError, ProgramError {		
			SLexer.init(args);

			Body body = Body.parse(SLexer.getToken()); // on parse le body
			int result = body.eval(); 		// on �value l'expression
			
			System.out.println(result); 	// on renvoi le r�sultat de l'�valuation
				
//			System.out.println(body);// d�commentez pour afficher le r�sultat du parser en plus de l'�valuation de l'expressions
	}
}
