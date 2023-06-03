package com.tipout.Tipout.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

/*
This class will be used to create a base employee,
different roles will inherit from this class.
 */
@Entity
//This creates different tables for the child classes, not sure if it is being helpful
@Inheritance(strategy = InheritanceType.JOINED)
public class Employee extends AbstractEntity {
    @NotNull
    @NotBlank
    private String firstName;
    @NotNull
    @NotBlank
    private String lastName;
    @ManyToOne
    private Employer employer;
    @Min(value = 0, message = "Cannot have a number lower than 0")
    @Max(value = 100, message = "Cannot have a number higher than 100")
    @Digits(integer = 3, fraction = 0)
    private BigDecimal percentOfTipOut;

    private String roleDetail = this.getClass().getSimpleName();
    private static BigDecimal rolePercentOfTipout;

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

    public static BigDecimal getRolePercentOfTipout() {
        return rolePercentOfTipout;
    }

    public static void setRolePercentOfTipout(BigDecimal rolePercentOfTipout) {
        Employee.rolePercentOfTipout = rolePercentOfTipout;
    }

    @Override
    public String toString() {
        return firstName + ' ' +lastName;
    }
}
