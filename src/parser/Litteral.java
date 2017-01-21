package parser;

public class Litteral extends Expression {
	int value;
	
	public Litteral(int value) {
		this.value = value;
	}
	
	public String toString() {
		return "Litteral(" + this.value + ")";
	}

	@Override
	public int eval(State<Integer> stateVar) {
		return value;
	}
}
