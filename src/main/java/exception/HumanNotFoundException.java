package exception;


/**
 * Исключение которое которое показывает что запрашиваемый человек не был найден
 * @author nikita
 *
 */
public class HumanNotFoundException extends RuntimeException {
	
	public HumanNotFoundException() {
		
	}

	public HumanNotFoundException(String s) {
		super(s);
	}
}
