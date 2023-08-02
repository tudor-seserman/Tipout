package com.tipout.Tipout.controllers;

import com.tipout.Tipout.models.DTOs.AuthResponseDTO;
import com.tipout.Tipout.models.DTOs.LoginFormDTO;
import com.tipout.Tipout.models.DTOs.LoginRequest;
import com.tipout.Tipout.models.DTOs.RegistrationFormDTO;
import com.tipout.Tipout.models.Employer;
import com.tipout.Tipout.models.UserEntity;
import com.tipout.Tipout.models.data.EmployerRepository;
import com.tipout.Tipout.models.data.UserRepository;
import com.tipout.Tipout.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/*
Controls authentication for Employers currently
 */
@RestController
@RequestMapping("auth")
public class AuthenticationController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationController.class);
    UserRepository userRepository;
    EmployerRepository employerRepository;
    AuthenticationManager authenticationManager;
    TokenService tokenService;

    @Autowired
    public AuthenticationController(UserRepository userRepository, EmployerRepository employerRepository,
            AuthenticationManager authenticationManager, TokenService tokenService) {
        this.userRepository = userRepository;
        this.employerRepository = employerRepository;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }



    // Name to be used as a key tied to employee id a session
    private static final String userSessionKey = "employer";

    // Method to extract Employer information from session information
    // public Employer getEmployerFromSession(HttpSession session) {
    // Long employerId = (Long) session.getAttribute(userSessionKey);
    // if (employerId == null) {
    // return null;
    // }
    //
    // Optional<Employer> employer = userRepository.findById(employerId);
    //
    // if (employer.isEmpty()) {
    // return null;
    // }
    //
    // return employer.get();
    // }

    // Method to create Employer sessions
    // private static void setEmployerInSession(HttpSession session,
    // Employer employer) {
    // session.setAttribute(userSessionKey, employer.getId());
    // }

    // Method to see if a user is in session. Used to conditionally display content.
    // public boolean inSession(HttpSession session){
    // Long employerId = (Long) session.getAttribute(userSessionKey);
    // if (employerId == null) {
    // return false;
    // }
    //
    // Optional<Employer> employer = userRepository.findById(employerId);
    //
    // if (employer.isEmpty()) {
    // return false;
    // }
    //
    // return true;
    // }
    @GetMapping("test")
    public HttpStatus test(){return HttpStatus.OK;}
    @PostMapping("test")
    public String token(@RequestBody LoginRequest loginFormDTO){
        System.out.println(loginFormDTO.toString());
        LOG.debug("Stuff");
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginFormDTO.username(), loginFormDTO.password()));
        return tokenService.generateToken(authentication);
    }

//
//    @RequestMapping(value = "register", method = POST, produces = "application/json")
//    public HttpStatus processRegistrationForm(@RequestBody @Valid RegistrationFormDTO employerRegistrationFormDTO,
//            Errors errors) {
//
//        if (errors.hasErrors()) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not create account");
//        }
//
//        Optional<UserEntity> existingUser = userRepository.findByUsername(employerRegistrationFormDTO.getUsername());
//
//        if (existingUser.isPresent()) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
//                    "A user with that username already exists, please select another.");
//        }
//
//        String password = employerRegistrationFormDTO.getPassword();
//        String verifyPassword = employerRegistrationFormDTO.getVerifyPassword();
//
//        // Makes sure that the passwords match up.
//        if (!password.equals(verifyPassword)) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Passwords do not match");
//        }
//
//        Employer newUser = new Employer(employerRegistrationFormDTO.getBusinessName(),
//                employerRegistrationFormDTO.getUsername(), employerRegistrationFormDTO.getPassword());
//        userRepository.save(newUser);
//
//        return HttpStatus.OK;
//    }

    @PostMapping("login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginFormDTO loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenService.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
    }

    // Displays login page
    // @GetMapping("/login")
    // public String displayLoginForm(Model model) {
    // model.addAttribute(new LoginFormDTO());
    // model.addAttribute("title", "Log In");
    // return "login";
    // }

    // Processes login form
    // @PostMapping("/login")
    // public String processLoginForm(@ModelAttribute @Valid LoginFormDTO
    // employerLoginFormDTO,
    // Errors errors,
    // HttpServletRequest request,
    // Model model) {
    //// If user attempts to log in using information outside of the valid form
    // requirements, they are redirected to the login page.
    //// For example if they enter a username that is too short
    // if (errors.hasErrors()) {
    // model.addAttribute("title", "Log In");
    // return "login";
    // }
    //
    // Optional<Employer> optTheEmployer =
    // userRepository.findByUsername(employerLoginFormDTO.getUsername());
    //// If the Employer is not found error message is presented
    // if (optTheEmployer.isEmpty()) {
    // errors.rejectValue("username", "user.invalid", "The given username does not
    // exist");
    // model.addAttribute("title", "Log In");
    // return "login";
    // }
    // Employer theEmployer = optTheEmployer.get();
    // String password = employerLoginFormDTO.getPassword();
    //// If password is not correct error message is presented
    //// if (!theEmployer.isMatchingPassword(password)) {
    //// errors.rejectValue("password", "password.invalid", "Invalid password");
    //// model.addAttribute("title", "Log In");
    //// return "login";
    //// }
    //// If no errors are present Employer is set in session
    // setEmployerInSession(request.getSession(), theEmployer);
    //
    // return "redirect:";
    // }
    //
    //// Handles logout request
    // @GetMapping("/logout")
    // public String logout(HttpServletRequest request){
    // request.getSession().invalidate();
    // return "redirect:/login";
    // }
    //
    //
}
