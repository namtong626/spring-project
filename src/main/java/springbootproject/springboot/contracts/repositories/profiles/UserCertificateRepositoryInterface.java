package springbootproject.springboot.contracts.repositories.profiles;

import org.springframework.data.jpa.repository.JpaRepository;
import springbootproject.springboot.models.UserCertificate;

public interface UserCertificateRepositoryInterface extends JpaRepository<UserCertificate, Long>{
}
