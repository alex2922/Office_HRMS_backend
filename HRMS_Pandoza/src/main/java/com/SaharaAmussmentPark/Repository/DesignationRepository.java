package com.SaharaAmussmentPark.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SaharaAmussmentPark.model.Department;
import com.SaharaAmussmentPark.model.Designation;

@Repository
public interface DesignationRepository extends JpaRepository<Designation, Integer> {
    boolean existsByNameAndDepartment(String name, Department department);
}
