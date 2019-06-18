package model.taxable;

import model.tax.PercentTax;

/**
 * Интерфейс для всего что можно обложить налогами
 * @author nikita
 *
 */
public interface ITaxable {
	
	/**
	 * Добавляет налог в множество всех налогов
	 * @param tax налог который надо добавить
	 */
	void addTax(PercentTax tax);
	
	/**
	 * Функция, которая расчитывает размер налога для уплаты
	 * @return размер налога
	 */
	double calculateTaxSum();
	
	/**
	 * Метод возвращает размер сохраненный размер налога
	 * @return размер налога
	 */
	double getTax();

}
