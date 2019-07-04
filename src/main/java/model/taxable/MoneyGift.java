package model.taxable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.springframework.validation.Validator;

import controller.validator.MoneyGiftValidator;
import controller.validator.SalaryValidator;
import lombok.Data;
import lombok.ToString;
import model.human.Human;

/**
 * Класс реализующий ценный подарок
 * @author nikita
 *
 */
@Data
@Entity
public class MoneyGift extends Taxable implements IGiveable {
    
	@ToString.Exclude
	@ManyToOne
	private Human from;
	
	@ToString.Exclude
	@ManyToOne
	private Human to;

	
	@Override
	public Validator getValidator() {
		return new MoneyGiftValidator();
	}


}
