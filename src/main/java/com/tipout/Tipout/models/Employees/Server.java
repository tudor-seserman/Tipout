package com.tipout.Tipout.models.Employees;

import com.tipout.Tipout.models.Employee;

import javax.persistence.Entity;

@Entity
public class Server extends TipCollector {
    public Server(double tipsCollected) {
        super(tipsCollected);
    }
}
