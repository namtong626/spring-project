package springbootproject.springboot.contracts.repositories.profiles;

import org.springframework.data.jpa.repository.JpaRepository;
import springbootproject.springboot.models.UserLanguage;

public interface UserLanguageRepositoryInterface extends JpaRepository<UserLanguage, Long>{
}
