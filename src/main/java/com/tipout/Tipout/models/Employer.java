package com.tipout.Tipout.models;

import com.tipout.Tipout.models.Employees.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class Employer extends AbstractEntity{
    @NotNull
    private String username;
    @NotNull
    private String businessName;
    @NotNull
    private String pwHash;

//    Need to be more explicit here
    @OneToMany(mappedBy = "employer")
    private List<Employee> employees= new ArrayList<>(Arrays.asList(new Bartender(), new BOH(), new Busser(), new Server()));

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public Employer() {
    }

    public Employer(String username, String businessName, String pwHash) {
        this.username = username;
        this.businessName = businessName;
        this.pwHash = encoder.encode(pwHash);
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }

}
