package model.taxable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.ToString;
import model.human.Human;

/**
 * Класс реализующий ценный подарок
 * @author nikita
 *
 */
@Data
@Entity
public class MoneyGift extends Taxable implements IGiveable {
    
	@ToString.Exclude
	@ManyToOne
	private Human from;
	
	@ToString.Exclude
	@ManyToOne
	private Human to;
	
	public MoneyGift(double p){
		setPrice(p);
	}
	
	public MoneyGift(Human from, Human to, double price){
		this.price = price;
		this.from = from;
		this.to = to;
	}

}
