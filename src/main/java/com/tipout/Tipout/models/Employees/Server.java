package com.tipout.Tipout.models.Employees;

import com.tipout.Tipout.models.Employee;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Server")
public class Server extends TipCollector {

    @ManyToOne
    private Server role = this;
    private Integer percentOfTipOut = 85;
    public Server() {super();}

}
