package com.tipout.Tipout.models.Employees;

import com.tipout.Tipout.models.Employee;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Busser")
public class Busser extends TippedNotCollector {

    @ManyToOne
    private Busser role = this;
    Integer percentOfTipOut = 3;

    public Busser() {super();
    }

}
