package springbootproject.springboot.contracts.services.servicesinteface;
import java.util.List;

import springbootproject.springboot.response.jobdto.JobResponseDTO;
public interface JobServiceInterface {
    List<JobResponseDTO> getJobList();
    
} 
