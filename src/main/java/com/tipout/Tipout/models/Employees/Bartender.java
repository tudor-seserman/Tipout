package com.tipout.Tipout.models.Employees;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Bartender")
public class Bartender extends TipCollector {


    private Integer percentOfTipOut = 10;

    public Bartender() {}
    public Bartender(CurrentEmployees role) {super(role);}
}
