package springbootproject.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {
    
    @RequestMapping("/admin/dashboard")
    public String dashboard() {
        return "pages/dashboard/index";
    }

    @RequestMapping("/calendar")
    public String calendar() {
        return "pages/calendar";
    }
}
