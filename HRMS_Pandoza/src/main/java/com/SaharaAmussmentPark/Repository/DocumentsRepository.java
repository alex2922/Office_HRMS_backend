package com.SaharaAmussmentPark.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SaharaAmussmentPark.model.DocumentsManegment;

@Repository
public interface DocumentsRepository extends JpaRepository<DocumentsManegment, Integer> {

	Optional<DocumentsManegment> findByuId(int uId);

	Optional<DocumentsManegment> findByemployeeId(String employeeId);

}
