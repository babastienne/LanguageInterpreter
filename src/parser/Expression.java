package parser;

import java.io.IOException;

import lexer.SLexer;
import lexer.UnexpectedCharacter;
import lexer.token.Op;
import lexer.token.RPar;
import lexer.token.Token;

public abstract class Expression extends AST {
	
	public abstract int eval(State<Integer> stateVar) throws ProgramError;
	
	public static Expression parse(Token t) throws SyntacticError, UnexpectedCharacter, IOException {
		
		if(t instanceof lexer.token.Litteral)
			return new parser.Litteral(((lexer.token.Litteral)t).getValue());
		
		
		else if(t instanceof lexer.token.Identifier)
			return new Variable(((lexer.token.Identifier)t).getValue());

		
		// Cas où l'expression est précédée d'une parenthèse
		else if(t instanceof lexer.token.LPar) {
			Token t2 = SLexer.getToken();
			
			if(t2 instanceof lexer.token.If) {
				try {
					Expression test = Expression.parse(SLexer.getToken());
					Expression vrai = Expression.parse(SLexer.getToken());
					Expression faux = Expression.parse(SLexer.getToken());
					
					Token t3 = SLexer.getToken();
					
					if(t3 instanceof RPar)
						return new ConditionalExpression(test, vrai, faux);
					else 
						throw new SyntacticError("");
					
				} catch(Exception e) {
					e.printStackTrace();
					throw new SyntacticError("Bad syntax for a conditionnal expression");
				}
				
			} else if(t2 instanceof Op) {
				try {
					Expression expr1 = Expression.parse(SLexer.getToken());
					Token t3 = SLexer.getToken();
					
					if(t3 instanceof RPar) {
						Op op = (Op)t2;
						switch(op) {
						case MINUS:
							return new UnaryMinus((Op)t2, expr1);
						default:
							throw new SyntacticError("Not a good syntaxe for an operation");
						}
					}
						
					
					else {
						Expression expr2 = Expression.parse(t3);
						Token t4 = SLexer.getToken();
						if(t4 instanceof RPar)
							return new BinaryExpression((lexer.token.Op)t2, expr1, expr2);
						else 
							throw new SyntacticError("Operation non available");
					}
				} catch(Exception e) {
					e.printStackTrace();
					throw new SyntacticError("Not a good syntax for an operation");
				}
				
			} 
			
			/*
			 * Note : selon la conception, les chiffres et variables présents dans une expression ne
			 * doivent pas être entourés de parenthèses. Si la syntaxe change et qu'elle autorise les
			 * chiffres et variables entourées de parenthèses, alors décommentez le bloc ci-dessous.
			 */
//			// gestion si jamais on écrit un chiffre entouré de parenthèses
//			else if(t2 instanceof lexer.token.Litteral) {
//				Token t4 = SLexer.getToken();
//				if(t4 instanceof lexer.token.RPar)
//					return new parser.Litteral(((lexer.token.Litteral)t2).getValue());
//				else
//					throw new SynctaticError("Bad syntax for a Litteral surrounded by parenthesis");
//			}
//			
//			// gestion si jamais on écrit une variable entourée de parenthèses
//			else if(t2 instanceof lexer.token.Identifier) {
//				Token t4 = SLexer.getToken();
//				if(t4 instanceof lexer.token.RPar)
//					return new Variable(((lexer.token.Identifier)t2).getValue());
//				else
//					throw new SynctaticError("Bad syntax for an Identifier surrounded by parenthesis");
//			}
			/*
			 * Fin du bloc de gestion des chiffres et variables entourés de parenthèses.
			 */
			
			else 
				throw new SyntacticError("Left parenthesis is open but no expression recognized.");
			
		/*
		 * cas où l'expression n'est pas précédée d'une parenthèse 
		 * (induit par le test effectué dans body) dans ce cas on 
		 * ne vérifie pas si l'expression est suivie d'une parenthèse, 
		 * le test est effectué dans le body	
		 */
		} else if(t instanceof lexer.token.If) {
			try {
				Expression test = Expression.parse(SLexer.getToken());
				Expression vrai = Expression.parse(SLexer.getToken());
				Expression faux = Expression.parse(SLexer.getToken());
				
				Token t3 = SLexer.getToken();
				
				if(t3 instanceof RPar)
					return new ConditionalExpression(test, vrai, faux);
				else 
					throw new SyntacticError("");
				
			} catch(Exception e) {
				e.printStackTrace();
				throw new SyntacticError("Bad syntax for a conditionnal expression");
			}

			
		} else if(t instanceof lexer.token.Op) {
			try {
				Expression expr1 = Expression.parse(SLexer.getToken());
				Token t3 = SLexer.getToken();
				
				if(t3 instanceof RPar) {
					lexer.token.Op op = (lexer.token.Op)t;
					switch(op) {
					case MINUS:
						return new UnaryMinus((lexer.token.Op)t, expr1);
					default:
						throw new SyntacticError("Bad syntaxe for an operation");
					}
				}
				else {
					Expression expr2 = Expression.parse(t3);
					Token t4 = SLexer.getToken();
					
					if(t4 instanceof RPar)
						return new BinaryExpression((lexer.token.Op)t, expr1, expr2);
					else 
						throw new SyntacticError("Bad syntax for an operation");
				}
			} catch(Exception e) {
				e.printStackTrace();
				throw new SyntacticError("Bad syntax for an operation");
			}
		} else
			throw new SyntacticError("Expression not recognized");
	}
}
