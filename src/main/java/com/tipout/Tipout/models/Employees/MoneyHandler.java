package com.tipout.Tipout.models.Employees;

import com.tipout.Tipout.models.Employee;
import com.tipout.Tipout.models.Employer;
import com.tipout.Tipout.models.Tips;
import com.tipout.Tipout.models.TipsCollected;

import javax.persistence.*;

@Entity
public class MoneyHandler extends Employee {
//    @ManyToOne
//    private TipsCollected tipsCollected;
    @OneToOne
    private Tips tips;

    public MoneyHandler() {
    }

    public MoneyHandler(String firstName, String lastName, Employer employer) {
        super(firstName, lastName, employer);
    }

//    public TipsCollected getTipsCollected() {
//        return tipsCollected;
//    }
//
//    public void setTipsCollected(TipsCollected tipsCollected) {
//        this.tipsCollected = tipsCollected;
//    }




    public Tips getTips() {
        return tips;
    }

    public void setTips(Tips tips) {
        this.tips = tips;
    }
}
