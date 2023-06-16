package com.tipout.Tipout;

import com.tipout.Tipout.controllers.AuthenticationController;
import com.tipout.Tipout.models.Employer;
import com.tipout.Tipout.models.data.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/*
Responsible for displaying only parts of the page to those users that are not in session
 */
public class AuthenticationFilter implements HandlerInterceptor {

    @Autowired
    EmployerRepository employerRepository;

    @Autowired
    AuthenticationController authenticationController;

//  List of pages that anyone can view
    private static final List<String> whitelist = Arrays.asList("/login", "/register", "/logout", "/styles.css", "/");

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws IOException {
//   If is open to anyone, user can see page
        if (isWhitelisted(request.getRequestURI())) {return true;}

//   Check to see if user is an Employer in session, if they are they can see restricted pages
        HttpSession session = request.getSession();
        Employer employer = authenticationController.getEmployerFromSession(session);
        if (employer != null) {
            return true;
        }
//  If an Employer is not in session they are redirected to the login page.
        response.sendRedirect("/login");
        return false;
    }
//  Method to see if a page is on the whitelist List.
    private static boolean isWhitelisted(String path) {
        for (String pathRoot : whitelist) {
            if (path.equals(pathRoot)) {
                return true;
            }
        }
        return false;
    }



}
