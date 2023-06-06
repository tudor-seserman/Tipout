package com.tipout.Tipout.models.data;

import com.tipout.Tipout.models.Employer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployerRepository extends CrudRepository<Employer, Integer> {
    Employer findByUsername(String username);
}
