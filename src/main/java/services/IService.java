package services;

import java.util.List;

/**
 * Интерфейс для сервисов с базовыми функциями
 * @author nikita
 *
 * @param <T> тип объектов с которыми будет работать сервис
 */
public interface IService<T> {

	/**
	 * Метод который выдает все объекты
	 * @return все найденные сохраненные объекты
	 */
    List<T> findAll();

    /**
     * Сохраняет сущность в бд
     * @param entity сущность для сохранения
     * @return сохраненный объект
     */
    T save(T entity);

    /**
     * Нахождение объекта по айди
     * @param id айди
     * @return найденный объект
     */
    T find(long id);

    /**
     * обновление объекта по айди
     * @param id айди
     * @param entity сущность обновления
     * @return обновленная сущность
     */
    T update(long id, T entity);

    /**
     * Удаление по айди
     * @param id айди
     */
    void delete(long id);
}