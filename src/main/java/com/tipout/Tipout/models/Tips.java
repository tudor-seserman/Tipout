package com.tipout.Tipout.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
/*
Class for handling collected tips
 */
@Entity
public class Tips extends AbstractEntity{
    private BigDecimal tips=null;

    public Tips() {
    }

    public Tips(BigDecimal tips) {
        this.tips = tips;
    }

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
