package springbootproject.springboot.controllers;
import java.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import springbootproject.springboot.contracts.services.servicesinteface.JobServiceInterface;
import springbootproject.springboot.response.jobdto.JobResponseDTO;

@Controller
public class JobController {
    JobServiceInterface jobService;
    public JobController (JobServiceInterface jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/admin/jobs")
    public String index(Model model){
        List<JobResponseDTO> job = this.jobService.getJobList();
        model.addAttribute("jobs", job);
        return "pages/jobs/index";
    }

    // @PostMapping("/admin/jobs/data")
    // @ResponseBody
    // public JobResponseDTO getListData(
    //         @RequestParam MultiValueMap<String, String> params) {
    //     int start = Integer.parseInt(params.getFirst("start"));
    //     int length = Integer.parseInt(params.getFirst("length"));
    //     String searchValue = params.getFirst("search") != "" ? params.getFirst("search") : null;
                
    //         return null;
    //     }
}
