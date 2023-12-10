package springbootproject.springboot.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springbootproject.springboot.contracts.repositories.JobRepositoryInterface;
import springbootproject.springboot.contracts.services.JobServiceInterface;
import springbootproject.springboot.models.Job;
import springbootproject.springboot.reponse.JobReponse;

@Service
public class JobService implements JobServiceInterface {
    @Autowired
    private JobRepositoryInterface jobRepo;

    @Override
    public List<JobReponse> getJobDataList() {
        List<Job> jobs = jobRepo.findAll();
        List<JobReponse> jobReponseList = new ArrayList<>();
        for(int i=0; i<jobs.size();i++){
            JobReponse object = new JobReponse();
            object.setId(jobs.get(i).getId());
            object.setTitle(jobs.get(i).getTitle());
            object.setStart_date(jobs.get(i).getStart_date());
            object.setEnd_date(jobs.get(i).getEnd_date());
            object.setCompanyname(jobs.get(i).getDescription());
            jobReponseList.add(object);
        }
        return jobReponseList;
    }
    
}
