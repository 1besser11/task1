package model.tax;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

/**
 * Класс реализующий процентный налог
 * @author nikita
 *
 */
@Entity
@Data
public class PercentTax implements ITax {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;

	String country = "";
	String name = "";
	String category = "";
	double percent;

    public PercentTax() {
    	
    }
    
	public PercentTax(String name, String c, double d, String category){
		setName(name);
		setCountry(c);
		setPercent(d);
		setCategory(category);
	}

	@Override
	public double calculate(double p) {
		return p * getPercent() / 100.0d;
	}
}
