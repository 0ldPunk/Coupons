package com.max.couponsprojectphase2.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.max.couponsprojectphase2.entities.Customer;

public interface ICustomerDao extends CrudRepository<Customer, Long> {

	@Query("SELECT u FROM Customer u WHERE u.id=:customerId")
	Customer findByCustomerId(@Param("customerId") Long customerId);

	@Query("SELECT u FROM Customer u WHERE u.email=:email")
	Customer findCustomerByEmail(@Param("email") String email);

	@Query("SELECT u FROM Customer u WHERE u.firstName=:firstName")
	List<Customer> findCustomersByFirstName(@Param("firstName") String firstName);

	@Query("SELECT u FROM Customer u WHERE u.lastName=:lastName")
	List<Customer> findCustomersByLastName(@Param("lastName") String lastName);

	@Query("SELECT u FROM Customer u WHERE u.address=:address")
	List<Customer> findCustomersByAddress(@Param("address") String address);


}
