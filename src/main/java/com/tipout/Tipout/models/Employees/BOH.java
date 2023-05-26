package com.tipout.Tipout.models.Employees;

import com.tipout.Tipout.models.Employer;

import javax.persistence.*;

@Entity
public class BOH extends NonMoneyHandler {
    @ManyToOne
    private Employer employer;
    Integer percentOfTipOut = 2;

    public BOH() {}

    public BOH(String firstName, String lastName) {
        super(firstName, lastName);
    }
}
