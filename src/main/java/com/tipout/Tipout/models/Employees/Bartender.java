package com.tipout.Tipout.models.Employees;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Bartender extends MoneyHandler {
    private static BigDecimal rolePercentOfTipOut=BigDecimal.valueOf(10);
    public Bartender() {this.setPercentOfTipout(rolePercentOfTipOut);}

    public Bartender(String firstName, String lastName) {
        super(firstName, lastName);
        this.setPercentOfTipout(rolePercentOfTipOut);
    }
}
