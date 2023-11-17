package springbootproject.springboot.contracts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import springbootproject.springboot.models.City;

public interface CityRepositoryInterface extends JpaRepository<City, Long>{
}
