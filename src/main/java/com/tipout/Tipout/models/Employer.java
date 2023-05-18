package com.tipout.Tipout.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Employer {
    private Integer id;
    private String name;
    private List<Employee> employees= new ArrayList<>();

    public Employer() {
    }

    public Employer(Integer id, String name, List<Employee> employees) {
        this.id = id;
        this.name = name;
        this.employees = employees;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
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
