package springbootproject.springboot.contracts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import springbootproject.springboot.models.Country;

public interface CountryRepositoryInterface extends JpaRepository<Country, Long>{
}
