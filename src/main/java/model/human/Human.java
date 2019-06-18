package model.human;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import model.taxable.Taxable;

/**
 * Класс для реализация типа Человек
 * @author nikita
 *
 */
@Data
@Entity
@EqualsAndHashCode(exclude={"taxables", "relatives"})
public class Human {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	String name;
	String surname;
	

	double income;
	

	double taxPayedAmount;
	
	@ToString.Exclude
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "human_human",
            joinColumns = @JoinColumn(name = "human1_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "human2_id", referencedColumnName = "id"))
	Set<Human> relatives = new HashSet<Human> ();
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "human_taxable",
            joinColumns = @JoinColumn(name = "human", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "taxable", referencedColumnName = "id"))
	Set<Taxable> taxables = new HashSet<Taxable> ();
	
    /**
     * Добавляет родственную связь
     * @param human объект для добавления в родственники
     */
	public void addRelative(Human human) {
		this.relatives.add(human);
		human.relatives.add(this);
	}
	
	/**
	 * Проверяет являются ли люди родствениками
	 * @param human объект типа человек
	 * @return 
	 */
	public boolean isRelative(Human human) {
		if(relatives.contains(human)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Добавляет налогообложенные предметы в множество
	 * @param smth налогообложенный предмет
	 */
	public void addTaxable(Taxable smth) {
		taxables.add(smth);
		income += smth.getPrice();
		taxPayedAmount += smth.getTax();
	}
	
	
}
