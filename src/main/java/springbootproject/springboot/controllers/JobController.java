package springbootproject.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import springbootproject.springboot.contracts.repositories.JobRepositoryInterface;

@Controller
public class JobController {
    @Autowired
    private JobRepositoryInterface jobRepo;
    
    @GetMapping("/job")
    public ModelAndView getAllEmployees() {
		ModelAndView mav = new ModelAndView("job");
		mav.addObject("job", jobRepo.findAll());
		return mav;
	}
}
