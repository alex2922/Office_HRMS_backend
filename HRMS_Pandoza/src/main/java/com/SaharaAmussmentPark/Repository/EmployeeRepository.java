package com.SaharaAmussmentPark.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.SaharaAmussmentPark.Dto.EmployeeInfoDto;
import com.SaharaAmussmentPark.model.Employee;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	Optional<Employee> findByEmployeeIdOrEmailOrAadharNumber(String employeeId, String email, String aadharNumber);

	Optional<Employee> findEmployeeByEmployeeId(String employeeId);
	Employee findByEmployeeId(String employeeId);

	Optional<Employee> findByuId(int uId);
	 Employee findDetailsByuId(int uId);

	


	Employee findByuId(Integer uId);
	
//	@Query("SELECT e.employeeName FROM Employee e")
//    List<String> findAllEmployeeNames();
	@Query("SELECT new com.SaharaAmussmentPark.Dto.EmployeeInfoDto(e.employeeId, e.employeeName, e.designation) FROM Employee e")
	List<EmployeeInfoDto> findAllEmployeeInfo();

	



}
