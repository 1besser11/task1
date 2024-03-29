package model.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import model.law.ITaxLaw;
import model.law.UATaxLaw;
import model.tax.PercentTax;
import services.TaxService;

/**
 * Класс реализующий фабрику для налогов Украиныы
 * @author nikita
 *
 */
@Component("uaTaxFactory")
public class UATaxFactory implements ITaxFactory {
	
	@Autowired 
	TaxService taxService;
	
	
	@Autowired 
	UATaxLaw uaTaxLaw;
	
	/**
	 * Код страны
	 */
	private String country = "ua";
	
	@Override
	public PercentTax getTax(String s) {
		switch(s.toLowerCase()) {
			case "military":
				return taxService.findByCategoryAndCountry("military", country);
			case "pdfo":
				return taxService.findByCategoryAndCountry("pdfo", country);
			default:
				throw new RuntimeException();
		}
	}

	@Override
	public ITaxLaw getLaw() {
		return uaTaxLaw;
	}
	
	/**
	 * Метод для первичной инициализации налогов в стране
	 */
	public void initiateTaxes() {
		taxService.save(new PercentTax("name", "ua", 1.5, "military"));
		taxService.save(new PercentTax("name","ua", 18, "pdfo"));
	}

}
