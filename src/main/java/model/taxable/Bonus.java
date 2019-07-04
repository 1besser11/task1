package model.taxable;

import javax.persistence.Entity;

import org.springframework.validation.Validator;

import controller.validator.BonusValidator;
import controller.validator.SalaryValidator;
import lombok.Data;

/**
 * Класс реализующий премию
 * @author nikita
 *
 */
@Data
@Entity
public class Bonus extends Taxable {
	
	String name;
	String reason;
	String company;
	

	@Override
	public Validator getValidator() {
		return new BonusValidator();
	}


}
