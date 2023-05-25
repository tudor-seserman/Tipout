package com.tipout.Tipout.models.Employees;

import javax.persistence.Entity;

@Entity
public class Server extends MoneyHandler {


    private Integer percentOfTipOut = 85;
    public Server() {}

    public Server(String firstName, String lastName) {
        super(firstName, lastName);
    }
}
