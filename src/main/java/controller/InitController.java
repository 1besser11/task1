package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import model.factory.ITaxFactory;
import model.factory.UATaxFactory;
import model.factory.producer.FactoryProducer;
import model.human.Human;
import model.law.ITaxLaw;
import model.tax.PercentTax;
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
       // uaTaxFactory.initiateTaxes();
        initiateData();
        return "init";
    }
    
    private void initiateData() {
		

		PercentTax tax = new PercentTax("name", "ua", 1.5, "military");
		
		PercentTax tax2 = new PercentTax("name", "ua", 18, "pdfo");
		

		taxService.save(tax);
		taxService.save(tax2);
		
		ITaxFactory factory = factoryProducer
				.getFactory("ua");
		
		ITaxLaw l = factory.getLaw();
	
		Human h1 = new Human();
    	h1.setName("Nikita");
    	h1.setSurname("Gordiienko");
    	
    	Human h2 = new Human();
    	h2.setName("Eugenia");
    	h2.setSurname("default");
    	Human h3 = new Human();
    	h3.setName("Larysa");
    	h3.setSurname("dEFF");
    	h1.addRelative(h3);
    	h1.addRelative(h2);

		h1 = hs.save(h1);
    	h2 = hs.save(h2);
    	h3 = hs.save(h3);
    	
    	
    	Bonus b = new Bonus();
    	b.setIncome(100);
    	b.setName("good worker");
    	b.setReason("bonus");
    	b.setCompany("company");
    	Salary s1 = new Salary();
    	s1.setIncome(3000);
    	s1.setCompany("Some company");
    	s1.setOccupation("some");
    	
    	Salary s2 = new Salary();
    	s2.setIncome(5000);
    	s2.setCompany("Some company 2");
    	s2.setOccupation("some");
    	Salary s3 = new Salary();
    	s3.setIncome(3000);
    	s3.setCompany("Some company 3");
    	s3.setOccupation("some");
    	Privelege p = new Privelege();
    	p.setIncome(100);
    	p.setName("good worker");
    	p.setReason("bonus");
    	MoneyGift gift = new MoneyGift();
    	gift.setFrom(h2);
    	gift.setTo(h1);
    	gift.setIncome(900);
    	
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
    	
		hs.update(h1.getId(), h1);
    	hs.update(h2.getId(), h2);
    	hs.update(h3.getId(), h3);
    	
    }
}