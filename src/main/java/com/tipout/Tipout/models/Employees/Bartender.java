package com.tipout.Tipout.models.Employees;

import com.tipout.Tipout.models.Employer;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Bartender extends MoneyHandler {
    public Bartender() {}

    public Bartender(String firstName, String lastName, Employer employer) {
        super(firstName, lastName, employer);
        this.setPercentOfTipout(employer.getTipRates().getBartenderRate());
    }

}
