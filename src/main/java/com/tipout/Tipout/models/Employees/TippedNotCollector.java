package com.tipout.Tipout.models.Employees;

import com.tipout.Tipout.models.AbstractEntity;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class TippedNotCollector extends AbstractEntity{
    private Integer percentOfTipOut;

    public TippedNotCollector() {super();
    }

    public Integer getPercentOfTipOut() {
        return percentOfTipOut;
    }

    public void setPercentOfTipOut(Integer percentOfTipOut) {
        this.percentOfTipOut = percentOfTipOut;
    }
}
