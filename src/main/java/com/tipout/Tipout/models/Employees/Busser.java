package com.tipout.Tipout.models.Employees;

import com.tipout.Tipout.models.Employer;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Busser extends NonMoneyHandler {
    private static BigDecimal rolePercentOfTipOut=BigDecimal.valueOf(3);
    public Busser() {this.setPercentOfTipout(rolePercentOfTipOut);}

    public Busser(String firstName, String lastName, Employer employer) {
        super(firstName, lastName, employer);
        this.setPercentOfTipout(rolePercentOfTipOut);
    }
}
