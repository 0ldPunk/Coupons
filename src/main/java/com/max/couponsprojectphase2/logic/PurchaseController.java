package com.max.couponsprojectphase2.logic;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.max.couponsprojectphase2.entities.Coupon;
import com.max.couponsprojectphase2.entities.Customer;
import com.max.couponsprojectphase2.entities.Purchase;
import com.max.couponsprojectphase2.entities.User;
import com.max.couponsprojectphase2.enums.ClientType;
import com.max.couponsprojectphase2.enums.ErrorType;
import com.max.couponsprojectphase2.dao.IPurchaseDao;
import com.max.couponsprojectphase2.data.LoggedInUserData;
import com.max.couponsprojectphase2.data.PurchaseDataObject;
import com.max.couponsprojectphase2.exceptions.ApplicationException;
import com.max.couponsprojectphase2.utils.DateUtils;

@Controller
public class PurchaseController {

	@Autowired
	private IPurchaseDao purchaseDao;

	@Autowired
	private CouponController couponController;

	@Autowired
	private CustomerController customerController;

	@Transactional
	public Purchase createPurchase(PurchaseDataObject purchaseData, LoggedInUserData loggedInUserData)
			throws ApplicationException {
		validatePurchase(purchaseData);
		validateLoggedInUserData(loggedInUserData);
		Purchase purchase = createPurchaseFromPurchaseDataObject(purchaseData, loggedInUserData);
		return purchaseDao.save(purchase);
	}

	public Purchase getPurchaseById(Long purchaseId) throws ApplicationException {
		validatePurchaseExist(purchaseId);
		return purchaseDao.findById(purchaseId).get();
	}

	public List<Purchase> getAllPurchasesByCustomerId(Long customerId) throws ApplicationException {
		List<Purchase> purchasesByCustomerId = purchaseDao.findAllPurchasesByCustomerId(customerId);
		return purchasesByCustomerId;
	}

	public List<Purchase> getPurchasesByCouponId(Long couponId) throws ApplicationException {
		List<Purchase> purchases = purchaseDao.findPurchasesByCouponId(couponId);
		return purchases;
	}

	public List<Purchase> getAllPurchases() throws ApplicationException {
		validateTable();
		List<Purchase> purchases = (List<Purchase>) purchaseDao.findAll();
		return purchases;
	}

	public void updatePurchase(Purchase updatedPurchase) throws ApplicationException {
		validateTable();
		validatePurchaseId(updatedPurchase.getId(), true);
		validatePurchaseExist(updatedPurchase.getId());
		validateUpdatePurchase(updatedPurchase);
		this.purchaseDao.save(updatedPurchase);
	}

	public void deletePurchase(Long purchaseId) throws ApplicationException {
		validatePurchaseExist(purchaseId);
		this.purchaseDao.deleteById(purchaseId);
	}

	@Transactional
	private Purchase createPurchaseFromPurchaseDataObject(PurchaseDataObject purchaseData, LoggedInUserData userData)
			throws ApplicationException {
		Coupon coupon = couponController.getCouponById(purchaseData.getCouponId());
		long couponId = coupon.getId();
		int currentAmountOfCoupons = coupon.getAmount();
		int amountOfCouponsToBuy = purchaseData.getAmount();
		int updatedCouponAmount = (currentAmountOfCoupons - amountOfCouponsToBuy);
		couponController.updateAmountOfCoupons(couponId, updatedCouponAmount);
		Customer customer = new Customer();
		User user = new User();
		user.setId(userData.getUserId());
		customer = this.customerController.getCustomerByCustomerId(user.getId());
		return new Purchase(amountOfCouponsToBuy, coupon, customer);
	}
//	public void inActivePurchaseOfExpiredCoupons() {
//		
//	}
//
//	public void inActivePurchaseOfOutOfStockCoupons() {
//
//	}

	private void validateLoggedInUserData(LoggedInUserData loggedInUserData) throws ApplicationException {
		if (loggedInUserData == null) {
			throw new ApplicationException(ErrorType.NULL_DATA,
					DateUtils.getCurrentDateAndTime() + "loggedInUserData is empty");
		}
		if (loggedInUserData.getUserId() == null) {
			throw new ApplicationException(ErrorType.NULL_DATA, DateUtils.getCurrentDateAndTime() + "userId is Null");
		}
		if (loggedInUserData.getClientType() == null) {
			throw new ApplicationException(ErrorType.NULL_DATA, DateUtils.getCurrentDateAndTime() + "userType is Null");
		}
		if (!loggedInUserData.getClientType().equals(ClientType.CUSTOMER)) {
			throw new ApplicationException(ErrorType.INVALID_USER_TYPE, DateUtils.getCurrentDateAndTime() + "UserType: "
					+ loggedInUserData.getClientType() + " not allowed to purchase");
		}
		if (loggedInUserData.getToken() == null) {
			// Or hacking attempt?
			throw new ApplicationException(ErrorType.NULL_DATA, DateUtils.getCurrentDateAndTime() + "token is null");
		}
		if (loggedInUserData.getCompanyId() != null) {
			throw new ApplicationException(ErrorType.REDUNDANT_DATA,
					DateUtils.getCurrentDateAndTime() + "User has companyId");
		}
	}

	private void validatePurchaseId(Long purchaseId, boolean isRequired) throws ApplicationException {
		if (isRequired) {
			if (purchaseId == null) {
				throw new ApplicationException(ErrorType.NULL_DATA,
						DateUtils.getCurrentDateAndTime() + ": Purchase id is not supplied");
			}
		} else {
			if (purchaseId != null) {
				throw new ApplicationException(ErrorType.REDUNDANT_DATA,
						DateUtils.getCurrentDateAndTime() + "Id is redundant");
			}
		}
	}

	private void validatePurchaseExist(Long purchaseId) throws ApplicationException {
		if (!purchaseDao.findById(purchaseId).isPresent()) {
			throw new ApplicationException(ErrorType.PURCHASE_DOES_NOT_EXIST,
					DateUtils.getCurrentDateAndTime() + ": Purchase doesn't exist");
		}
	}

	private void validateUpdatePurchase(Purchase updatedPurchase) throws ApplicationException {
		Purchase currentPurchase = purchaseDao.findById(updatedPurchase.getId()).get();
		if (currentPurchase.equals(updatedPurchase)) {
			throw new ApplicationException(ErrorType.UPDATE_FAILED,
					DateUtils.getCurrentDateAndTime() + ": No difference found between old and new data");
		}
	}

	private void validatePurchase(PurchaseDataObject purchase) throws ApplicationException {
		if (purchase == null) {
			throw new ApplicationException(ErrorType.NULL_DATA,
					DateUtils.getCurrentDateAndTime() + ": Purchase is null");
		}
		if (purchase.getAmount() <= 0) {
			throw new ApplicationException(ErrorType.INVALID_AMOUNT,
					DateUtils.getCurrentDateAndTime() + ": Amount must be positive and greater then zero.");
		}

	}

	private void validateTable() throws ApplicationException {
		if (purchaseDao.findAll() == null) {
			throw new ApplicationException(ErrorType.EMPTY_TABLE,
					DateUtils.getCurrentDateAndTime() + "Table is not exist.");
		}

	}
}
