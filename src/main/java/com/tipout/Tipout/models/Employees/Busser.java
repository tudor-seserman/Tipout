package com.tipout.Tipout.models.Employees;

import com.tipout.Tipout.models.Employee;

import javax.persistence.Entity;

@Entity
public class Busser extends Employee {

    Integer percentOfTipOut = 3;
    public Busser() {
    }

    public Busser(String firstName, String lastName) {
        super(firstName, lastName);
    }
}
