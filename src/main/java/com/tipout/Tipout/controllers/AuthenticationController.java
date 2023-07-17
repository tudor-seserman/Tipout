package com.tipout.Tipout.controllers;

import com.tipout.Tipout.models.DTOs.EmployerLoginFormDTO;
import com.tipout.Tipout.models.DTOs.EmployerRegistrationFormDTO;
import com.tipout.Tipout.models.Employer;
import com.tipout.Tipout.models.data.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/*
Controls authentication for Employers currently
 */
@RestController
@RequestMapping("")
public class AuthenticationController {
    @Autowired
    EmployerRepository employerRepository;

// Name to be used as a key tied to employee id a session
    private static final String userSessionKey = "employer";

//Method to extract Employer information from session information
    public Employer getEmployerFromSession(HttpSession session) {
        Long employerId = (Long) session.getAttribute(userSessionKey);
        if (employerId == null) {
            return null;
        }

        Optional<Employer> employer = employerRepository.findById(employerId);

        if (employer.isEmpty()) {
            return null;
        }

        return employer.get();
    }

//    Method to create Employer sessions
    private static void setEmployerInSession(HttpSession session,
                                             Employer employer) {
        session.setAttribute(userSessionKey, employer.getId());
    }

//Method to see if a user is in session. Used to conditionally display content.
    public boolean inSession(HttpSession session){
        Long employerId = (Long) session.getAttribute(userSessionKey);
        if (employerId == null) {
            return false;
        }

        Optional<Employer> employer = employerRepository.findById(employerId);

        if (employer.isEmpty()) {
            return false;
        }

        return true;
    }
//Displays registration form
    @GetMapping("/register")
    public String displayRegistrationForm(Model model) {
        model.addAttribute(new EmployerRegistrationFormDTO());
        model.addAttribute("title", "Register");
        return "register";
    }


    @RequestMapping(value = "/register", method = POST, produces = "application/json")
    public HttpStatus processRegistrationForm(@RequestBody @Valid EmployerRegistrationFormDTO employerRegistrationFormDTO,
                                                Errors errors) {

        if(errors.hasErrors()){
            System.out.println(errors.getAllErrors());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not create account");
        }

        Employer existingUser = employerRepository.findByUsername(employerRegistrationFormDTO.getUsername());


        if (existingUser != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A user with that username already exists");
        }



        Employer newUser = new Employer(employerRegistrationFormDTO.getBusinessName(), employerRegistrationFormDTO.getUsername(), employerRegistrationFormDTO.getPassword());
        employerRepository.save(newUser);
        return HttpStatus.OK;
    }

// Processes registration form.
    /*
    @RequestMapping(value = "/register", method = POST, produces = "application/json")
    public HttpStatus processRegistrationForm(@RequestBody EmployerRegistrationFormDTO employerRegistrationFormDTO,
                                              @RequestBody String test,
                                          HttpServletRequest request,
                                          Model model) {
        System.out.println(employerRegistrationFormDTO+employerRegistrationFormDTO.getUsername()+" "+employerRegistrationFormDTO.getPassword());
        System.out.println(test);
 Checks for validation errors
        if (errors.hasErrors()) {
            model.addAttribute("title", "Register");
            return "register";
        }

        Employer existingUser = employerRepository.findByUsername(employerRegistrationFormDTO.getUsername());

//Makes sure that username isn't duplicated
        if (existingUser != null) {
            errors.rejectValue("username", "username.alreadyexists", "A user with that username already exists");
            model.addAttribute("title", "Register");
            return "register";
        }

        String password = employerRegistrationFormDTO.getPassword();
        String verifyPassword = employerRegistrationFormDTO.getVerifyPassword();

//      Makes sure that the passwords match up.
        if (!password.equals(verifyPassword)) {
            errors.rejectValue("password", "passwords.mismatch", "Passwords do not match");
            model.addAttribute("title", "Register");
            return "register";
        }
//     After checks, Employer is created, saved, and set as in session.
        Employer newUser = new Employer(employerRegistrationFormDTO.getBusinessName(), employerRegistrationFormDTO.getUsername(), employerRegistrationFormDTO.getPassword());
        employerRepository.save(newUser);
        setEmployerInSession(request.getSession(), newUser);

        return HttpStatus.OK;
    }
    */

//  Displays login page
    @GetMapping("/login")
    public String displayLoginForm(Model model) {
        model.addAttribute(new EmployerLoginFormDTO());
        model.addAttribute("title", "Log In");
        return "login";
    }

//  Processes login form
    @PostMapping("/login")
    public String processLoginForm(@ModelAttribute @Valid EmployerLoginFormDTO employerLoginFormDTO,
                                   Errors errors,
                                   HttpServletRequest request,
                                   Model model) {
//  If user attempts to log in using information outside of the valid form requirements, they are redirected to the login page.
//  For example if they enter a username that is too short
        if (errors.hasErrors()) {
            model.addAttribute("title", "Log In");
            return "login";
        }

        Employer theEmployer = employerRepository.findByUsername(employerLoginFormDTO.getUsername());
//If the Employer is not found error message is presented
        if (theEmployer == null) {
            errors.rejectValue("username", "user.invalid", "The given username does not exist");
            model.addAttribute("title", "Log In");
            return "login";
        }

        String password = employerLoginFormDTO.getPassword();
//If password is not correct error message is presented
        if (!theEmployer.isMatchingPassword(password)) {
            errors.rejectValue("password", "password.invalid", "Invalid password");
            model.addAttribute("title", "Log In");
            return "login";
        }
//If no errors are present Employer is set in session
        setEmployerInSession(request.getSession(), theEmployer);

        return "redirect:";
    }

//    Handles logout request
    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/login";
    }


}
