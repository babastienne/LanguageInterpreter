package parser;

@SuppressWarnings("serial")
public class SyntacticError extends Exception {
	
	public SyntacticError(String description){
		super("Syntactic Error : " + description);
	}

}
