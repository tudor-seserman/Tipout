package com.tipout.Tipout.models;

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

    private String roleDetail = this.getClass().getSimpleName();

    private double moneyToBeTippedOut;


    public Employee() {
    }

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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

    public double getMoneyToBeTippedOut() {
        return moneyToBeTippedOut;
    }

    public void setMoneyToBeTippedOut(double moneyToBeTippedOut) {
        this.moneyToBeTippedOut = moneyToBeTippedOut;
    }

    public String getRoleDetail() {
        return roleDetail;
    }

    public void setRoleDetail(String roleDetail) {
        this.roleDetail = roleDetail;
    }

    @Override
    public String toString() {
        return firstName + ' ' +lastName;
    }
}
