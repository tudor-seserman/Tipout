package com.tipout.Tipout.models.DTOs;

import com.tipout.Tipout.models.Employee;
import com.tipout.Tipout.models.Employees.BOH;
import com.tipout.Tipout.models.Employees.Bartender;
import com.tipout.Tipout.models.Employees.Busser;
import com.tipout.Tipout.models.Employees.Server;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class EmployeesDTO {
    private Bartender bartender;
    private BOH boh;
    private Server server;
    private Busser busser;
    private List<Employee> employees = new ArrayList<>();

    public EmployeesDTO() {
    }

    public EmployeesDTO(List<Employee> employees) {
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

    public BOH getBoh() {
        return boh;
    }

    public void setBoh(BOH boh) {
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
}
