package com.scm.scm20.controller.main;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    // About Page

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "About");
        return "root/about/about";
    }


    // Services Page

    @GetMapping("/services")
    public String services(Model model) {
        model.addAttribute("title", "Services");
        return "root/services/services";
    }

    // Home Page

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("title", "Home");
        return "root/home";
    }

    // Contact Page

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("title", "Contact");
        return "root/contact/contact";
    }

    // Home Page

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("title", "Register");
        return "root/signup/signup";
    }

        // Home Page

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "Login");
        return "root/login/login";
    }

}
