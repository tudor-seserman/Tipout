package com.tipout.Tipout.models.Employees;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
public class Bartender extends TipCollector {


    private Integer percentOfTipOut = 10;

    public Bartender() {}

    public Bartender(String firstName, String lastName) {
        super(firstName, lastName);
    }
}
