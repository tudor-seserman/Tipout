package com.tipout.Tipout.models.data;

import com.tipout.Tipout.models.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;


@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Integer>{

    @Query(value="Delete  \n" +
            "FROM tipout.TipsCollected_employeeTipsMap\n" +
            "Where employeeTipsMap_KEY = ?1\n" +
            "\n" +
            "Delete\n" +
            "From tipscollected_moneyhandlertipsmap\n" +
            "Where moneyHandlerTipsMap_KEY = ?1\n" +
            "\n" +
            "Delete\n" +
            "From Employee\n" +
            "Where id = ?1", nativeQuery = true)
    List<Integer> completelyDeleteMoneyhandler(Integer id);

    @Query(value="Delete  \n" +
            "FROM tipout.TipsCollected_employeeTipsMap\n" +
            "Where employeeTipsMap_KEY = ?1\n" +
            "\n" +
            "Delete\n" +
            "From tipscollected_nonmoneyhandlertipsmap\n" +
            "Where moneyHandlerTipsMap_KEY = ?1\n" +
            "\n" +
            "Delete\n" +
            "From Employee\n" +
            "Where id = ?1", nativeQuery = true)
    List<Integer> completelyDeleteNonMoneyhandler(Integer id);
}
