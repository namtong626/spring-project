package springbootproject.springboot.contracts.repositories.profiles;

import org.springframework.data.jpa.repository.JpaRepository;
import springbootproject.springboot.models.Welfare;

public interface WelfareRepositoryInterface extends JpaRepository<Welfare, Long>{
}
