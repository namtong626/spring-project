package springbootproject.springboot.contracts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import springbootproject.springboot.models.Country;
// import springbootproject.springboot.models.User;

public interface CountryRepositoryInterface extends JpaRepository<Country, Long>{
    Country findByName(String name);
}
