package com.tipout.Tipout.models.Employees;

import com.tipout.Tipout.models.Employee;
import com.tipout.Tipout.models.Tips;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class NonMoneyHandler extends Employee {
    private Integer percentOfTipOut;

    @OneToOne
    private Tips tips;

    public NonMoneyHandler() {
    }

    public NonMoneyHandler(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public Integer getPercentOfTipOut() {
        return percentOfTipOut;
    }

    public void setPercentOfTipOut(Integer percentOfTipOut) {
        this.percentOfTipOut = percentOfTipOut;
    }

    public Tips getTips() {
        return tips;
    }

    public void setTips(Tips tips) {
        this.tips = tips;
    }
}
