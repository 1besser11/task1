package model.tax;

/**
 * Интерфейс налога
 * @author nikita
 *
 */
public interface ITax {
	
	/**
	 * Устанавливает процент налога
	 * @param d
	 */
	void setPercent(double d);
	
	/**
	 * Отдает процент данного налога
	 * @return
	 */
	double getPercent();
	
	/**
	 * Расчитывает налог для данной суммы
	 * @param p сумма
	 * @return размер налога
	 */
	double calculate(double p);
	

}
