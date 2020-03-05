package com.max.couponsprojectphase2.logic;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.max.couponsprojectphase2.dao.ICouponDao;
import com.max.couponsprojectphase2.entities.Coupon;
import com.max.couponsprojectphase2.enums.CouponType;
import com.max.couponsprojectphase2.enums.ErrorType;
//import com.max.couponsprojectphase2.enums.ErrorType;
//import com.max.couponsprojectphase2.utils.DateUtils;
import com.max.couponsprojectphase2.exceptions.ApplicationException;
import com.max.couponsprojectphase2.utils.DateUtils;

@Controller
public class CouponController {

	@Autowired
	private ICouponDao couponDao;

	public void createCoupon(Coupon coupon) throws ApplicationException {
    	validateCoupon(coupon);
    	validateCouponId(coupon.getId(), false); // validate provided id is NULL
    	validateCouponDoesNotExist(couponDao.findByTitle(coupon.getTitle()));
		couponDao.save(coupon);
	}

	public Coupon getCouponById(Long couponId) throws ApplicationException {
    	validateTable();
    	validateCouponExist(couponId);
		return couponDao.findById(couponId).get();
	}
	
	public List<Coupon> getCouponsByCouponType(CouponType couponType) throws ApplicationException {
		List<Coupon> couponsByType = couponDao.finByType(couponType);
		return couponsByType;
	}
	
	public List<Coupon> getAllCoupons() throws ApplicationException {
		List<Coupon> coupons = (List<Coupon>) couponDao.findAll();
		return coupons;
	}
	
	public List<Coupon> getAllCouponsByCompanyId(Long companyId) throws ApplicationException {
		List<Coupon> coupons = couponDao.findAllCouponsByCompanyId(companyId);
		return coupons;
	}

	public void updateCoupon(Coupon coupon) throws ApplicationException {
    	validateTable();
    	validateCoupon(coupon);
    	validateCouponId(coupon.getId(), true);
    	validateCouponExist(coupon.getId());
    	validateUpdateCoupon(coupon);
		couponDao.save(coupon);
	}
	
	public void deleteCoupon(Long couponId) throws ApplicationException {
    	validateTable();
    	validateCouponExist(couponId);
		couponDao.deleteById(couponId);
	}
	 public void updateAmountOfCoupons(long couponId, int amount) throws ApplicationException {
	        couponDao.updateAmountOfCoupons(couponId, amount);
	    }


	    private void validateTable() throws ApplicationException {
	        if (couponDao.findTableSize() == 0) {
	            throw new ApplicationException(ErrorType.EMPTY_TABLE,
	                    DateUtils.getCurrentDateAndTime() + ": Coupon Table is empty");
	        }
	    }

	    private void validateCouponId(Long couponId, boolean isRequired) throws ApplicationException {
	        if (isRequired) {
	            if (couponId == null) {
	                throw new ApplicationException(ErrorType.NULL_DATA,
	                        DateUtils.getCurrentDateAndTime() + ": CouponId is not supplied");
	            }
	        } else {
	            if (couponId != null) {
	                throw new ApplicationException(ErrorType.REDUNDANT_DATA,
	                        DateUtils.getCurrentDateAndTime() + ": Id is redundant");
	            }
	        }
		}

	    public void validateCouponExist(Long couponId) throws ApplicationException {
	    	if (!couponDao.findById(couponId).isPresent()) {
	    		throw new ApplicationException(ErrorType.COUPON_DOES_NOT_EXIST,
						DateUtils.getCurrentDateAndTime() + ": Coupon doesn't exist");
			}
	    }

	    private void validateCouponDoesNotExist(Coupon coupon) throws ApplicationException {
			if (coupon != null) {
				throw new ApplicationException(ErrorType.COUPON_ALREADY_EXISTS,
						DateUtils.getCurrentDateAndTime() + ": Coupon already exist");
			}
	    }

		private void validateUpdateCoupon(Coupon updatedCoupon) throws ApplicationException {
			Coupon currentCoupon = couponDao.findById(updatedCoupon.getId()).get();
			if (currentCoupon.equals(updatedCoupon)) {
				throw new ApplicationException(ErrorType.UPDATE_FAILED,
						DateUtils.getCurrentDateAndTime() + ": No difference found between old and new data");
			}
		}

