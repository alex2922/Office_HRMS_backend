package com.SaharaAmussmentPark.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SaharaAmussmentPark.model.OfficialLetter;

@Repository
public interface OfficialLetterRepository extends JpaRepository<OfficialLetter, Integer> {
	OfficialLetter findByoname(String name);

}
