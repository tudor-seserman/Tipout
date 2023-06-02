package com.tipout.Tipout.models.data;

import com.tipout.Tipout.models.Employee;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Integer>{
    @Transactional
    @Modifying
    @Query(value="DElETE FROM tipout.TipsCollected_employeeTipsMap Where employeeTipsMap_KEY = ?1", nativeQuery = true)
    void completelyDeleteEmployeeTipRecord(Integer id);
    @Transactional
    @Modifying
    @Query(value="DELETE FROM tipscollected_moneyhandlertipsmap Where moneyHandlerTipsMap_KEY = ?1", nativeQuery = true)
    void completelyDeleteMoneyhandlerTipRecord(Integer id);

    @Transactional
    @Modifying
    @Query(value="DELETE FROM tipscollected_nonmoneyhandlertipsmap Where nonmoneyHandlerTipsMap_KEY = ?1", nativeQuery = true)
    void completelyDeleteNonMoneyhandlerTipRecord(Integer id);

    @Query(value="SELECT *, 0 AS clazz_ FROM tipout.EMPLOYEE Where deleted = false", nativeQuery = true)
    List<Employee> findCurrentEmployees();

    @Query(value="SELECT *, 0 AS clazz_ FROM tipout.EMPLOYEE Where deleted = true", nativeQuery = true)
    List<Employee> findArhievedEmployees();

}
