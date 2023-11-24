package springbootproject.springboot.contracts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import springbootproject.springboot.models.Category;

public interface CategoryReponsitoryInterface extends JpaRepository<Role, Long>{
    
    Category findByName(String category_name);
}

