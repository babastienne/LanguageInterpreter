package parser;

import lexer.token.Op;

public class UnaryMinus extends Expression {
	private Expression expr;
	private Op op;
	
	public UnaryMinus(Op op, Expression expr) {
		this.op = op;
		this.expr = expr;
	}
	
	public String toString() {
		return "UnaryMinus(" + this.op + ", " + this.expr + ")";
	}

	@Override
	public int eval(State<Integer> stateVar) throws ProgramError {
		return this.expr.eval(stateVar) * -1;
	}
}
