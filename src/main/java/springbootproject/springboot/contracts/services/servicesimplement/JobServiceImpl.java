package springbootproject.springboot.contracts.services.servicesimplement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springbootproject.springboot.contracts.repositories.profiles.JobRepositoryInterface;
import springbootproject.springboot.contracts.services.servicesinteface.JobServiceInterface;
import springbootproject.springboot.models.Job;
import springbootproject.springboot.response.jobdto.JobResponseDTO;
@Service
public class JobServiceImpl implements JobServiceInterface {
    @Autowired // ánh xạ tới Repo
    JobRepositoryInterface jobRepo;
    @Override
    public List<JobResponseDTO> getJobList() {
        List<Job> jobs = jobRepo.findAll();
        List<JobResponseDTO> jobResponseList = new ArrayList<>();
        for(int i=0; i<jobs.size(); i++){
            JobResponseDTO object = new JobResponseDTO();
            object.setId(jobs.get(i).getId());
            object.setJobName(jobs.get(i).getTitle());
            object.setStartDate(jobs.get(i).getStart_date());
            object.setEndDate(jobs.get(i).getEnd_date());
            object.setSalary(jobs.get(i).getSalary_from());
            object.setCompanyName(jobs.get(i).getCompany().getId().toString());
            jobResponseList.add(object);
        }
        return jobResponseList;
    }
    
}
