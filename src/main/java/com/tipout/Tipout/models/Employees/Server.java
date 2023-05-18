package com.tipout.Tipout.models.Employees;

import com.tipout.Tipout.models.Employee;

import javax.persistence.Entity;

@Entity
public class Server extends TipCollector {

    private Integer percentOfTipOut = 85;
    public Server() {
    }

    public Server(int id, String firstName, String lastName, double tipsCollected) {
        super(id, firstName, lastName, tipsCollected);
    }
}
