package com.tipout.Tipout.models.data;

import com.tipout.Tipout.models.Employee;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;


@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Integer>{
}
