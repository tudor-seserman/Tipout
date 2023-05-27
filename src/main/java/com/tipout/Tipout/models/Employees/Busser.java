package com.tipout.Tipout.models.Employees;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Busser extends NonMoneyHandler {

    public Busser() {
    }

    public Busser(String firstName, String lastName) {
        super(firstName, lastName);
        this.setPercentOfTipOut(BigDecimal.valueOf(3));
    }
}
