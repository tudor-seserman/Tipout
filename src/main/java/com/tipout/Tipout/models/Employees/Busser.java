package com.tipout.Tipout.models.Employees;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Busser extends NonMoneyHandler {


    BigDecimal percentOfTipOut = BigDecimal.valueOf(3);

    public Busser() {
    }

    public Busser(String firstName, String lastName) {
        super(firstName, lastName);
    }
}
