package com.tipout.Tipout.models;

import com.tipout.Tipout.models.Employees.CurrentEmployees;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employee extends AbstractEntity {
    @NotNull
    @NotBlank
    private String firstName;
    @NotNull
    @NotBlank
    private String lastName;
    @ManyToOne
    private Employer employer;

    @OneToMany(mappedBy = "role")
    private List<CurrentEmployees> role = new ArrayList<>();

    public Employee() {
    }

    public Employee(String firstName, String lastName, List<CurrentEmployees> role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public List<CurrentEmployees> getRole() {
        return role;
    }

    public void setRole(List<CurrentEmployees> role) {
        this.role = role;
    }

}
