package com.sivale.test.jama_test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sivale.test.jama_test.repository.model.User;;

public interface UserDetailRepository extends JpaRepository<User,Long>{
	 User findByEmail(String email);
}
