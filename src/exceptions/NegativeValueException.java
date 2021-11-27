package exceptions;

public class NegativeValueException extends Exception{
	
	private static final long serialVersionUID = 1;

	public NegativeValueException(double v) {
		super("El precio del inmueble no puede ser negativo. Usted ingreso "+v+" en el campo de texto de dicho atributo dentro del formulario, intentelo nuevamente.");
	}
}
