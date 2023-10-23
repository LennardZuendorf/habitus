package tech.ignitr.habitus.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class WebController {

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/legal")
    public String publicTest(Model model) {
        return "legal";
    }

    @GetMapping("/app")
    public String app(Model model) {
        return "app";
    }
}
