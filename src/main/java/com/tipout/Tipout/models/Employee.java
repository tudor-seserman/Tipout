package com.tipout.Tipout.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;
@MappedSuperclass
public abstract class Employee {
    @Id
    @GeneratedValue
    private int id;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    @Digits(integer = 3, fraction = 0)
    private double percentOfTipOut;

    public Employee() {
    }

    public Employee(int id, String name, double percentOfTipOut) {
        this.id = id;
        this.name = name;
        this.percentOfTipOut = percentOfTipOut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPercentOfTipOut() {
        return percentOfTipOut;
    }

    public void setPercentOfTipOut(double percentOfTipOut) {
        this.percentOfTipOut = percentOfTipOut;
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
