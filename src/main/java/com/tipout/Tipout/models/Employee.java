package com.tipout.Tipout.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
/*
This class will be used to create a base employee,
different roles will inherit from this class.
 */
@Entity
//This creates different tables for the child classes, not sure if it is being helpful
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Employee extends AbstractEntity {
    @NotNull
    @NotBlank
    private String firstName;
    @NotNull
    @NotBlank
    private String lastName;
    @ManyToOne
    private Employer employer;

    private BigDecimal percentOfTipOut;

    private String roleDetail = this.getClass().getSimpleName();

    private BigDecimal moneyToBeTippedOut;


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

    public BigDecimal getMoneyToBeTippedOut() {
        return moneyToBeTippedOut;
    }

    public void setMoneyToBeTippedOut(BigDecimal moneyToBeTippedOut) {
        this.moneyToBeTippedOut = moneyToBeTippedOut;
    }

    public String getRoleDetail() {
        return roleDetail;
    }

    public void setRoleDetail(String roleDetail) {
        this.roleDetail = roleDetail;
    }

    public BigDecimal getPercentOfTipOut() {
        return percentOfTipOut;
    }

    public void setPercentOfTipOut(BigDecimal percentOfTipOut) {
        this.percentOfTipOut = percentOfTipOut;
    }

    @Override
    public String toString() {
        return firstName + ' ' +lastName;
    }
}
