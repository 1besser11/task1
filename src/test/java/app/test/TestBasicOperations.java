package app.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import exception.TaxNotFoundException;
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
public class TestBasicOperations {
	
	@Autowired
	public HumanService hs;
	
	@Autowired
	public FactoryProducer factoryProducer;
	
	@Autowired 
	TaxService taxService;
	
	@Autowired 
	TaxableService taxableService;

	@Test
	public void testAddRelative() {
		Human h1 = new Human();
		h1.setName("Nikita");
		h1 = hs.save(h1);
		Human h2 = new Human();
		h2 = hs.save(h2);
		h2.setName("Eugenia");
		h2.addRelative(h1);
		System.out.println("!!!!!!!!!! " + h1);
		System.out.println("!!!!!!!!!! " + h2);
		
		assertTrue(h1.isRelative(h2));
	}
	
	@Test
	public void testMoneyGiftWithRelatives() {
		Human h1 = new Human();
		Human h2 = new Human();
		PercentTax tax = taxService.save(new PercentTax("ua", 1.5, "military"));
		PercentTax tax2 = taxService.save(new PercentTax("ua", 18, "pdfo"));
		System.out.println("!!!!!!!!!! " + tax);
		ITaxFactory factory = factoryProducer
				.getFactory("ua");
		
		ITaxLaw l = factory.getLaw();
		
		MoneyGift gift = l.taxify(new MoneyGift(h1, h2, 1000));
		System.out.println("!!!!!!!!!! " + gift.calculateTaxSum());
		assertTrue(Double.valueOf(gift.calculateTaxSum()).equals((1000 * 19.5 /100)));
	}
	
	@Test(expected = TaxNotFoundException.class)
	public void testTaxNotInitiated() {
		Human h1 = new Human();
		Human h2 = new Human();
		PercentTax tax = taxService.save(new PercentTax("ua", 1.5, "military"));
		
		System.out.println("!!!!!!!!!! " + tax);
		ITaxFactory factory = factoryProducer
				.getFactory("ua");
		
		ITaxLaw l = factory.getLaw();
		MoneyGift gift = l.taxify(new MoneyGift(h1, h2, 1000));
		System.out.println("!!!!!!!!!! " + gift.calculateTaxSum());
	}
	
	@Test
	public void testCheckIfTaxSaved() {
		Human h1 = new Human();
		Human h2 = new Human();
		PercentTax tax = taxService.save(new PercentTax("ua", 1.5, "military"));
		PercentTax tax2 = taxService.save(new PercentTax("ua", 18, "pdfo"));
		System.out.println("!!!!!!!!!! " + tax);
		ITaxFactory factory = factoryProducer
				.getFactory("ua");
		
		ITaxLaw l = factory.getLaw();
		
		MoneyGift gift = l.taxify(new MoneyGift(h1, h2, 1000));
		taxableService.save(gift);
		h1.addTaxable(gift);
		h1 = hs.save(h1);
		
		System.out.println("4!!!!!!!!!!!" +h1);
	}
	


}
