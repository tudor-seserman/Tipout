package com.tipout.Tipout.models.Employees;

import javax.persistence.Entity;

@Entity
public class Bartender extends TipCollector{
    public Bartender(double tipsCollected) {
        super(tipsCollected);
    }

    public Bartender(int id, String name, double percentOfTipOut, double tipsCollected) {
        super(id, name, percentOfTipOut, tipsCollected);
    }
}
