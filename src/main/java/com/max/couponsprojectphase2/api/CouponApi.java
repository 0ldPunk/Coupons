package com.max.couponsprojectphase2.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.max.couponsprojectphase2.entities.Coupon;
import com.max.couponsprojectphase2.enums.CouponType;
import com.max.couponsprojectphase2.exceptions.ApplicationException;
import com.max.couponsprojectphase2.logic.CouponController;

@RestController
@RequestMapping("/coupon")
public class CouponApi {

	@Autowired
	private CouponController couponController;

	// method=POST URL=http://localhost:8080/coupon
	@PostMapping
	public void createCoupon(@RequestBody Coupon coupon) throws ApplicationException {
		System.out.println(coupon);
		this.couponController.createCoupon(coupon);
		Long couponId = coupon.getId();
		System.out.println("Coupon " + couponId + " has been successfuly created.");
	}

	// method=GET URL=http://localhost:8080/coupon/444
	@GetMapping("/{couponId}")
	public Coupon getCoupon(@PathVariable("couponId") Long couponId) throws ApplicationException {
		System.out.println("Requested coupon id is: " + couponId);
		return this.couponController.getCouponById(couponId);
	}
	
	// method=GET URL=http://localhost:8080/coupon/couponType?couponType=type
	@GetMapping("/couponType")
	public List<Coupon> getCouponsByCouponType(@PathVariable("CouponType") CouponType couponType) throws ApplicationException {
		System.out.println("Requested coupon type is: " + couponType);
		return this.couponController.getCouponsByCouponType(couponType);
	}

	// method=GET URL=http://localhost:8080/coupon
	@GetMapping
	public List<Coupon> getAllCoupons() throws ApplicationException {
		System.out.println("This is a coupons list:");
		return this.couponController.getAllCoupons();
	}

//	// method=GET URL=http://localhost:8080/coupon/byCompanyId?companyId=111
//	@GetMapping("/allCouponsByCompanyId")
//	public List<Coupon> getAllCouponsByCompanyId(@PathVariable("companyId") Long companyId)
//			throws ApplicationException {
//		System.out.println("This is a coupons list by company with id: " + companyId);
//		return couponController.getAllCouponsByCompanyId(companyId);
//	}

	// method=PUT URL=http://localhost:8080/coupon
	@PutMapping
	public void updateCoupon(@RequestBody Coupon coupon) throws ApplicationException {
		this.couponController.updateCoupon(coupon);
		System.out.println("Updated coupon is: " + coupon);
	}

	// Delete coupon from coupons (COUPON_ID is a FK in purchases)
	// method=DELETE URL=http://localhost:8080/coupon/444
	@DeleteMapping("/{couponId}")
	public void deleteCoupon(@PathVariable("couponId") Long couponId) throws ApplicationException {
		this.couponController.deleteCoupon(couponId);
		System.out.println("Delete coupon by id " + couponId + " succeed.");
	}
}
