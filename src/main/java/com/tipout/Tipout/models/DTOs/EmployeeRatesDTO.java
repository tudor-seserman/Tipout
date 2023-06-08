package com.tipout.Tipout.models.DTOs;

import com.tipout.Tipout.models.Employee;
import com.tipout.Tipout.models.Employees.BOH;
import com.tipout.Tipout.models.Employees.Bartender;
import com.tipout.Tipout.models.Employees.Busser;
import com.tipout.Tipout.models.Employees.Server;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRatesDTO {
    private Bartender bartender;
    private BOH boh;
    private Server server;
    private Busser busser;
    private List<Employee> employees = new ArrayList<>();

    public EmployeeRatesDTO() {
    }

    public EmployeeRatesDTO(List<Employee> employees) {
        for(Employee employee: employees) {
            if(employee instanceof Bartender) {
                this.bartender = (Bartender) employee;
                this.employees.add(employee);
            } else if (employee instanceof BOH) {
                this.boh = (BOH) employee;
                this.employees.add(employee);
            } else if (employee instanceof Server) {
                this.server = (Server)employee;
                this.employees.add(employee);
            } else if (employee instanceof Busser) {
                this.busser = (Busser) employee;
                this.employees.add(employee);
            }else{
//            throw some error message
            }
        }
    }



    public Bartender getBartender() {
        return bartender;
    }

    public void setBartender(Bartender bartender) {
        this.bartender = bartender;
    }

    public BOH getBOH() {
        return boh;
    }

    public void setBOH(BOH boh) {
        this.boh = boh;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public Busser getBusser() {
        return busser;
    }

    public void setBusser(Busser busser) {
        this.busser = busser;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public BigDecimal getTipoutInput(Employee employee){
        if(employee instanceof Bartender) {
            return this.bartender.getPercentOfTipout();
        } else if (employee instanceof BOH) {
            return this.boh.getPercentOfTipout();
        } else if (employee instanceof Server) {
            return this.server.getPercentOfTipout();
        } else if (employee instanceof Busser) {
            return this.busser.getPercentOfTipout();
        }else{
//            throw some error message
            return null;
        }
    }
}
