package com.tipout.Tipout.models.data;

import com.tipout.Tipout.models.Employees.MoneyHandler;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MoneyHandlerRepository extends CrudRepository<MoneyHandler,Long>{
    List<MoneyHandler> findAllByDeletedFalseAndEmployer_Id(long Employer_Id);
}
