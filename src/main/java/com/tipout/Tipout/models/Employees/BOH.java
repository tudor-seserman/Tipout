package com.tipout.Tipout.models.Employees;

import com.tipout.Tipout.models.Employee;
import com.tipout.Tipout.models.Employer;

import javax.persistence.*;

@Entity
@Table(name = "BOH")
public class BOH extends TippedNotCollector {
    @ManyToOne
    private Employer employer;
    @ManyToOne
    private CurrentEmployees role;
    Integer percentOfTipOut = 2;

    public BOH() {}

    public BOH(CurrentEmployees role) {
        super(role);
    }


}
