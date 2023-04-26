package tech.ignitr.habitus.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

public class SiteController {

    @RequestMapping(path = "/")
    public ModelAndView showIndexPage() {
        return new ModelAndView("home.html");
    }

    @GetMapping(path = "/index")
    public ModelAndView showIndexPage2() {
        return new ModelAndView("home");
    }

    @GetMapping(path = "/dashboard")
    public ModelAndView showDashboard() {
        return new ModelAndView("dashboard");
    }

    @GetMapping(path = "error")
    public ModelAndView showError() {
        return new ModelAndView("error");
    }

    @GetMapping(path = "login")
    public ModelAndView showLogin() {
        return new ModelAndView("login");
    }
}
