package springbootproject.springboot.contracts.services;

import java.util.List;

import springbootproject.springboot.reponse.JobReponse;

public interface JobServiceInterface {
    List<JobReponse> getJobDataList();
}
