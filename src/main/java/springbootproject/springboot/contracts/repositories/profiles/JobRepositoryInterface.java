package springbootproject.springboot.contracts.repositories.profiles;

import org.springframework.data.jpa.repository.JpaRepository;

import springbootproject.springboot.models.Job;

public interface JobRepositoryInterface extends JpaRepository<Job, Long> {
}
