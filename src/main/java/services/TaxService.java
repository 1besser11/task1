package services;

import static org.junit.Assert.assertFalse;

import java.util.List;

import org.eclipse.jdt.core.compiler.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.DataBinder;

import controller.validator.PercentTaxValidator;
import exception.TaxNotFoundException;
import model.tax.PercentTax;
import repositories.TaxRepository;

@Service
public class TaxService implements IService<PercentTax> {

    @Autowired
    @Qualifier("taxRepository")
    private TaxRepository taxRepository;

    @Override
    public List<PercentTax> findAll() {
        return taxRepository.findAll();
    }

    @Override
    public PercentTax save( PercentTax entity) {
    	DataBinder binder = new DataBinder(entity);
		binder.setValidator(new PercentTaxValidator());
		binder.validate();
		if(binder.getBindingResult().hasErrors()) {
			throw new RuntimeException(binder.getBindingResult().getAllErrors().toString());
		}
        return taxRepository.save(entity);
    }

    @Override
    public PercentTax find(long id) {
        return taxRepository.findById(id)
                .orElseThrow(() -> new TaxNotFoundException("Not found tax with id " + id));
    }
    
    
    public PercentTax find(String country) {
        return taxRepository.findByCountry(country)
                .orElseThrow(() -> new TaxNotFoundException("Not found tax with country " + country));
    }
    
    public PercentTax findByCategoryAndCountry(String category, String country) {
        return taxRepository.findByCategoryAndCountry(category, country)
                .orElseThrow(() -> new TaxNotFoundException("Not found tax with country " + country + category));
    }

    @Override
    public PercentTax update(long id, PercentTax entity) {
    	entity.setId(id);
    	return taxRepository.save(entity);
    }

    @Override
    public void delete(long id) {
    	taxRepository.deleteById(id);
    }


}