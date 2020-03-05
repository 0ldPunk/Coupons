//package com.max.couponsprojectphase2.threads;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.GregorianCalendar;
//import java.util.Timer;
//import java.util.TimerTask;
//
//import com.max.couponsprojectphase2.exceptions.ApplicationException;
//import com.max.couponsprojectphase2.logic.CouponController;
//import com.max.couponsprojectphase2.logic.PurchaseController;
//
//public class CouponsTimerTask extends TimerTask implements Runnable {
//
//	@Override
//	public void run() {
//		try {
//			CouponController couponController = new CouponController();
//			PurchaseController purchaseController = new PurchaseController();
//			Date date = new Date();
//			//Set all expired coupons as inActive
//			couponController.inActiveExpiredCoupons(date);
//			//Set all out of stock coupons as inActive
//			couponController.inActiveOutOfStockCoupons();
//
//			//Set all purchases of expired coupons as inActive
//			purchaseController.inActivePurchaseOfExpiredCoupons();
//			//Set all purchases of out of stock coupons as inActive
//			purchaseController.inActivePurchaseOfOutOfStockCoupons();
//
//		} catch (ApplicationException e) {
//			e.printStackTrace();
//		} finally {
//			System.out.println("\n Task finished at: " + new java.util.Date() + "\n");
//		}
//
//	}
//
//	public void startInActivingCoupons() {
//		GregorianCalendar gc = new GregorianCalendar();
//
//		gc.set(Calendar.HOUR, 00);
//		gc.set(Calendar.MINUTE, 00);
//		gc.set(Calendar.SECOND, 00);
//
//		gc.add(Calendar.DAY_OF_MONTH, 1);
//
//		Timer timer = new Timer();
//
//		timer.schedule(new CouponsTimerTask(), gc.getTime(), 1000 * 60 * 60 * 24);
//
//	}
//}
