package com.SaharaAmussmentPark.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SaharaAmussmentPark.model.Employee;
import com.SaharaAmussmentPark.model.Salary;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	Optional<Employee> findByEmployeeIdOrEmailOrAadharNumber(String employeeId, String email, String aadharNumber);

	Optional<Employee> findEmployeeByEmployeeId(String employeeId);
	Employee findByEmployeeId(String employeeId);


	Employee findByuId(Integer uId);



}
