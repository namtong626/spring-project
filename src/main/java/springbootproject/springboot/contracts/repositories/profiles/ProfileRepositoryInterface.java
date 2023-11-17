package springbootproject.springboot.contracts.repositories.profiles;

import org.springframework.data.jpa.repository.JpaRepository;
import springbootproject.springboot.models.Profile;

public interface ProfileRepositoryInterface extends JpaRepository<Profile, Long>{
}