	    private void validateCoupon(Coupon coupon) throws ApplicationException {
	        if (coupon == null) {
	            throw new ApplicationException(ErrorType.NULL_DATA,
	                    DateUtils.getCurrentDateAndTime() + ": Coupon is null");
	        }
	        if (coupon.getTitle() == null) {
	            throw new ApplicationException(ErrorType.NULL_DATA,
	                    DateUtils.getCurrentDateAndTime() + ": Title is null");
	        }
	        if (coupon.getTitle().isEmpty()) {
	            throw new ApplicationException(ErrorType.EMPTY_DATA,
	                    DateUtils.getCurrentDateAndTime() + ": Title is empty");
	        }
	        if (coupon.getDescription() == null) {
	            throw new ApplicationException(ErrorType.NULL_DATA,
	                    DateUtils.getCurrentDateAndTime() + ": Description is null");
	        }
	        if (coupon.getDescription().isEmpty()) {
	            throw new ApplicationException(ErrorType.EMPTY_DATA,
	                    DateUtils.getCurrentDateAndTime() + ": Description is empty");
	        }
	        if (coupon.getPrice() <= 0) {
	            throw new ApplicationException(ErrorType.INVALID_PRICE,
	                    DateUtils.getCurrentDateAndTime() + ": Invalid Price");
	        }
	        if (coupon.getAmount() <= 0) {
	            throw new ApplicationException(ErrorType.INVALID_AMOUNT,
	                    DateUtils.getCurrentDateAndTime() + ": Invalid amount");
	        }
	        if (coupon.getStartDate() == null || coupon.getEndDate() == null) {
	            throw new ApplicationException(ErrorType.NULL_DATA,
	                    DateUtils.getCurrentDateAndTime() + ": Null date(s)");
	        }
	        if (coupon.getStartDate().after(coupon.getEndDate())) {
	            throw new ApplicationException(ErrorType.INVALID_DATES,
	                    DateUtils.getCurrentDateAndTime() + ": StartDate is after EndDate");
	        }
	        if (coupon.getType() == null) {
	            throw new ApplicationException(ErrorType.NULL_DATA,
	                    DateUtils.getCurrentDateAndTime() + ": Null or Empty Type");
	        }
	        if (!CouponType.contains(coupon.getType())) {
	            throw new ApplicationException(ErrorType.COUPON_TYPE_DOES_NOT_EXIST,
	                    DateUtils.getCurrentDateAndTime() + ": Coupon type " + coupon.getType() + " doesn't exist");
	        }
	    }

	    boolean isCouponExist(Coupon coupon) throws ApplicationException {
	        validateCouponId(coupon.getId(), true);
	        if (couponDao.findByTitle(coupon.getTitle()) == null) {
	            return false;
	        }
	        return true;
	    }

	    public void deleteAllExpiredCoupons(Date date) throws ApplicationException{
	        couponDao.deleteAllExpiredCoupons(date);
	    }
	

	
	public void inActiveExpiredCoupons(Date date) {
		// TODO Auto-generated method stub
		
	}
	
	public void inActiveOutOfStockCoupons() {
		// TODO Auto-generated method stub
		
	}
//	public void removeExpiredCoupon(Date todayDate) throws ApplicationException {
//		if(this.couponDao.removeExpiredCoupons(todayDate)) {
//			
//		}
//	}
//
//	private void validateCoupon(Coupon coupon) throws ApplicationException {
//		if (coupon == null) {
//			throw new ApplicationException(ErrorType.NULL_DATA, "Null coupon.");
//		}
//		if (coupon.getId() < 0) {
//			throw new ApplicationException(ErrorType.INVALID_ID, "The id is not valid.");
//		}
//		if (coupon.getPrice() < 0) {
//			throw new ApplicationException(ErrorType.INVALID_PRICE, "The price must be positive!");
//		}
//		if (coupon.getStartDate() == null || coupon.getEndDate() == null) {
//			throw new ApplicationException(ErrorType.NULL_DATA, "Null date.");
//		}
//		if (coupon.getStartDate().after(coupon.getEndDate())) {
//			throw new ApplicationException(ErrorType.INVALID_DATES, "Start date is after end date.");
//		}
//		if (coupon.getAmount() <= 0) {
//			throw new ApplicationException(ErrorType.INVALID_AMOUNT, "Invalid amount.");
//		}
//		if(coupon.getType() == null) {
//			throw new ApplicationException(ErrorType.NULL_DATA, "Null coupon type.");
//		}
//		if (coupon.getType().isEmpty()) {
//			throw new ApplicationException(ErrorType.EMPTY_DATA, "The type is not specified");
//		}
//		if (coupon.getTitle() == null) {
//			throw new ApplicationException(ErrorType.NULL_DATA, "Null title.");
//		}
//		if (coupon.getTitle().isEmpty()) {
//			throw new ApplicationException(ErrorType.EMPTY_DATA, "Title is not specified");
//		}
//		if (coupon.getDescription() == null) {
//			throw new ApplicationException(ErrorType.NULL_DATA, "Null description.");
//		}
//		if (coupon.getDescription().isEmpty()) {
//			throw new ApplicationException(ErrorType.EMPTY_DATA, "Description is not specified.");
//		}
//		if (coupon.getCompanyId() < 0) {
//			throw new ApplicationException(ErrorType.INVALID_ID, "Invalid company ID.");
//		}
//	}
}
