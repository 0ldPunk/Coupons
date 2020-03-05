package com.max.couponsprojectphase2.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.max.couponsprojectphase2.entities.User;

public interface IUserDao extends CrudRepository<User, Long> {
	
	@Query("SELECT u FROM User u WHERE u.id=:userId")
	User findByUserId(@Param("userId") Long userId);

	@Query("SELECT u FROM User u WHERE u.userName=:userName and u.password=:password")
	User login(@Param("userName") String userName, @Param("password") String password);

	@Query("SELECT u FROM User u WHERE u.userName=:userName")
	User findByUserName(@Param("userName") String userName);

}
