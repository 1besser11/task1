package model.taxable;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;
import model.tax.ITax;
import model.tax.PercentTax;


/**
 * Класс реализующий базовые функции любого дохода который можно обложить налогами
 * @author nikita
 *
 */
@Data
@Entity
@Inheritance(
	    strategy = InheritanceType.JOINED
	)
@EqualsAndHashCode(exclude={"taxes"})
public class Taxable implements ITaxable, IIncome {
	
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
    String name;
	double price;
    double tax;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "taxable_tax",
            joinColumns = @JoinColumn(name = "taxable", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tax", referencedColumnName = "id"))
	private Set<PercentTax> taxes = new HashSet<PercentTax>();
	
	@Override
	public void addTax(PercentTax tax) {
		taxes.add(tax);
	}

	@Override
	public double calculateTaxSum() {
		double sum = 0.0;
		for(ITax t: taxes) {
			sum += t.calculate(getPrice());
		}
		tax = sum;
		return sum;
	}
	
	@Override
	public double getIncome() {
		return price;
	}

}
