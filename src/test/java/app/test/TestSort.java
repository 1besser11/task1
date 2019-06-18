package app.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

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

@RunWith(SpringRunner.class)
@DataJpaTest
public class TestSort {
	
	

	@Autowired
	public HumanService hs;
	
	@Autowired
	public FactoryProducer factoryProducer;
	
	@Autowired 
	TaxService taxService;
	
	@Autowired 
	TaxableService taxableService;
	
	@Before
	public void init() {

		PercentTax tax = taxService.save(new PercentTax("ua", 1.5, "military"));
		PercentTax tax2 = taxService.save(new PercentTax("ua", 18, "pdfo"));
		
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

		hs.save(h1);
    	hs.save(h2);
    	hs.save(h3);
    	
    	
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

	@Test
	public void testIncomeAsc() {
		
    	List<Human> sorted = hs.findPageSortedByIncomeAsc(10, 0);
    	for(Human h: sorted) {
    		System.out.println("5sorted!!!!!!!!!!!" +h);
    	}
    	
    	assertTrue(sorted.get(0).getIncome()<=sorted.get(1).getIncome());
    	assertTrue(sorted.get(1).getIncome()<=sorted.get(2).getIncome());
    	
		
	}
	
	@Test
	public void testIncomeDesc() {
		
    	List<Human> sorted = hs.findPageSortedByIncomeDesc(10, 0);
    	for(Human h: sorted) {
    		System.out.println("5sorted!!!!!!!!!!!" +h);
    	}
    	
    	assertTrue(sorted.get(0).getIncome()>=sorted.get(1).getIncome());
    	assertTrue(sorted.get(1).getIncome()>=sorted.get(2).getIncome());
    	
		
	}

	@Test
	public void testTaxDesc() {
		
    	List<Human> sorted = hs.findPageSortedByIncomeDesc(10, 0);
    	for(Human h: sorted) {
    		System.out.println("5sorted!!!!!!!!!!!" +h);
    	}
    	
    	assertTrue(sorted.get(0).getTaxPayedAmount()>=sorted.get(1).getTaxPayedAmount());
    	assertTrue(sorted.get(1).getTaxPayedAmount()>=sorted.get(2).getTaxPayedAmount());
    	
		
	}
	
	@Test
	public void testTaxAsc() {
		
    	List<Human> sorted = hs.findPageSortedByIncomeAsc(10, 0);
    	for(Human h: sorted) {
    		System.out.println("5sorted!!!!!!!!!!!" +h);
    	}
    	
    	assertTrue(sorted.get(0).getTaxPayedAmount()<=sorted.get(1).getTaxPayedAmount());
    	assertTrue(sorted.get(1).getTaxPayedAmount()<=sorted.get(2).getTaxPayedAmount());
    	
		
	}
}
