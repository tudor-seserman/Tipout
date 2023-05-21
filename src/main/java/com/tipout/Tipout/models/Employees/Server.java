package com.tipout.Tipout.models.Employees;

import com.tipout.Tipout.models.Employee;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Server")
public class Server extends TipCollector {


    private Integer percentOfTipOut = 85;
    public Server() {}

    public Server(String firstName, String lastName) {
        super(firstName, lastName);
    }
}
