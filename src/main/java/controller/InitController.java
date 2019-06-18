package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import model.factory.ITaxFactory;
import model.factory.UATaxFactory;
import model.factory.producer.FactoryProducer;
import model.human.Human;
import model.law.ITaxLaw;
import model.taxable.Bonus;
import model.taxable.MoneyGift;
import model.taxable.Privelege;
import model.taxable.Salary;
import services.HumanService;
import services.TaxService;
import services.TaxableService;


/**
 *  Контроллер для инициализации начальных данных
 * @author nikita
 *
 */
@Controller
public class InitController {
	
	@Autowired
    UATaxFactory uaTaxFactory;
    

	@Autowired
	HumanService hs;
	
	@Autowired
	FactoryProducer factoryProducer;
	
	@Autowired 
	TaxService taxService;
	
	@Autowired 
	TaxableService taxableService;
	
    @GetMapping({"/init"})
    public String hello() {
    	System.out.println("init");
        uaTaxFactory.initiateTaxes();
        initiateData();
        return "init";
    }
    
    private void initiateData() {
		
		
		ITaxFactory factory = factoryProducer
				.getFactory("ua");
		
		ITaxLaw l = factory.getLaw();
	
		Human h1 = new Human();
    	h1.setName("Nikita");
    	h1.setSurname("Gordiienko");
    	
    	Human h2 = new Human();
    	h2.setName("Eugenia");
    	
    	Human h3 = new Human();
    	h3.setName("Larysa");
    	
    	h1.addRelative(h3);
    	h1.addRelative(h2);

		h1 = hs.save(h1);
    	h2 = hs.save(h2);
    	h3 = hs.save(h3);
    	
    	
    	Bonus b = new Bonus(100, "good worker");
    	Salary s1 = new Salary();
    	s1.setPrice(3000);
    	s1.setCompany("Some company");
    	
    	Salary s2 = new Salary();
    	s2.setPrice(5000);
    	s2.setCompany("Some company 2");
    	
    	Salary s3 = new Salary();
    	s3.setPrice(3000);
    	s3.setCompany("Some company 3");
    	
    	Privelege p = new Privelege(500, "good parent");
    	
    	MoneyGift gift = new MoneyGift(h2, h1, 900);
    	
		b = (Bonus) taxableService.save(l.taxify(b));
		s1 = (Salary) taxableService.save(l.taxify(s1));
		gift = (MoneyGift) taxableService.save(l.taxify(gift));
		
		s2 = (Salary) taxableService.save(l.taxify(s2));
		
		s3 = (Salary) taxableService.save(l.taxify(s3));
		p = (Privelege) taxableService.save(l.taxify(p));
		
		taxableService.save(b);
		taxableService.save(s1);
		taxableService.save(gift);
		
		taxableService.save(s2);
		
		taxableService.save(s3);
		taxableService.save(p);
		

    	h1.addTaxable(b);
    	h1.addTaxable(s1);
    	h1.addTaxable(gift);
    	
    	h2.addTaxable(s2);
    	
    	h3.addTaxable(s3);
    	h3.addTaxable(p);
    	
		hs.save(h1);
    	hs.save(h2);
    	hs.save(h3);
    	
    	
    	
    }
}