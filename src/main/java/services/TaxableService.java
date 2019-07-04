package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.DataBinder;

import controller.validator.BonusValidator;
import controller.validator.MoneyGiftValidator;
import controller.validator.PercentTaxValidator;
import controller.validator.PrivelegeValidator;
import controller.validator.SalaryValidator;
import exception.TaxableNotFoundException;
import model.taxable.Bonus;
import model.taxable.Taxable;
import repositories.TaxableRepository;

/**
 * Сервис для всего что облагается  налогом
 * @author nikita
 *
 */
@Service
public class TaxableService implements IService<Taxable> {

    @Autowired
    @Qualifier("taxableRepository")
    private TaxableRepository taxableRepository;

    @Override
    public List<Taxable> findAll() {
        return taxableRepository.findAll();
    }

    @Override
    public Taxable save( Taxable entity) {
    	
    	entity.setTax(entity.calculateTaxSum());
    	DataBinder binder = new DataBinder(entity);
    	binder.setValidator(entity.getValidator());
		binder.validate();
		if(binder.getBindingResult().hasErrors()) {
			throw new RuntimeException(binder.getBindingResult().getAllErrors().toString());
		}
        return taxableRepository.save(entity);
    }

    @Override
    public Taxable find(long id) {
        return taxableRepository.findById(id)
                .orElseThrow(() -> new TaxableNotFoundException("Not found taxable with id " + id));
    }
    
    @Override
    public Taxable update(long id, Taxable entity) {
    	entity.setId(id);
    	return taxableRepository.save(entity);
    }

    @Override
    public void delete(long id) {
    	taxableRepository.deleteById(id);
    }


}