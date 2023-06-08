package com.tipout.Tipout.models.Employees;

import com.tipout.Tipout.models.Employer;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Server extends MoneyHandler {
    public Server() {}

    public Server(String firstName, String lastName, Employer employer) {
        super(firstName, lastName, employer);
        this.setPercentOfTipout(employer.getTipRates().getServerRate());
    }
}
