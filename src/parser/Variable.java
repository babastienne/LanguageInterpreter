package parser;

public class Variable extends Expression {
	private String name;
	
	public Variable(String value) {
		this.name = value;
	}
	
	public String toString() {
		return "Variable(" + this.name + ")";
	}

	@Override
	public int eval(State<Integer> stateVar) throws ProgramError {
		if(stateVar.containsKey(this.name))
			return stateVar.lookup(name);
		else
			throw new ProgramError("Variable not define");
	}
	
	public String getName() {
		return this.name;
	}
}
