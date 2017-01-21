package test;

public class TestErrorSyntax extends Test {
	public static void main(String[] args){
		Test.main(args);
		verbose = true;
		test(verbose, "test/syntaxError/errorIf.calc", "(if 1 5)", "error", "Bad syntax for a conditionnal expression");
		test(verbose, "test/syntaxError/errorDigit.calc", "(5)", "error", "Litteral shouldn't be surrounded by parenthesis");
        test(verbose, "test/syntaxError/errorRecursive.calc", "(if (+ 5 (if 1 1)) 85 (+ 8 5))", "error", "Multiple error recursivly called");
        test(verbose, "test/syntaxError/errorDefinition.calc", "(= (var) 7)", "error", "Bad syntax for a definition of a variable");
        test(verbose, "test/syntaxError/errorIdentifier.calc", "(var)", "error", "Identifier shouldn't be surrounded by parenthesis");
        test(verbose, "test/syntaxError/errorOperation.calc", "(+ 5 8 9)", "error", "Bad syntax for an operation");
        test(verbose, "test/syntaxError/errorOperation2.calc", "( + 8)", "error", "Bad syntax for an operation");
        test(verbose, "test/syntaxError/errorProgramSyntax.calc", "* 8 5", "error", "Program don't match the syntax");
        test(verbose, "test/syntaxError/errorRightParenthesis.calc", "(= a 7", "error", "Right parenthesis expected");
        test(verbose, "test/syntaxError/errorUnrecognizedExpression.calc", "( * * 8 5)", "error", "Expression not recognized");
        
		report();
	}
}
