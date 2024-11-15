package com.idrbt.dr.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.idrbt.dr.admin.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUserId(String userId);

	//User findByEmailId(String emailId);

	//User findByUsername(String username);
}
