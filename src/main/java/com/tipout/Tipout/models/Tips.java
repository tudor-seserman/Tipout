package com.tipout.Tipout.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Tips{
    private Double tips;

    public Tips() {
    }

    public Tips(Double tips) {
        this.tips = tips;
    }
    @Id
    public Double getTips() {
        return tips;
    }

    public void setTips(Double tips) {
        this.tips = tips;
    }

    @Override
    public String toString() {
        return tips.toString();
    }
}
