package controller;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import controller.validator.HumanValidator;
import exception.ValidationException;
import model.human.Human;
import services.HumanService;

@Controller
public class HumanController {
	
	@Autowired
	HumanService hs;
	
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new HumanValidator());
    }
    
    @RequestMapping(value = "/create", method=RequestMethod.POST)
    public void processFoo(@Valid @RequestBody Human human, Errors errors) { 
    	if(errors.hasErrors()) {
    		throw new ValidationException(errors);
    	}
    	
    	hs.save(human);
    }
    
    @ExceptionHandler({ValidationException.class})
    public ResponseEntity<?> handleException(ValidationException e) {
    	ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    	String msg = "";
    	for(ObjectError o: e.getErrors().getAllErrors()) {
    		try {
    			msg += messageSource.getMessage(o, Locale.getDefault()) + "\n";
    		}
    		catch(Exception exception){
    			msg += o.getCode() + "\n";
    		}
    	}
    	System.out.println(e.getErrors().getAllErrors());
    	
    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\": \"" + msg + "\"}");
    }

}
