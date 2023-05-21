package com.tipout.Tipout.models.data;

import com.tipout.Tipout.models.Employees.TipCollector;
import com.tipout.Tipout.models.Employer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployerRepository extends CrudRepository<Employer, Integer> {

}
