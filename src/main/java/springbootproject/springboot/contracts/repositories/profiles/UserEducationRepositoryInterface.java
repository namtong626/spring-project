package springbootproject.springboot.contracts.repositories.profiles;

import org.springframework.data.jpa.repository.JpaRepository;
import springbootproject.springboot.models.UserEducation;

public interface UserEducationRepositoryInterface extends JpaRepository<UserEducation, Long>{
}
