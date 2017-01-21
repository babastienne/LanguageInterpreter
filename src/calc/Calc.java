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
			int result = body.eval(); 		// on évalue l'expression
			
			System.out.println(result); 	// on renvoi le résultat de l'évaluation
				
//			System.out.println(body);// décommentez pour afficher le résultat du parser en plus de l'évaluation de l'expressions
	}
}
