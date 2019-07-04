package controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import model.taxable.MoneyGift;

@Component("beforeCreateMoneyGiftValidator")
public class MoneyGiftValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return MoneyGift.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		MoneyGift b = (MoneyGift) target;
		if(b.getIncome() < 0)
        {
        	errors.rejectValue("income", "negative_value");
        }
        else if(b.getTax() < 0)
        {
        	errors.rejectValue("tax", "negative_value");
        }
        
		
	}

}
