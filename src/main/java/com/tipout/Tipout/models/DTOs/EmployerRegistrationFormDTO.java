package com.tipout.Tipout.models.DTOs;
//Wrapper class to facilitate Employer creation
public class EmployerRegistrationFormDTO extends EmployerLoginFormDTO{
    private String verifyPassword;
    private String businessName;

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }
}
