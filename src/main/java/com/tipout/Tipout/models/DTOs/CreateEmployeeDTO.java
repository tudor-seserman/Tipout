package com.tipout.Tipout.models.DTOs;

import com.tipout.Tipout.models.Employee;
import com.tipout.Tipout.models.Employees.BOH;
import com.tipout.Tipout.models.Employees.Bartender;
import com.tipout.Tipout.models.Employees.Busser;
import com.tipout.Tipout.models.Employees.Server;

import java.util.ArrayList;
import java.util.List;

/*
Way to use a list of Employee Types an Employer chooses to use.
 */
public class CreateEmployeeDTO {
    private Bartender bartender;
    private BOH boh;
    private Server server;
    private Busser busser;
    private List<Employee> employees = new ArrayList<>();

    public CreateEmployeeDTO() {
    }

    public CreateEmployeeDTO(List<String> employeeRoles) {
        for(String role: employeeRoles)
        {switch (role) {
            case "Bartender":
                this.bartender = new Bartender();
                break;
            case "BOH":
                this.boh = new BOH();
                break;
            case "Busser":
                this.busser = new Busser();
                break;
            case "Server":
                Server newServer = new Server();
                break;
            default:
//                Error message
                break;
        }
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

}
