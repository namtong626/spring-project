package springbootproject.springboot.contracts.repositories.profiles;

import org.springframework.data.jpa.repository.JpaRepository;
import springbootproject.springboot.models.UserJobProfile;

public interface UserJobProfileRepositoryInterface extends JpaRepository<UserJobProfile, Long>{
}
