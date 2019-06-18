package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import model.human.Human;

/**
 * Репозиторий для класса человек
 * @author nikita
 *
 */
public interface HumanRepository extends JpaRepository<Human, Long> {

   
}