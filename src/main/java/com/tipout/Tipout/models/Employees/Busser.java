package com.tipout.Tipout.models.Employees;

import com.tipout.Tipout.models.Employee;

import javax.persistence.Entity;

@Entity
public class Busser extends Employee {
    public Busser() {
    }

    public Busser(int id, String name, double percentOfTipOut) {
        super(id, name, percentOfTipOut);
    }
}
