package com.tipout.Tipout.models.data;

import com.tipout.Tipout.models.Employees.MoneyHandler;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MoneyHandlerRepository extends CrudRepository<MoneyHandler,Integer>{
}
