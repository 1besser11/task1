package model.taxable;

import javax.persistence.Entity;

import lombok.Data;

/**
 * Класс реализующий премию
 * @author nikita
 *
 */
@Data
@Entity
public class Bonus extends Taxable {
	
	String name;
	String reason;
	String company;
	
	public Bonus(double price, String name){
		this.price = price;
		this.name = name;
	
	}
	


}
