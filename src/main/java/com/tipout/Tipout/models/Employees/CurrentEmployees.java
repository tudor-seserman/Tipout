package com.tipout.Tipout.models.Employees;

import com.tipout.Tipout.models.AbstractEntity;
import com.tipout.Tipout.models.Employer;

import javax.persistence.*;

@MappedSuperclass
public abstract class CurrentEmployees extends AbstractEntity {


    private Employer employer;

    private CurrentEmployees role;

    public CurrentEmployees() {}

    public CurrentEmployees(CurrentEmployees role) {
        super();
        this.role = role;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public CurrentEmployees getRole() {
        return role;
    }

    public void setRole(CurrentEmployees role) {
        this.role = role;
    }
}
