package com.tipout.Tipout.models;

import com.tipout.Tipout.models.Employees.BOH;
import com.tipout.Tipout.models.Employees.Bartender;
import com.tipout.Tipout.models.Employees.Busser;
import com.tipout.Tipout.models.Employees.Server;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.math.BigInteger;

@Entity
public class EmployeeTipRates extends AbstractEntity{
    @OneToOne(mappedBy = "tipRates")
    private Employer employer;
    private BigInteger bartenderRate = BigInteger.valueOf(10);
    private BigInteger BOHRate = BigInteger.valueOf(2);
    private BigInteger busserRate = BigInteger.valueOf(3);
    private BigInteger serverRate = BigInteger.valueOf(85);

    public EmployeeTipRates() {
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public BigInteger getBartenderRate() {
        return bartenderRate;
    }

    public void setBartenderRate(BigInteger bartenderRate) {
        this.bartenderRate = bartenderRate;
    }

    public BigInteger getBOHRate() {
        return BOHRate;
    }

    public void setBOHRate(BigInteger BOHRate) {
        this.BOHRate = BOHRate;
    }

    public BigInteger getBusserRate() {
        return busserRate;
    }

    public void setBusserRate(BigInteger busserRate) {
        this.busserRate = busserRate;
    }

    public BigInteger getServerRate() {
        return serverRate;
    }

    public void setServerRate(BigInteger serverRate) {
        this.serverRate = serverRate;
    }

    public BigInteger getTipoutByRole(Employee employee){
        if(employee instanceof Bartender) {
            return getBartenderRate();
        } else if (employee instanceof BOH) {
            return getBOHRate();
        } else if (employee instanceof Server) {
            return getServerRate();
        } else if (employee instanceof Busser) {
            return getBusserRate();
        }else{
//            throw some error message
            return null;
        }
    }
}
