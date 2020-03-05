package com.max.couponsprojectphase2.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.max.couponsprojectphase2.entities.Coupon;
import com.max.couponsprojectphase2.enums.CouponType;

public interface ICouponDao extends CrudRepository<Coupon, Long> {
	
	@Query("SELECT c FROM Coupon c WHERE c.id=:couponId")
	Coupon findByCouponId(@Param("couponId") Long couponId);

	@Query("SELECT c FROM Coupon c WHERE c.title=:title")
	Coupon findByTitle(@Param("title") String title);

	@Query("SELECT c FROM Coupon c WHERE c.company=:companyId")
	List<Coupon> findAllCouponsByCompanyId(@Param("companyId") Long companyId);

	@Query("SELECT c FROM Coupon c WHERE c.type=:couponType")
	List<Coupon> finByType(@Param("couponType") CouponType couponType);

	@Modifying
	@Query("UPDATE Coupon c SET c.amount=:amount WHERE c.id=:couponId")
	void updateAmountOfCoupons(@Param("couponId") Long couponId, @Param("amount") int amount);
	
    @Query("DELETE FROM Coupon c WHERE c.endDate <= :date")
    void deleteAllExpiredCoupons(@Param("date") Date date);
    
    @Query("SELECT COUNT(*) FROM Coupon c")
    Long findTableSize();
}
