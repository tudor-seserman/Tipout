package com.tipout.Tipout.models.Employees;

import javax.persistence.Entity;

@Entity
public class Busser extends NotMoneyHandler {


    Integer percentOfTipOut = 3;

    public Busser() {
    }

    public Busser(String firstName, String lastName) {
        super(firstName, lastName);
    }
}
