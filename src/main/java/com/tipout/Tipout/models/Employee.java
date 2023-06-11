package com.tipout.Tipout.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.math.BigInteger;

/*
This class will be used to create a base employee,
different roles will inherit from this class.
 */
@Entity
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
    private BigInteger percentOfTipOut;
//Quick way to get role name
    private String roleDetail = this.getClass().getSimpleName();
//Field is used to filter out archived employees from active Employees
    private boolean deleted = Boolean.FALSE;




    public Employee() {
    }


    public Employee(String firstName, String lastName, Employer employer) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.employer = employer;
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


    public String getRoleDetail() {
        return roleDetail;
    }

    public void setRoleDetail(String roleDetail) {
        this.roleDetail = roleDetail;
    }

    public BigInteger getPercentOfTipout() {
        return percentOfTipOut;
    }

    public void setPercentOfTipout(BigInteger percentOfTipOut) {
        this.percentOfTipOut = percentOfTipOut;
    }


    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return firstName + ' ' +lastName;
    }
}
