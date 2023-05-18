package com.tipout.Tipout.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class TipsCollected {
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @NotBlank
    @Digits(integer = 6, fraction = 2)
    private double totalTipsCollected;

    public TipsCollected() {

    }

    public TipsCollected(int id, double totalTipsCollected) {
        this.id = id;
        this.totalTipsCollected = totalTipsCollected;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotalTipsCollected() {
        return totalTipsCollected;
    }

    public void setTotalTipsCollected(double totalTipsCollected) {
        this.totalTipsCollected = totalTipsCollected;
    }
}
