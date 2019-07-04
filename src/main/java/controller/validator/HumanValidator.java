package controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import model.human.Human;

@Component("beforeCreateHumanValidator")
public class HumanValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Human.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "field.required");
        
        
        Human p = (Human) target;
        if (p.getName().length() == 0) {
        	errors.rejectValue("name", "empty");
        }
        else if (!p.getName().matches("^([A-Z a-z-]{2,40}|[А-Щ ЬЮЯІЇЄа-щьюяіїєґ-]{2,40})$")) {
        	errors.rejectValue("name", "not_correct_format");
        }
        else if (p.getIncome() < 0) {
        	errors.rejectValue("income", "negativevalue");
        }
        else if (p.getSurname().length() == 0) {
        	errors.rejectValue("surname", "empty");
        }
        else if (!p.getSurname().matches("^([A-Z a-z-]{2,40}|[А-Щ ЬЮЯІЇЄа-щьюяіїєґ-]{2,40})$")) {
        	errors.rejectValue("surname", "not_correct_format");
        }
        else if (p.getTaxPayedAmount() < 0) {
        	errors.rejectValue("taxPayedAmount", "negativevalue");
        }
        
        
	}

}
