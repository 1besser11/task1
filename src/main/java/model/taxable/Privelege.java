package model.taxable;

import javax.persistence.Entity;

import org.springframework.validation.Validator;

import controller.validator.PrivelegeValidator;
import controller.validator.SalaryValidator;
import lombok.Data;

@Data
@Entity
public class Privelege extends Taxable {
	
	String name;
	String reason;
	
	@Override
	public Validator getValidator() {
		return new PrivelegeValidator();
	}



}
