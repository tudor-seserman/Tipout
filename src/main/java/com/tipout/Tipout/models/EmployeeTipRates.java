package com.tipout.Tipout.models;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Entity
public class EmployeeTipRates {
    @OneToOne(mappedBy = "tipRates")
    Employer employer;
    private BigDecimal bartenderRate = BigDecimal.valueOf(10);
    private BigDecimal BOHRate = BigDecimal.valueOf(2);
    private BigDecimal busserRate = BigDecimal.valueOf(3);
    private BigDecimal serverRate = BigDecimal.valueOf(85);

    public EmployeeTipRates() {
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public BigDecimal getBartenderRate() {
        return bartenderRate;
    }

    public void setBartenderRate(BigDecimal bartenderRate) {
        this.bartenderRate = bartenderRate;
    }

    public BigDecimal getBOHRate() {
        return BOHRate;
    }

    public void setBOHRate(BigDecimal BOHRate) {
        this.BOHRate = BOHRate;
    }

    public BigDecimal getBusserRate() {
        return busserRate;
    }

    public void setBusserRate(BigDecimal busserRate) {
        this.busserRate = busserRate;
    }

    public BigDecimal getServerRate() {
        return serverRate;
    }

    public void setServerRate(BigDecimal serverRate) {
        this.serverRate = serverRate;
    }
}
