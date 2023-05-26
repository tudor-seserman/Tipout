package com.tipout.Tipout.models.Employees;

import javax.persistence.Entity;

@Entity
public class Busser extends NonMoneyHandler {


    Integer percentOfTipOut = 3;

    public Busser() {
    }

    public Busser(String firstName, String lastName) {
        super(firstName, lastName);
    }
}
