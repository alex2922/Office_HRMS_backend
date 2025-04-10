package com.SaharaAmussmentPark.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SaharaAmussmentPark.model.Employee;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	Optional<Employee> findByEmployeeIdOrEmailOrAadharNumber(String employeeId, String email, String aadharNumber);

	Employee findByEmployeeId(String employeeId);

	Optional<Employee> findByuId(Integer uId);
	Optional<Employee> findEmployeeByEmployeeId(String employeeId);


	Optional<Employee> findByemployeeId(String employeeId);

}
