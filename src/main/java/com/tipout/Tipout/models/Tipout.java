package com.tipout.Tipout.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Tipout {
    @Id
    @GeneratedValue
    private int id;
    @NotNull
    @NotBlank
    @Digits(integer = 6, fraction = 2)
    private double moneyToTipout;
    @OneToMany(mappedBy = "id")
    private final List<Employee> peopleToTipout = new ArrayList<>();

    public Tipout(int id, double moneyToTipout) {
        this.id = id;
        this.moneyToTipout = moneyToTipout;
    }

    public Tipout() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMoneyToTipout() {
        return moneyToTipout;
    }

    public void setMoneyToTipout(double moneyToTipout) {
        this.moneyToTipout = moneyToTipout;
    }

    public List<Employee> getPeopleToTipout() {
        return peopleToTipout;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tipout tipout = (Tipout) o;
        return id == tipout.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
