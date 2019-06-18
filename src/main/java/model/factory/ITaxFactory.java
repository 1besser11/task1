package model.factory;

import model.law.ITaxLaw;
import model.tax.ITax;

/**
 * Интерфейс для фабрики налогов
 * @author nikita
 *
 */
public interface ITaxFactory {
	
	/**
	 * Ищет налог по общему названию
	 * @param s общее нззвание 
	 * @return объект налога
	 */
	ITax getTax(String s);
	
	/**
	 * Дает экземпляр налогообложения для данной страны
	 * @return
	 */
	ITaxLaw getLaw();
}
