package parser;

public class ProgramError extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ProgramError(String msg) {
		super("Error during the execution of the program : " + msg);
	}
}
