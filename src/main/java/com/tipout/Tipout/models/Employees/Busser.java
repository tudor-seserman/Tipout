package com.tipout.Tipout.models.Employees;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Busser extends NonMoneyHandler {
    private static BigDecimal rolePercentOfTipOut=BigDecimal.valueOf(3);
    public Busser() {this.setPercentOfTipOut(rolePercentOfTipOut);}

    public Busser(String firstName, String lastName) {
        super(firstName, lastName);
        this.setPercentOfTipOut(rolePercentOfTipOut);
    }
}
