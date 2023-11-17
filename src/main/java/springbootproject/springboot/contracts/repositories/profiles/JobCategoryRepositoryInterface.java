package springbootproject.springboot.contracts.repositories.profiles;

import org.springframework.data.jpa.repository.JpaRepository;
import springbootproject.springboot.models.JobCategory;

public interface JobCategoryRepositoryInterface extends JpaRepository<JobCategory, Long>{
}
