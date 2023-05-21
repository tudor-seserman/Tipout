package com.tipout.Tipout.models.data;

import com.tipout.Tipout.models.Employee;
import com.tipout.Tipout.models.Employees.TipCollector;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TipCollectorRepository extends CrudRepository<TipCollector,Integer>{
}
