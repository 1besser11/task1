package model.law;

import model.taxable.Bonus;
import model.taxable.MoneyGift;
import model.taxable.Privelege;
import model.taxable.Salary;

/**
 * Интерфейс для законов налогообложения
 * @author nikita
 *
 */
public interface ITaxLaw {
	
	/**
	 * Облагает ценный подарок налогами в соответствии с законом данной страны
	 * @param gift подарок
	 * @return объект подарка
	 */
	MoneyGift taxify(MoneyGift gift);
	
	/**
	 * Облагает зарплату налогами в соответствии с законом данной страны
	 * @param salary зарпалата
	 * @return объект зарплаты
	 */
	Salary taxify(Salary salary);
	
	/**
	 * Облагает премию налогами в соответствии с законом данной страны
	 * @param bonus премия
	 * @return объект премии
	 */
	Bonus taxify(Bonus bonus);
	
	/**
	 * Облагает льготу налогами в соответствии с законом данной страны
	 * @param privelege льгота
	 * @return объект льготы
	 */
	Privelege taxify(Privelege privelege);

}
