package com.tipout.Tipout.models.DTOs;

import com.tipout.Tipout.models.Employee;
import com.tipout.Tipout.models.Employees.BOH;
import com.tipout.Tipout.models.Employees.Bartender;
import com.tipout.Tipout.models.Employees.Busser;
import com.tipout.Tipout.models.Employees.Server;
import com.tipout.Tipout.models.Employer;
import com.tipout.Tipout.models.data.BOHRepository;
import com.tipout.Tipout.models.data.BartenderRepository;
import com.tipout.Tipout.models.data.BusserRepository;
import com.tipout.Tipout.models.data.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/*
Way to use a list of Employee Types an Employer chooses to use.
 */
@Service
public class CreateEmployeeDTO {
    @Autowired
    BartenderRepository bartenderRepository;
    @Autowired
    BOHRepository bohRepository;
    @Autowired
    BusserRepository busserRepository;
    @Autowired
    ServerRepository serverRepository;
    @NotNull
    @NotBlank
    private String firstName;
    @NotNull
    @NotBlank
    private String lastName;
    @NotNull
    @NotBlank
    private String employeeRole;

    public CreateEmployeeDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(String employeeRole) {
        this.employeeRole = employeeRole;
    }

    public void createEmployee(Employer employer){
        switch (this.employeeRole) {
            case "Bartender":
                Bartender newBartender = new Bartender(this.firstName, this.lastName, employer);
                bartenderRepository.save(newBartender);
                break;
            case "BOH":
                BOH newBOH = new BOH(this.firstName, this.lastName, employer);
                bohRepository.save(newBOH);
                break;
            case "Busser":
                Busser newBusser = new Busser(this.firstName, this.lastName, employer);
                busserRepository.save(newBusser);
                break;
            case "Server":
                Server newServer = new Server(this.firstName, this.lastName, employer);
                serverRepository.save(newServer);
                break;
            default:
                throw new RuntimeException();
        }
    }

}
