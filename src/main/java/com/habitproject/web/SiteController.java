package com.habitproject.web;

import com.habitproject.configuration.SecurityConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SiteController {

    @RequestMapping(path = SecurityConfig.Site.SLASH)
    public ModelAndView showIndexPage() {
        return new ModelAndView("home.html");
    }

    @GetMapping(path = SecurityConfig.Site.INDEX)
    public ModelAndView showIndexPage2() {
        return new ModelAndView("home");
    }

    @GetMapping(path = SecurityConfig.Site.DASH)
    public ModelAndView showDashboard() {
        return new ModelAndView("dashboard");
    }

    @GetMapping(path = SecurityConfig.Site.ERROR)
    public ModelAndView showError() {
        return new ModelAndView("error");
    }

    @GetMapping(path = SecurityConfig.Site.LOGIN)
    public ModelAndView showLogin() {
        return new ModelAndView("login");
    }
}