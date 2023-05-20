package com.tipout.Tipout.models.Employees;

import com.tipout.Tipout.models.Employee;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Busser")
public class Busser extends TippedNotCollector {


    Integer percentOfTipOut = 3;

    public Busser() {
    }

    public Busser(CurrentEmployees role){
        super(role);
    }

}
