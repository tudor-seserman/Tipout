package com.tipout.Tipout.models.Employees;

import com.tipout.Tipout.models.Employee;

import javax.persistence.Entity;

@Entity
public class BOH extends Employee {
    public BOH(int id, String name, double percentOfTipOut) {
        super(id, name, percentOfTipOut);
    }
}
