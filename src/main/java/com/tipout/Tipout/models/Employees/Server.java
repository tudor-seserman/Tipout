package com.tipout.Tipout.models.Employees;

import com.tipout.Tipout.models.Employer;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Server extends MoneyHandler {
    private static BigDecimal rolePercentOfTipOut=BigDecimal.valueOf(85);
    public Server() {this.setPercentOfTipout(rolePercentOfTipOut);}

    public Server(String firstName, String lastName, Employer employer) {
        super(firstName, lastName, employer);
        this.setPercentOfTipout(rolePercentOfTipOut);
    }
}
