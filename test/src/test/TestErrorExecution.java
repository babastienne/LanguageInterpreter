package test;

public class TestErrorExecution extends Test {
	public static void main(String[] args){
		Test.main(args);
		verbose = true;
		test(verbose, "test/executionError/errorNoExpressionToEvaluate.calc", "(=a 5)", "error", "No expression to evaluate");
		test(verbose, "test/executionError/errorNoExpressionToEvaluate2.calc", "", "error", "No expression to evaluate");
        test(verbose, "test/executionError/errorVariableAlreadyExist.calc", "(=a 8) (=a 9)", "error", "Variable already exist");
        test(verbose, "test/executionError/errorVariableNotDefine.calc", "(+ a b)", "error", "Variable not define");
        
		report();
	}
}
