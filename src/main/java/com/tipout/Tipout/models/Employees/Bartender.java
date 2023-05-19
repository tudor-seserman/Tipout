package com.tipout.Tipout.models.Employees;

import com.tipout.Tipout.models.Employee;

import javax.persistence.Entity;

@Entity
public class Bartender extends TipCollector{
    private Integer percentOfTipOut = 10;

    public Bartender() {
    };

    public Bartender(int id, String firstName, String lastName) {
        super(id, firstName, lastName);
    }
}
