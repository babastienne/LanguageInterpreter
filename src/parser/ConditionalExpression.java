package parser;

public class ConditionalExpression extends Expression {
	private Expression expr1, expr2, expr3;
	
	public ConditionalExpression(Expression expr1, Expression expr2, Expression expr3) {
		this.expr1 = expr1;
		this.expr2 = expr2;
		this.expr3 = expr3;
	}
	
	public String toString() {
		return "ConditionalExpression(" + this.expr1 + ", " + this.expr2 + ", " + this.expr3 + ")";
	}

	@Override
	public int eval(State<Integer> stateVar) throws ProgramError {
		if(this.expr1.eval(stateVar) != 0) // if the first expression is evaluate as true
			return this.expr2.eval(stateVar);
		else // if the expression is equal to 0, then it's false and the result of the evaluation is the expr3
			return this.expr3.eval(stateVar);
	}
}
