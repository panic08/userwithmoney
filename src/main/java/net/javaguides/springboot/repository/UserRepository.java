package net.javaguides.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.model.UserWithMoney;

@Repository
public interface UserRepository extends JpaRepository<UserWithMoney, Long> {

	UserWithMoney findByEmail(String email);
	 
}
