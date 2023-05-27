package com.tipout.Tipout.models.Employees;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Bartender extends MoneyHandler {

    public Bartender() {}

    public Bartender(String firstName, String lastName) {
        super(firstName, lastName);
        this.setPercentOfTipOut(BigDecimal.valueOf(10));
    }
}
