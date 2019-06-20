package model.factory.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import model.factory.ITaxFactory;
import model.factory.PLTaxFactory;
import model.factory.UATaxFactory;

@Component("factoryProducer")
public class FactoryProducer {
	
	static FactoryProducer producer;
	static ITaxFactory last;
	
	@Autowired
	@Qualifier("uaTaxFactory")
	UATaxFactory uaTaxFactory;
	
	@Autowired
	@Qualifier("plTaxFactory")
	PLTaxFactory plTaxFactory;
	

	public ITaxFactory getFactory(String s) {
		switch(s.toLowerCase()) {
			case "ua":
				return uaTaxFactory;
			case "pl":
				return plTaxFactory;
			default:
				throw new RuntimeException();
		}	
	}
	
}
