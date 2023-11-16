package springbootproject.springboot.contracts.repositories.profiles;

import org.springframework.data.jpa.repository.JpaRepository;
import springbootproject.springboot.models.UserExperience;

public interface UserExperienceRepositoryInterface extends JpaRepository<UserExperience, Long>{
}
