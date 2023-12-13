package springbootproject.springboot.contracts.repositories.profiles;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import springbootproject.springboot.models.JobCategory;
import springbootproject.springboot.response.jobcatedto.JobCategoryResponseDTO;

public interface JobCategoryRepositoryInterface extends JpaRepository<JobCategory, Long> {
    // @Query(value = "\n" + "select jc.id, jc.name \n" +
    // "from profiles as p join profiles_categories as pc on p.id = pc.profile_id\n"
    // +
    // " join job_categories as jc on jc.id = pc.job_category_id \n" +
    // "where p.id =:profileId ", nativeQuery = true)
    @Query(value = "\n" +
            "select jc.id, jc.name \n" +
            "from profiles as p\n" +
            "join profiles_categories as pc on p.id=pc.profile_id \n" +
            "join job_categories as jc on jc.id = pc.job_category_id \n" +
            "where p.id=:profileId", nativeQuery = true)

    List<JobCategoryResponseDTO> findALLByProfileId(Long profileId);
}
