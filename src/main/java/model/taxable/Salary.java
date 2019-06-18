package model.taxable;

import java.util.Date;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class Salary extends Taxable {
	
	Date date;
	String company;
	String occupation;

}
