package com.max.couponsprojectphase2.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.max.couponsprojectphase2.consts.Constants;
import com.max.couponsprojectphase2.data.LoggedInUserData;
import com.max.couponsprojectphase2.data.PurchaseDataObject;
import com.max.couponsprojectphase2.entities.Purchase;
import com.max.couponsprojectphase2.exceptions.ApplicationException;
import com.max.couponsprojectphase2.logic.CouponController;
import com.max.couponsprojectphase2.logic.CustomerController;
import com.max.couponsprojectphase2.logic.PurchaseController;

@RestController
@RequestMapping("/purchase")
public class PurchaseApi {

	@Autowired
	private PurchaseController purchaseController;
//    @Autowired
//    private CustomerController customerController;
//    @Autowired
//    private CouponController couponController;

	// method=POST URL=http://localhost:8080/purchase
	@PostMapping
	public Purchase createPurchase(@RequestBody PurchaseDataObject purchase, HttpServletRequest request) throws ApplicationException {
		LoggedInUserData userData = (LoggedInUserData) request.getAttribute(Constants.USER_DATA_KEY);
		System.out.println("You have successfully bought " + purchase.getAmount() + " coupons, of id " + purchase.getCouponId());
		return this.purchaseController.createPurchase(purchase, userData);
	}

	// method=GET URL=http://localhost:8080/purchase/111
	@GetMapping("/{purchaseId}")
	public Purchase getPurchaseById(@PathVariable("purchaseId") Long purchaseId) throws ApplicationException {
		System.out.println("Requested purchase id is: " + purchaseId);
		return this.purchaseController.getPurchaseById(purchaseId);
	}

	// method=GET URL=http://localhost:8080/purchase/byCustomerId?customerId=111
	@GetMapping
	@RequestMapping("/byCustomerId")
	public List<Purchase> getAllPurchasesByCustomerId(HttpServletRequest request) throws ApplicationException {
		LoggedInUserData loggedInUserData = (LoggedInUserData) request.getAttribute(Constants.USER_DATA_KEY);
		System.out.println("This is a purchases list of customer with id: " + loggedInUserData.getUserId());
		return this.purchaseController.getAllPurchasesByCustomerId(loggedInUserData.getUserId());
	}

	// method=GET URL=http://localhost:8080/purchase/byCouponId?couponId=42
	@GetMapping("/byCouponId")
	public List<Purchase> getPurchasesByCouponId(@RequestParam("couponId") Long couponId) throws ApplicationException {
		System.out.println("This is a purchases list of coupon with id: " + couponId);
		return this.purchaseController.getPurchasesByCouponId(couponId);
	}
	
//	@GetMapping("/myPurchases")
//	public List<Purchase> getMyPurchases(@RequestBody HttpServletRequest request) throws ApplicationException {
//		LoggedInUserData loggedInUserData = (LoggedInUserData) request.getAttribute("LoggedInUserData");
//		Long customerId = loggedInUserData.getUserId();
//		List<Purchase> purchases = this.purchaseController.getPurchasesByCustomerId(customerId);
//		return purchases;
//	}

	// method=GET URL=http://localhost:8080/purchase
	@GetMapping
	public List<Purchase> getAllPurchases() throws ApplicationException {
		System.out.println("This is an all purchases list: ");
		return this.purchaseController.getAllPurchases();
	}

	// method=PUT URL=http://localhost:8080/purchase
	@PutMapping
	public void updatePurchase(@RequestBody Purchase purchase) throws ApplicationException {
		System.out.println("Updated purchase is: " + purchase);
		this.purchaseController.updatePurchase(purchase);
	}

	// Delete purchase from purchases
	// method=DELETE URL=http://localhost:8080/purchase/111
	@DeleteMapping("/{purchaseId}")
	public void deletePurchase(@PathVariable("purchaseId") Long purchaseId) throws ApplicationException {
		System.out.println("Delete purchase by id " + purchaseId + " succeed.");
		this.purchaseController.deletePurchase(purchaseId);
	}
}
