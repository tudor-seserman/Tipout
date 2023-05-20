package com.tipout.Tipout.models.Employees;

public class EmployeeTypes <T>{
    private T employee;

    public EmployeeTypes(T employee) {
        this.employee = employee;
    }

    public T getEmployee() {
        return employee;
    }

    public void setEmployee(T employee) {
        this.employee = employee;
    }
}
