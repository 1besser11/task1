package services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.DataBinder;

import controller.validator.HumanValidator;
import controller.validator.PercentTaxValidator;
import exception.HumanNotFoundException;
import exception.ValidationException;
import model.human.Human;
import repositories.HumanRepository;


/**
 * Сервис для работы с объектами типа Человек
 * @author nikita
 *
 */
@Service
public class HumanService implements IService<Human> {

    @Autowired
    @Qualifier("humanRepository")
    private HumanRepository humanRepository;

    @Override
    public List<Human> findAll() {
        return humanRepository.findAll();
    }

    @Override
    public Human save(Human entity) {
    	DataBinder binder = new DataBinder(entity);
		binder.setValidator(new HumanValidator());
		binder.validate();
		if(binder.getBindingResult().hasErrors()) {
			throw new RuntimeException();
		}
        return humanRepository.save(entity);
    }

    @Override
    public Human find(long id) {
        return humanRepository.findById(id)
                .orElseThrow(() -> new HumanNotFoundException("Not found tax with id " + id));
    }

    @Override
    public Human update(long id, Human entity) {
    	entity.setId(id);
    	return humanRepository.save(entity);
    }

    @Override
    public void delete(long id) {
    	humanRepository.deleteById(id);
    }
    
    public List<Human> findPageSortedByIncomeDesc(int size, int page) {
    	Page<Human> result = humanRepository
    			.findAll(PageRequest.of(size * page, size * (page + 1),
    					Sort.by(Sort.Direction.DESC, "income")));
    	return result.getContent();
    }
    
    public List<Human> findPageSortedByIncomeAsc(int size, int page) {
    	Page<Human> result = humanRepository
    			.findAll(PageRequest.of(size * page, size * (page + 1),
    					Sort.by(Sort.Direction.ASC, "income")));
    	return result.getContent();
    }
    
    public List<Human> findPageSortedByTaxDesc(int size, int page) {
    	Page<Human> result = humanRepository
    			.findAll(PageRequest.of(size * page, size * (page + 1), 
    					Sort.by(Sort.Direction.DESC, "taxPayedAmount")));
    	return result.getContent();
    }
    
    public List<Human> findPageSortedByTaxAsc(int size, int page) {
    	Page<Human> result = humanRepository
    			.findAll(PageRequest.of(size * page, size * (page + 1), 
    					Sort.by(Sort.Direction.ASC, "taxPayedAmount")));
    	return result.getContent();
    }


}