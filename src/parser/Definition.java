package parser;

import java.io.IOException;

import lexer.token.Identifier;
import lexer.token.LPar;
import lexer.token.RPar;
import lexer.SLexer;
import lexer.token.Token;
import lexer.UnexpectedCharacter;

public class Definition extends AST {

	private Variable name;
	private Expression value;
	
	public Definition(Variable name , Expression value) {
		this.name = name;
		this.value = value;
	}
	
	public static Definition parse(Token token) throws SyntacticError, UnexpectedCharacter, IOException {
		Token t = token;
		if(t instanceof Identifier) {
			Variable var = new Variable(((Identifier) t).getValue());
			Token t2 = SLexer.getToken();
			
			if(t2 instanceof lexer.token.Litteral) {
				Litteral exp = new Litteral(((lexer.token.Litteral) t2).getValue());
				return new Definition(var, exp);
			} else if(t2 instanceof LPar) {
				Expression exp =  Expression.parse(t2);
				return new Definition(var, exp);
			} else if(t2 instanceof RPar) {
				throw new SyntacticError("No value assignated to the variable " + var.getName() + ".");
			} else throw new SyntacticError("Unrecognized expression assignated to the variable : " + var.getName());
			
		} else if (t instanceof RPar) {
			throw new SyntacticError("Bad syntax for a definition of a variable : variable and expression expected after an equals");
		} else throw new SyntacticError("Bad syntax for a definition of a variable.");

	}
	
	@Override
	public String toString() {
		return "Definition(" + this.name + "," + this.value + ")";
	}
	
	public int eval(State<Integer> stateVar) throws ProgramError {
		if(!stateVar.containsKey(this.name.getName()))
			stateVar.bind(this.name.getName(), this.value.eval(stateVar));
		else
			throw new ProgramError("Variable already exist in the state");
		return this.value.eval(stateVar);
	}
	
}