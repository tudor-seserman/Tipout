package com.tipout.Tipout.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Tips{
    private BigDecimal tips=new BigDecimal(0);

    public Tips() {
    }

    public Tips(BigDecimal tips) {
        this.tips = tips;
    }
    @Id
    public BigDecimal getTips() {
        return tips;
    }

    public void setTips(BigDecimal tips) {
        this.tips = tips;
    }

    @Override
    public String toString() {
        return (tips == null)? "Not in tippool" :tips.toString();
    }
}
