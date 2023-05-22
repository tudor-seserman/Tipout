package com.tipout.Tipout.models.Employees;

import com.tipout.Tipout.models.Employee;
import com.tipout.Tipout.models.Tips;
import com.tipout.Tipout.models.TipsCollected;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TipCollector extends Employee {
    @ManyToOne
    private TipsCollected tipsCollected;
    private Integer percentOfTipOut;
    @OneToOne
    private Tips tips;

    public TipCollector() {
    }

    public TipCollector(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public TipsCollected getTipsCollected() {
        return tipsCollected;
    }

    public void setTipsCollected(TipsCollected tipsCollected) {
        this.tipsCollected = tipsCollected;
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
