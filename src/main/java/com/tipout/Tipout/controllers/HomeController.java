package com.tipout.Tipout.controllers;


import com.tipout.Tipout.models.Employer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

@Controller
public class HomeController {

    @Autowired
    AuthenticationController authenticationController;


    @GetMapping
    public String returnIndex(HttpServletRequest request,
                              Model model){
        HttpSession session = request.getSession();
        Boolean loggedin = authenticationController.inSession(session);
        System.out.println(loggedin);
        model.addAttribute("loggedin",loggedin);
        model.addAttribute("title", "Welcome to Tipout");
        return "index";
    }
}
