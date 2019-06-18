package repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import model.tax.PercentTax;

/**
 * Репозиторий для налога
 * @author nikita
 *
 */
public interface TaxRepository extends JpaRepository<PercentTax, Long> {

	Optional<PercentTax> findByCountry(String country);
	
	Optional<PercentTax> findByCategoryAndCountry(String category, String country);
}
