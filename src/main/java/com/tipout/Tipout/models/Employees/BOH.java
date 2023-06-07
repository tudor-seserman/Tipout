package com.tipout.Tipout.models.Employees;

import com.tipout.Tipout.models.Employer;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class BOH extends NonMoneyHandler {
    private static BigDecimal rolePercentOfTipOut=BigDecimal.valueOf(2);
    public BOH() {this.setPercentOfTipout(rolePercentOfTipOut);}

    public BOH(String firstName, String lastName, Employer employer) {
        super(firstName, lastName, employer);
        this.setPercentOfTipout(rolePercentOfTipOut);
    }

}
