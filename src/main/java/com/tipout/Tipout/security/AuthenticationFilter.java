package com.tipout.Tipout.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tipout.Tipout.controllers.AuthenticationController;
import com.tipout.Tipout.models.Employer;
import com.tipout.Tipout.models.data.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.tipout.Tipout.security.SecurityConstraints.EXPIRATION_TIME;

/*
Responsible for displaying only parts of the page to those users that are not in session
 */
//public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter implements HandlerInterceptor {

//    @Autowired
//    EmployerRepository employerRepository;
//
//    @Autowired
//    AuthenticationController authenticationController;
//
//    private AuthenticationManager authenticationManager;
//
//    public AuthenticationFilter(AuthenticationManager authenticationManager) {
//        this.authenticationManager = authenticationManager;
//
//        setFilterProcessesUrl("/login");
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest req,
//                                                HttpServletResponse res) throws AuthenticationException {
//        try {
//            Employer creds = new ObjectMapper()
//                    .readValue(req.getInputStream(), Employer.class);
//
//            return authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            creds.getUsername(),
//                            creds.getPassword(),
//                            new ArrayList<>())
//            );
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest req,
//                                            HttpServletResponse res,
//                                            FilterChain chain,
//                                            Authentication auth) throws IOException {
//        String token = JWT.create()
//                .withSubject(((Employer) auth.getPrincipal()).getUsername())
//                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//                .sign(Algorithm.HMAC512(SecurityConstraints.SECRET.getBytes()));
//
//        String body = ((Employer) auth.getPrincipal()).getUsername() + " " + token;
//
//        res.getWriter().write(body);
//        res.getWriter().flush();
//    }
//
//
///*
//*
//*
//*   Server side Authentication and Filters
//*
//*
// */
//
////  List of pages that anyone can view
//    private static final List<String> whitelist = Arrays.asList("/login", "/register", "/logout", "/styles.css", "/", "/api/employees");
//
//    public boolean preHandle(HttpServletRequest request,
//                             HttpServletResponse response,
//                             Object handler) throws IOException {
////   If is open to anyone, user can see page
//        if (isWhitelisted(request.getRequestURI())) {return true;}
//
////   Check to see if user is an Employer in session, if they are they can see restricted pages
//        HttpSession session = request.getSession();
//        Employer employer = authenticationController.getEmployerFromSession(session);
//        if (employer != null) {
//            return true;
//        }
////  If an Employer is not in session they are redirected to the login page.
//        response.sendRedirect("/login");
//        return false;
//    }
////  Method to see if a page is on the whitelist List.
//    private static boolean isWhitelisted(String path) {
//        for (String pathRoot : whitelist) {
//            if (path.startsWith(pathRoot)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//
//
//}
