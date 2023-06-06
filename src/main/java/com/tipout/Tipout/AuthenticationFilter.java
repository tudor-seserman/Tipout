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

public class AuthenticationFilter implements HandlerInterceptor {

    @Autowired
    EmployerRepository employerRepository;

    @Autowired
    AuthenticationController authenticationController;
    private static final List<String> whitelist = Arrays.asList("/login", "/register", "/logout", "/styles.css", "/");

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws IOException {
        if (isWhitelisted(request.getRequestURI())) {return true;}

        HttpSession session = request.getSession();
        Employer employer = authenticationController.getEmployerFromSession(session);

        if (employer != null) {
            return true;
        }

        response.sendRedirect("/login");
        return false;
    }

    private static boolean isWhitelisted(String path) {
        for (String pathRoot : whitelist) {
            if (path.equals(pathRoot)) {
                return true;
            }
        }
        return false;
    }



}
