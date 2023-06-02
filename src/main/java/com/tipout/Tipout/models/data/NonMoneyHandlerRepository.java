package com.tipout.Tipout.models.data;

import com.tipout.Tipout.models.Employee;
import com.tipout.Tipout.models.Employees.NonMoneyHandler;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NonMoneyHandlerRepository extends CrudRepository<NonMoneyHandler,Integer>{
    List<NonMoneyHandler> findAllByDeletedFalse();
}
