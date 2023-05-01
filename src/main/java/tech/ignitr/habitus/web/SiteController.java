package tech.ignitr.habitus.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SiteController {

    @RequestMapping(path = "/")
    public String showIndexPage() {
        return "index";
    }

    @GetMapping(path = "/home")
    public String showDashboard() {
        return "home";
    }

    @GetMapping(path = "error")
    public String showError() {
        return "error";
    }

    @GetMapping(path = "login")
    public String showLogin() {
        return "index";
    }
}
