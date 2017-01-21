package parser;

import lexer.token.Op;

public class BinaryExpression extends Expression{
	private Expression expr1, expr2;
	private Op op;
	
	public BinaryExpression(Op op, Expression expr1, Expression expr2) {
		this.op = op;
		this.expr1 = expr1;
		this.expr2 = expr2;
	}
	
	public String toString() {
		return "BinaryExpression(" + op.toString() + ", " + this.expr1 + ", " + this.expr2;
	}

	@Override
	public int eval(State<Integer> stateVar) throws ProgramError {
		int r1 = expr1.eval(stateVar);
		int r2 = expr2.eval(stateVar);
		switch(op) {
			case PLUS:
				return r1 + r2;
			case DIVIDE:
				return r1 / r2;
			case MINUS:
				return r1 - r2;
			case TIMES:
				return r1 * r2;
			case COMPARE:
				return (r1 == r2) ? 1 : 0;
			case LESS:
				return (r1 < r2) ? 1 : 0;
			default:
				return 0;
		}
	}
}
