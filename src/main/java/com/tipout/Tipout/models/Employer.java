package com.tipout.Tipout.models;

import com.tipout.Tipout.models.Employees.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
@Entity
public class Employer {
    @Id
    @GeneratedValue
    private Integer id;
    private String firstName;
    private String lastName;

//    @OneToMany(mappedBy = "employer")
    private List<AbstractEntity> employees= new ArrayList<>();

    public Employer() {
    }

    public Employer(Integer id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public List<AbstractEntity> getEmployees() {
        return employees;
    }

    public void setEmployees(List<AbstractEntity> employees) {
        this.employees = employees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employer employer = (Employer) o;
        return id.equals(employer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
