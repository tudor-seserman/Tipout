package com.tipout.Tipout.models.Employees;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Server extends MoneyHandler {


    private BigDecimal percentOfTipOut = BigDecimal.valueOf(85);
    public Server() {}

    public Server(String firstName, String lastName) {
        super(firstName, lastName);
    }
}
