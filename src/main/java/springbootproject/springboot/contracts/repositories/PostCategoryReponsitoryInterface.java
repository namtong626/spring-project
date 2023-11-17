package springbootproject.springboot.contracts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import springbootproject.springboot.models.Post;

public interface PostReponsitoryInterface extends JpaRepository<Post, Long>{
    
    Post findByJobName(String jobName);
  

}

