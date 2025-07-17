package com.scm.scm20.controller.main;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexPageController {

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "About");
        return "root/about/about";
    }

    @GetMapping("/services")
    public String services(Model model) {
        model.addAttribute("title", "Services");
        return "root/services/services";
    }

    @GetMapping("/home")
    public String home() {
        return "root/home";
    }

}
