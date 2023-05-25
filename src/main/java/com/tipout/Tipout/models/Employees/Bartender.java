package com.tipout.Tipout.models.Employees;

import javax.persistence.Entity;

@Entity
public class Bartender extends MoneyHandler {


    private Integer percentOfTipOut = 10;

    public Bartender() {}

    public Bartender(String firstName, String lastName) {
        super(firstName, lastName);
    }
}
