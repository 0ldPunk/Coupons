//package com.max.couponsprojectphase2.test;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//import java.util.TimeZone;
//
//import com.max.couponsprojectphase2.beans.Company;
//import com.max.couponsprojectphase2.beans.Coupon;
//import com.max.couponsprojectphase2.beans.Customer;
//import com.max.couponsprojectphase2.dao.CompanyDao;
//import com.max.couponsprojectphase2.dao.CouponDao;
//import com.max.couponsprojectphase2.dao.CustomerDao;
//
//public class Tester {
//
//	public static void main(String[] args) throws Exception {
//
//		//-----Create Company-----
//
////		CompanyDao companyDao = new CompanyDao();
////		Company company = new Company("ZARA", "Azrieli");
////		companyDao.createCompany(company);
////		Company company2 = new Company("Tomy Hilfiger", "Eilat");
////		companyDao.createCompany(company2);
////		Company company3 = new Company("Skoda", "Beer Sheva");
////		companyDao.createCompany(company3);
//		
////		..........................................................................
////		Printing the object company before getting id from DB.
////		System.out.println(company);
////		Creating object company in the DB.
////		companyDao.createCompany(company);
////      Printing the object company after getting id from DB.
////		System.out.println(company);
////		...........................................................................
//
//		//-----Update Company-----
//
////		Company company = new Company (14, "ZARA", "Tel Aviv");
////		companyDao.updateComnpany(company);	
//
//		//-----Delete Company-----
//
////		companyDao.deleteCompany(9);
//		
//		//-----Is Company Exists By Id-----
//		
////		System.out.println(companyDao.isCompanyExistsById(14));
//		
//		//-----Is Company Exists By Name-----
//		
////		System.out.println(companyDao.isCompanyExistsByName("123d"));
////		System.out.println(companyDao.isCompanyExistsByName("ZARA"));
////		System.out.println(companyDao.isCompanyExistsByName("Tomy Hilfiger"));
////		System.out.println(companyDao.isCompanyExistsByName("Chupakabra"));
//
//		//-----Get Company By Company Id-----
//		
////		System.out.println(companyDao.getCompanyByCompanyId(14));
////		System.out.println(companyDao.getCompanyByCompanyId(9));
//
//		//-----Get All Companies-----
//
////		List<Company> companies = companyDao.getAllCompanies();
////		for (Company company : companies) {
////			System.out.println(company);
////		}
//		
//		//-----Create Coupon-----
////		CouponDao couponDao = new CouponDao();
////		---------------------------------
////		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
////		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
////		Date startDate = ft.parse("2019-06-23");
////		Date endDate = ft.parse("2019-06-30");
////		---------------------------------
//		
////		Coupon coupon = new Coupon("Cinema", startDate, endDate, 3, "Sale", 59.90f, "Description_coupon1", 14);
////		couponDao.createCoupon(coupon);
////		Coupon coupon2 = new Coupon("ZARA", startDate, endDate, 5, "Sale", 70.50f, "Description_coupon2", 11);
////		couponDao.createCoupon(coupon2);
////		Coupon coupon3 = new Coupon("Tomy Hilfiger", startDate, endDate, 1, "Sale", 90.10f, "Description_coupon3", 12);
////		couponDao.createCoupon(coupon3);
//
//		//-----Update Coupon-----
//
////		Coupon coupon = new Coupon (3, "Audi", startDate, endDate, 7, "Sale", 245000.00f, "Sale day", 14);
////		couponDao.updateCoupon(coupon);	
//		
//		//-----Delete Coupon-----
//
////		couponDao.deleteCoupon(1);
//
//		//-----Is Coupon Exists By Id-----
//		
////		System.out.println(couponDao.isCouponExistsById(3));
//		
//		//-----Get Coupon By Coupon Id-----
//		
////		System.out.println(couponDao.getCouponByCouponId(3));
//
//		//-----Get All Coupons-----
//
////		List<Coupon> coupons = couponDao.getAllCoupons();
////		for (Coupon coupon : coupons) {
////			System.out.println(coupon);			
////		}
//		
//		//-----Create Customer-----
//		CustomerDao customerDao = new CustomerDao();
//
//		Customer customer = new Customer();
//		customerDao.createCustomer(customer);
////		Coupon coupon = new Coupon("Cinema", startDate, endDate, 3, "Sale", 59.90f, "Description_coupon1", 14);
////		couponDao.createCoupon(coupon);
////		Coupon coupon2 = new Coupon("ZARA", startDate, endDate, 5, "Sale", 70.50f, "Description_coupon2", 11);
////		couponDao.createCoupon(coupon2);
////		Coupon coupon3 = new Coupon("Tomy Hilfiger", startDate, endDate, 1, "Sale", 90.10f, "Description_coupon3", 12);
////		couponDao.createCoupon(coupon3);
//
//		//-----Update Coupon-----
//
////		Coupon coupon = new Coupon (3, "Audi", startDate, endDate, 7, "Sale", 245000.00f, "Sale day", 14);
////		couponDao.updateCoupon(coupon);	
//		
//		//-----Delete Coupon-----
//
////		couponDao.deleteCoupon(1);
//
//		//-----Is Coupon Exists By Id-----
//		
////		System.out.println(couponDao.isCouponExistsById(3));
//		
//		//-----Get Coupon By Coupon Id-----
//		
////		System.out.println(couponDao.getCouponByCouponId(3));
//
//		//-----Get All Coupons-----
//
////		List<Coupon> coupons = couponDao.getAllCoupons();
////		for (Coupon coupon : coupons) {
////			System.out.println(coupon);			
////		}
//
//	}
//}
