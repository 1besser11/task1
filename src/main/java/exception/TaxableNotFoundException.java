package exception;


/**
 * Исключение которое которое показывает что запрашиваемый объект не был найден
 * @author nikita
 *
 */
public class TaxableNotFoundException extends RuntimeException {
	
	public TaxableNotFoundException() {
		
	}

	public TaxableNotFoundException(String s) {
		super(s);
	}
}
