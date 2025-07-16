package com.scm.test.controller;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

@Controller
public class TestController {

    @GetMapping("/hello")
    public String greet() { // This method when hit will throw error because it will look for html or jsp with the name hello world
        System.out.println("sid");
        return "Hello World";
    }

    @GetMapping("/helloTemplate")
    public String greetTemplate(Model model) {
        System.out.println("sid");
        model.addAttribute("name", "Siddharth");
        model.addAttribute("title", "Testing");
        return "test/helloTemplate";
    }
}
