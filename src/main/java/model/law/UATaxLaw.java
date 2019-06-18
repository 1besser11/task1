package model.law;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import model.factory.UATaxFactory;
import model.taxable.Bonus;
import model.taxable.MoneyGift;
import model.taxable.Privelege;
import model.taxable.Salary;

/**
 * Класс реализующий законы налогообложения Украины
 * @author nikita
 *
 */
@Component
public class UATaxLaw implements ITaxLaw {
	
	@Autowired
	@Qualifier("uaTaxFactory")
	UATaxFactory uaTaxFactory;

	@Override
	public MoneyGift taxify(MoneyGift gift) {
		if(gift.getPrice() > 850.0 && !gift.getFrom().isRelative(gift.getTo())) {
			
			gift.addTax(uaTaxFactory.getTax("military"));
			gift.addTax(uaTaxFactory.getTax("pdfo"));
		}
		return gift;
		
	}

	@Override
	public Salary taxify(Salary gift) {
		gift.addTax(uaTaxFactory.getTax("military"));
		gift.addTax(uaTaxFactory.getTax("pdfo"));
		return gift;
	}

	@Override
	public Bonus taxify(Bonus gift) {
		gift.addTax(uaTaxFactory.getTax("military"));
		gift.addTax(uaTaxFactory.getTax("pdfo"));
		return gift;
	}

	@Override
	public Privelege taxify(Privelege gift) {
		return gift;
	}

	
	


}
