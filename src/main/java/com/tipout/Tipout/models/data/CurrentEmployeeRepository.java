package com.tipout.Tipout.models.data;

import com.tipout.Tipout.models.Employee;
import com.tipout.Tipout.models.Employees.CurrentEmployees;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CurrentEmployeeRepository extends CrudRepository<CurrentEmployees,Integer>{
}
