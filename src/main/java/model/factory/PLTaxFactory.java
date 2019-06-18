package model.factory;

import org.springframework.stereotype.Component;

import model.law.ITaxLaw;
import model.tax.ITax;

@Component("plTaxFactory")
public class PLTaxFactory implements ITaxFactory {

	private PLTaxFactory() {
		
	}
	
	@Override
	public ITax getTax(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ITaxLaw getLaw() {
		// TODO Auto-generated method stub
		return null;
	}

}
