package exception;

import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

public class ValidationException extends RuntimeException {
	
	Errors errors;
	
	public ValidationException() {
		
	}
	
	public ValidationException(Errors list){
		this.errors = list;
	}
	
	public Errors getErrors() {
		return errors;
	}

}
