package springbootproject.springboot.controllers;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import springbootproject.springboot.contracts.services.JobServiceInterface;
import springbootproject.springboot.reponse.JobReponse;

@Controller
public class JobController {
    protected JobServiceInterface jobService;
    public JobController(JobServiceInterface jobService) {
      this.jobService = jobService;
    }

    @GetMapping("/job")
    public String index(Model model) {
    List<JobReponse> job = this.jobService.getJobDataList();
    model.addAttribute("jobs", job);
    return "pages/job";
}
}