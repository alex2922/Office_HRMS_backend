package com.SaharaAmussmentPark.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SaharaAmussmentPark.model.IncreamentLetter;

@Repository
public interface InceamentLetterRepository extends JpaRepository<IncreamentLetter, Integer> {

	Optional<IncreamentLetter> findByEmployeeId(String employeeId);

}
