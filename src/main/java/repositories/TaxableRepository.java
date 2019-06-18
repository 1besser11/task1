package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import model.taxable.Taxable;

/**
 * Репозиторий для всего что облагается налогом
 * @author nikita
 *
 */
public interface TaxableRepository extends JpaRepository<Taxable, Long> {

}