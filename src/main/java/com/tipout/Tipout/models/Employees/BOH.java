package com.tipout.Tipout.models.Employees;

import com.tipout.Tipout.models.Employer;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class BOH extends NonMoneyHandler {
    @ManyToOne
    private Employer employer;
    BigDecimal percentOfTipOut = BigDecimal.valueOf(2);

    public BOH() {}

    public BOH(String firstName, String lastName) {
        super(firstName, lastName);
    }
}
