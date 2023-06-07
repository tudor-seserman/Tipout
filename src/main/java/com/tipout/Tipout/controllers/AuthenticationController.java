package com.tipout.Tipout.controllers;

import com.tipout.Tipout.models.DTOs.EmployerLoginFormDTO;
import com.tipout.Tipout.models.DTOs.EmployerRegistrationFormDTO;
import com.tipout.Tipout.models.Employer;
import com.tipout.Tipout.models.data.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;
@Controller
public class AuthenticationController {
    @Autowired
    EmployerRepository employerRepository;

    private static final String userSessionKey = "employer";

    public Employer getEmployerFromSession(HttpSession session) {
        Integer employerId = (Integer) session.getAttribute(userSessionKey);
        if (employerId == null) {
            return null;
        }

        Optional<Employer> employer = employerRepository.findById(employerId);

        if (employer.isEmpty()) {
            return null;
        }

        return employer.get();
    }

    private static void setEmployerInSession(HttpSession session, Employer employer) {
        session.setAttribute(userSessionKey, employer.getId());
    }

    public boolean inSession(HttpSession session){
        Integer employerId = (Integer) session.getAttribute(userSessionKey);
        if (employerId == null) {
            return false;
        }

        Optional<Employer> employer = employerRepository.findById(employerId);

        if (employer.isEmpty()) {
            return false;
        }

        return true;
    }

    @GetMapping("/register")
    public String displayRegistrationForm(Model model) {
        model.addAttribute(new EmployerRegistrationFormDTO());
        model.addAttribute("title", "Register");
        return "register";
    }

    @PostMapping("/register")
    public String processRegistrationForm(@ModelAttribute @Valid EmployerRegistrationFormDTO employerRegistrationFormDTO,
                                          Errors errors,
                                          HttpServletRequest request,
                                          Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Register");
            return "register";
        }

        Employer existingUser = employerRepository.findByUsername(employerRegistrationFormDTO.getUsername());

        if (existingUser != null) {
            errors.rejectValue("username", "username.alreadyexists", "A user with that username already exists");
            model.addAttribute("title", "Register");
            return "register";
        }

        String password = employerRegistrationFormDTO.getPassword();
        String verifyPassword = employerRegistrationFormDTO.getVerifyPassword();
        if (!password.equals(verifyPassword)) {
            errors.rejectValue("password", "passwords.mismatch", "Passwords do not match");
            model.addAttribute("title", "Register");
            return "register";
        }

        Employer newUser = new Employer(employerRegistrationFormDTO.getBusinessName(), employerRegistrationFormDTO.getUsername(), employerRegistrationFormDTO.getPassword());
        employerRepository.save(newUser);
        setEmployerInSession(request.getSession(), newUser);

        return "redirect:";
    }

    @GetMapping("/login")
    public String displayLoginForm(Model model) {
        model.addAttribute(new EmployerLoginFormDTO());
        model.addAttribute("title", "Log In");
        return "login";
    }

    @PostMapping("/login")
    public String processLoginForm(@ModelAttribute @Valid EmployerLoginFormDTO employerLoginFormDTO,
                                   Errors errors, HttpServletRequest request,
                                   Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Log In");
            return "login";
        }

        Employer theEmployer = employerRepository.findByUsername(employerLoginFormDTO.getUsername());

        if (theEmployer == null) {
            errors.rejectValue("username", "user.invalid", "The given username does not exist");
            model.addAttribute("title", "Log In");
            return "login";
        }

        String password = employerLoginFormDTO.getPassword();

        if (!theEmployer.isMatchingPassword(password)) {
            errors.rejectValue("password", "password.invalid", "Invalid password");
            model.addAttribute("title", "Log In");
            return "login";
        }

        setEmployerInSession(request.getSession(), theEmployer);

        return "redirect:";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/login";
    }


}
