package com.tipout.Tipout.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping
    public String returnIndex(Model model){
        model.addAttribute("title", "Welcome to Tipout");
        return "index";
    }
}
