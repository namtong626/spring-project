package springbootproject.springboot.contracts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import springbootproject.springboot.models.District;

public interface DistrictRepositoryInterface extends JpaRepository<District, Long>{
}
