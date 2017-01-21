package test;

public class TestErrorLexer extends Test {
	public static void main(String[] args){
		Test.main(args);
		verbose = true;
		test(verbose, "test/lexerError/errorUnexpectedCharacter.calc", "!", "error", "Unexpected Character + ascii code");
		test(verbose, "test/lexerError/errorDigit.calc", "", "error", "File not found exception");
        
		report();
	}
}
