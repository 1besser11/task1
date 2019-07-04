package controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import model.human.Human;
import model.tax.PercentTax;

@Component("beforeCreatePercentTaxValidator")
public class PercentTaxValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return PercentTax.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		PercentTax p = (PercentTax) target;
		if (p.getName() == null || p.getName().length() == 0) {
	       errors.rejectValue("name", "empty");
	    }
	    else if (!p.getName().matches("^([A-Za-z- ]{2,40}|[А-ЩЬЮЯІЇЄа-щьюяіїєґ-]{2,40})$")) {
	       errors.rejectValue("name", "not_correct_format");
	    }
	    else if (p.getCategory().length() == 0) {
	    	errors.rejectValue("category", "empty");
	    }
	    else if (!p.getCategory().matches("^([A-Za-z- ]{2,40}|[А-ЩЬЮЯІЇЄа-щьюяіїєґ-]{2,40})$")) {
	        errors.rejectValue("category", "not_correct_format");
	    }
	    else if (p.getCountry().length() == 0) {
	    	errors.rejectValue("country", "empty");
	    }
	    else if (!p.getCountry().matches("^[a-z]{2,3}$")) {
	        errors.rejectValue("country", "not_correct_format");
	    }
	    else if (p.getPercent() < 0) {
	    	errors.rejectValue("percent", "negative_value");
	    }
	    else if (p.getPercent() > 100) {
	        errors.rejectValue("percent", "percent.more_100");
	    }
	}

}
