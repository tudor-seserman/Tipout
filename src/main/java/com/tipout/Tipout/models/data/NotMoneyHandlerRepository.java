package com.tipout.Tipout.models.data;

import com.tipout.Tipout.models.Employees.NotMoneyHandler;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NotMoneyHandlerRepository extends CrudRepository<NotMoneyHandler,Integer>{
}
