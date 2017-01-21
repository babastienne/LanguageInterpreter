package parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lexer.SLexer;
import lexer.UnexpectedCharacter;
import lexer.token.EOF;
import lexer.token.Equals;
import lexer.token.Identifier;
import lexer.token.LPar;
import lexer.token.Litteral;
import lexer.token.RPar;
import lexer.token.Token;

public class Body extends AST{

	private List<Definition> definitions = new ArrayList<Definition>();
	private Expression expr;
	private State<Integer> stateVar = new State<Integer>();
	
	public static Body parse(Token token) throws UnexpectedCharacter, IOException, SyntacticError {
		Body body = new Body();
		Token t = token;
		
		while(!(t instanceof EOF)) {
			if(t instanceof LPar) {
				Token t2 = SLexer.getToken();
				if(t2 instanceof Equals) {
					Definition def = Definition.parse(SLexer.getToken());
					body.definitions.add(def);
				} else {
					if(t2 instanceof Litteral)
						throw new SyntacticError("Litteral shouldn't be surrounded by parenthesis.");
					if(t2 instanceof Identifier)
						throw new SyntacticError("Identifier shouldn't be surrounded by parenthesis");
					body.expr = Expression.parse(t2);

					if(!(SLexer.getToken() instanceof EOF))
						throw new SyntacticError("Body shouldn't contains multiple expressions");
					return body;
				}
				t = SLexer.getToken();
				if(!(t instanceof RPar)) {
					throw new SyntacticError("Right parenthesis expected.");
				}
				t = SLexer.getToken();
			} else {
				if(!(t instanceof Identifier) && !(t instanceof Litteral))
					throw new SyntacticError("Program don't match the syntax");
				body.expr = Expression.parse(t);
				if(!(SLexer.getToken() instanceof EOF))
					throw new SyntacticError("Body shouldn't contains multiple expressions");
				return body;
			}
		}
		return body;
	
	}

	@Override
	public String toString() {
		StringBuilder stringB = new StringBuilder();
		for(Definition definition : definitions)
			stringB.append(definition.toString() + ", ");
		
		if(this.expr == null) {
			stringB.delete(stringB.length()-2, stringB.length());
			return "Body(" + stringB + ")";
		}
		return "Body(" + stringB + expr + ")";
	}
	
	public Expression getExpression() {
		return expr;
	}
	
	public List<Definition> getDefinitions() {
		return definitions;
	}
	
	public int eval() throws ProgramError {
		for(Definition d : definitions) {
			d.eval(stateVar);
		}
		if(this.expr != null)
			return expr.eval(stateVar);
		else
			throw new ProgramError("No expression to evaluate. File is maybe empty.");
	}
}
