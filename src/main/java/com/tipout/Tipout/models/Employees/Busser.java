package com.tipout.Tipout.models.Employees;

import com.tipout.Tipout.models.Employee;

import javax.persistence.Entity;

@Entity
public class Busser extends Employee {

    Integer percentOfTipOut = 3;
    public Busser() {
    }

    public Busser(int id, String firstName, String lastName) {
        super(id, firstName, lastName);
    }
}
