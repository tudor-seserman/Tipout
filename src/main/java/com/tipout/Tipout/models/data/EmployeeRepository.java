package com.tipout.Tipout.models.data;

import com.tipout.Tipout.models.Employee;
import com.tipout.Tipout.models.Employees.BOH;
import com.tipout.Tipout.models.Employees.TipCollector;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Integer>{
}
