package controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import model.taxable.Salary;

@Component("beforeCreateSalaryValidator")
public class SalaryValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Salary.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Salary b = (Salary) target;
		if (b.getCompany().length() == 0) {
        	errors.rejectValue("company", "empty");
        }
        else if (!b.getCompany().matches("^([A-Z\\sa-z0-9-]{2,40}|[А-Щ\\sЬЮЯІЇЄа-щьюяіїєґ0-9-]{2,40})$")) {
        	errors.rejectValue("company", "not_correct_format");
        }
        else if (b.getOccupation().length() == 0) {
        	errors.rejectValue("reason", "empty");
        }
        else if (!b.getOccupation().matches("^([A-Z\\sa-zА-ЩЬЮЯІЇЄа-щьюяіїєґ-]{2,400})$")) {
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
