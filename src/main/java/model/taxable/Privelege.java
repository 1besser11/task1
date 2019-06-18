package model.taxable;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class Privelege extends Taxable {
	
	String name;
	String reason;
	
	public Privelege(double price, String name){
		this.price = price;
		this.name = name;
	
	}
	


}
