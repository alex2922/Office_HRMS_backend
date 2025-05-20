package com.SaharaAmussmentPark.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SaharaAmussmentPark.model.Salary;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Integer> {

	Optional<Salary> findEmployeeByEmployeeIdAndMonth(String employeeId, String month);

}
