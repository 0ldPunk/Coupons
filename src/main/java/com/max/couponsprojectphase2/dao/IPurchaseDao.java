package com.max.couponsprojectphase2.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.max.couponsprojectphase2.entities.Purchase;

public interface IPurchaseDao extends CrudRepository<Purchase, Long> {

	@Query("SELECT u FROM Purchase u WHERE u.id=:purchaseId")
	Purchase findByPurchaseId(@Param("purchaseId") Long purchaseId);

	@Query("SELECT p FROM Purchase p WHERE p.coupon.id=:couponId")
	List<Purchase> findPurchasesByCouponId(@Param("couponId") Long couponId);

	@Query("SELECT p FROM Purchase p WHERE p.customer.id=:customerId")
	List<Purchase> findByCustomerId(@Param("customerId") Long customerId);
	
    @Query("SELECT COUNT(*) FROM Purchase p")
    Long findTableSize();

}
