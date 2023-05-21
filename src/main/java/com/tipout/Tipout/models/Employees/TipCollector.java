package com.tipout.Tipout.models.Employees;

import com.tipout.Tipout.models.Employee;
import com.tipout.Tipout.models.TipsCollected;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Entity
public abstract class TipCollector extends Employee {
    @Valid
    @OneToMany(mappedBy = "totalTipsCollected")
    private final List<TipsCollected> tipsCollected = new ArrayList<>();
    private Integer percentOfTipOut;

    public TipCollector() {
    }

    public TipCollector(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public List<TipsCollected> getTipsCollected() {
        return tipsCollected;
    }

    public Integer getPercentOfTipOut() {
        return percentOfTipOut;
    }

    public void setPercentOfTipOut(Integer percentOfTipOut) {
        this.percentOfTipOut = percentOfTipOut;
    }
}
