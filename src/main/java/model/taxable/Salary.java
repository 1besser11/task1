package model.taxable;

import java.util.Date;

import javax.persistence.Entity;

import org.springframework.validation.Validator;

import controller.validator.SalaryValidator;
import lombok.Data;

@Data
@Entity
public class Salary extends Taxable {
	
	Date date;
	String company;
	String occupation;
	
	@Override
	public Validator getValidator() {
		return new SalaryValidator();
	}

}
