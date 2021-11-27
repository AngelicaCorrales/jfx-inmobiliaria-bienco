package exceptions;

public class NoValueException extends Exception{
	
	private static final long serialVersionUID = 1;

	public NoValueException(double v) {
		super("El precio del inmueble no puede ser cero. Usted ingreso "+v+" en el campo de texto de dicho atributo dentro del formulario, intentelo nuevamente.");
	}
}
