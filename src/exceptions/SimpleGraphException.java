package exceptions;

public class SimpleGraphException extends Exception {

	
	private static final long serialVersionUID = 1;
	
	public SimpleGraphException() {
		super("It is a simple graph, multiple edges are not allowed");
	}

}
