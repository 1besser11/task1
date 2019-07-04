package model.taxable;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.springframework.validation.Validator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import model.human.Human;
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
public class Taxable implements ITaxable, IIncome, IValidator {
	
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	double income;
    double tax;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "human_id")
    private Human human;
    
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
			sum += t.calculate(getIncome());
		}
		tax = sum;
		return sum;
	}
	
	@Override
	public double getIncome() {
		return income;
	}
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Taxable )) return false;
        return id != 0 && id == (((Taxable) o).getId());
    }

	@Override
	public Validator getValidator() {
		return null;
	}

}
