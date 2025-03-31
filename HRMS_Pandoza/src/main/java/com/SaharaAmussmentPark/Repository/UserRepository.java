package com.SaharaAmussmentPark.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SaharaAmussmentPark.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User getByEmail(String email);
	 Optional<User> findByEmail(String email);

	User getByEmailAndPassword(String email, String password);

}
