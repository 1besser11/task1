package model.taxable;

import model.human.Human;

/**
 * Интерфейс ревлизующий передачу между людьми
 * @author nikita
 *
 */
public interface IGiveable {
	
	/**
	 * Метод для получения человека, который дает
	 * @return объект класса человек
	 */
	Human getFrom();
	
	/**
	 * Метод для получения человека который прнимает
	 * @return объект класса человек
	 */
	Human getTo();

}
