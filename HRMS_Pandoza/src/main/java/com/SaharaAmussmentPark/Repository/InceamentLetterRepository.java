package com.SaharaAmussmentPark.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SaharaAmussmentPark.model.IncreamentLetter;

@Repository
public interface InceamentLetterRepository extends JpaRepository<IncreamentLetter, Integer> {

	List<IncreamentLetter> findByEmployeeId(String employeeId);

	
	

}
