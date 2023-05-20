package com.tipout.Tipout.models.Employees;

import com.tipout.Tipout.models.Employee;

import javax.persistence.*;

@Entity
@Table(name = "BOH")
public class BOH extends TippedNotCollector {
    @ManyToOne
    private BOH role = this;
    Integer percentOfTipOut = 2;

    public BOH() {super();}

}
