package com.tipout.Tipout.models.Employees;

import com.tipout.Tipout.models.Employee;
import com.tipout.Tipout.models.Employer;
import com.tipout.Tipout.models.Tips;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class NonMoneyHandler extends Employee {

    @OneToOne
    private Tips tips;

    public NonMoneyHandler() {
    }

    public NonMoneyHandler(String firstName, String lastName, Employer employer) {
        super(firstName, lastName, employer);
    }

    public Tips getTips() {
        return tips;
    }

    public void setTips(Tips tips) {
        this.tips = tips;
    }
}
