package exception;

/**
 * Исключение которое которое показывает что запрашиваемый налог не был найден
 * @author nikita
 *
 */
public class TaxNotFoundException extends RuntimeException {
	
	public TaxNotFoundException() {
		
	}

	public TaxNotFoundException(String s) {
		super(s);
	}
}
