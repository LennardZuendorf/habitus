package tech.ignitr.habitus.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SiteController {

    @RequestMapping(path = "/")
    public ModelAndView showIndexPage() {
        return new ModelAndView("index.html");
    }

    @GetMapping(path = "/index")
    public ModelAndView showIndexPage2() {
        return new ModelAndView("index.html");
    }

    @GetMapping(path = "/dashboard")
    public ModelAndView showDashboard() {
        return new ModelAndView("dashbaord.html");
    }

    @GetMapping(path = "error")
    public ModelAndView showError() {
        return new ModelAndView("error.html");
    }

    @GetMapping(path = "login")
    public ModelAndView showLogin() {
        return new ModelAndView("login.html");
    }
}
