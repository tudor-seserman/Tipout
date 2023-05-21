package com.tipout.Tipout.models.Employees;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Bartender")
public class Bartender extends TipCollector  {

    @ManyToOne
    private EmployeeTypes<Bartender> role;
    private Integer percentOfTipOut = 10;

    public Bartender() {}


}
