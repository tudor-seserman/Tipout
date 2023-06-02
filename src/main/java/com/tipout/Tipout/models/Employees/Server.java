package com.tipout.Tipout.models.Employees;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Server extends MoneyHandler {
    private static BigDecimal rolePercentOfTipOut=BigDecimal.valueOf(85);
    public Server() {this.setPercentOfTipOut(rolePercentOfTipOut);}

    public Server(String firstName, String lastName) {
        super(firstName, lastName);
        this.setPercentOfTipOut(rolePercentOfTipOut);
    }
}
