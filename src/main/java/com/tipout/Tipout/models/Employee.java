package com.tipout.Tipout.models;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;
@Entity
public class Employee {
    @Id
    @GeneratedValue
    private int id;
    @NotNull
    @NotBlank
    private String firstName;

    @NotNull
    @NotBlank
    private String lastName;
    @ManyToOne
    private Employer employer;
    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    private Employee role;

    private String roleDescription = this.getClass().getSimpleName();

    private Integer percentOfTipOut;

    public Employee() {
    }

    public Employee(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Integer getPercentOfTipOut() {
        return percentOfTipOut;
    }

    public void setPercentOfTipOut(Integer percentOfTipOut) {
        this.percentOfTipOut = percentOfTipOut;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public Employee getRole() {
        return role;
    }

    public void setRole(Employee role) {
        this.role = role;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
