package com.example.spring.study.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/hi")
    public String Meetyou(Model model) {
        model.addAttribute("username","미쯔");
        return "greetings"; // templates/greetings.mustache
    }

    @GetMapping("/bye")
    public String Next(Model model){
        model.addAttribute("nickname","옆누");
        return "goodbye";
    }
}
