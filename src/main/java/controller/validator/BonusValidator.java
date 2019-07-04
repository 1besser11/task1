package controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import model.taxable.Bonus;

@Component("beforeCreateBonusValidator")
public class BonusValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Bonus.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Bonus b = (Bonus) target;
		if (b.getName().length() == 0) {
        	errors.rejectValue("name", "empty");
        }
        else if (!b.getName().matches("^([A-Z\\sa-z-]{2,40}|[А-Щ\\sЬЮЯІЇЄа-щьюяіїєґ-]{2,40})$")) {
        	errors.rejectValue("name", "not_correct_format");
        }
        else if (b.getCompany().length() == 0) {
        	errors.rejectValue("company", "empty");
        }
        else if (!b.getCompany().matches("^([A-Za-zА-ЩЬЮЯІЇЄа-щьюяіїєґ-]{2,200})$")) {
        	errors.rejectValue("company", "not_correct_format");
        }
        else if (b.getReason().length() == 0) {
        	errors.rejectValue("reason", "empty");
        }
        else if (!b.getReason().matches("^([A-Z\\sa-zА-ЩЬЮЯІЇЄа-щьюяіїєґ-]{2,400})$")) {
        	errors.rejectValue("reason", "not_correct_format");
        }
        else if(b.getIncome() < 0)
        {
        	errors.rejectValue("income", "negative_value");
        }
        else if(b.getTax() < 0)
        {
        	errors.rejectValue("tax", "negative_value");
        }
        
		
	}

}
