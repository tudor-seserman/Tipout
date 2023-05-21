package com.tipout.Tipout.models.data;

import com.tipout.Tipout.models.Employees.TipCollector;
import com.tipout.Tipout.models.Employees.TippedNotCollector;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TippedNotCollectorRepository extends CrudRepository<TippedNotCollector,Integer>{
}
