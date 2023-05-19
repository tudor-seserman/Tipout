package com.tipout.Tipout.models.Employees;

import com.tipout.Tipout.models.Employee;

import javax.persistence.Entity;

@Entity
public class BOH extends Employee {

    Integer percentOfTipOut = 2;
    public BOH() {
    }

    public BOH(String firstName, String lastName) {
        super(firstName, lastName);
    }
}
