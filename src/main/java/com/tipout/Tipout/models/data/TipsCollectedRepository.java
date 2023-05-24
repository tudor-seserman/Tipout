package com.tipout.Tipout.models.data;

import com.tipout.Tipout.models.Employee;
import com.tipout.Tipout.models.Tips;
import com.tipout.Tipout.models.TipsCollected;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface TipsCollectedRepository extends CrudRepository<TipsCollected,Integer> {
//    @Query(value = "SELECT Employee.firstName, Employee.lastName, Tips.tips " +
//            "FROM tipout.TipsCollected_employeeTipsMap " +
//            "Join Tips on Tips.id=employeeTipsMap_id " +
//            "Join Employee on Employee.id =employeeTipsMap_KEY " +
//            "Where tipout.TipsCollected_employeeTipsMap.TipsCollected_id = 16",
//            nativeQuery = true)
    @Query(value="SELECT Sum(Tips.tips)\n" +
            "FROM TipsCollected_employeeTipsMap\n" +
            "Join Tips on Tips.id=employeeTipsMap_id\n" +
            "Where TipsCollected_employeeTipsMap.TipsCollected_id = ?1",
                nativeQuery = true)
    BigDecimal findByTotalTipPool(Integer id);


}
