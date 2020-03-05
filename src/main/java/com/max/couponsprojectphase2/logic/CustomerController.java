package com.max.couponsprojectphase2.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.max.couponsprojectphase2.dao.ICustomerDao;
import com.max.couponsprojectphase2.entities.Customer;
import com.max.couponsprojectphase2.enums.ErrorType;
import com.max.couponsprojectphase2.exceptions.ApplicationException;
import com.max.couponsprojectphase2.utils.DateUtils;

@Controller
public class CustomerController {

	@Autowired
	private ICustomerDao customerDao;
	
	@Autowired
	private UserController userController;


	public Long createCustomer(Customer customer) throws ApplicationException {
		validateCustomer(customer);
		validateCustomerId(customer.getId(), false);
		validateCustomerDoesNotExist(customerDao.findCustomerByEmail(customer.getEmail()));
		Long userId = userController.createUser(customer.getUser());
		customer.setId(userId);
		return customerDao.save(customer).getId();
	}
	
	public Customer getCustomerByCustomerId(Long customerId) throws ApplicationException {
		validateTable();
		validateCustomerExist(customerId);
		return customerDao.findById(customerId).get();
	}

	public List<Customer> getCustomerByFirstName(String firstName) throws ApplicationException {
		validateFirtsName(firstName);
		List<Customer> customersByFirstName = customerDao.findCustomersByFirstName(firstName);
		return customersByFirstName;
	}

	public List<Customer> getCustomerByLastName(String lastName) throws ApplicationException {
		validateLastName(lastName);
		List<Customer> customersByLastName = customerDao.findCustomersByLastName(lastName);
		return customersByLastName;
	}

	public List<Customer> getCustomerByAddress(String address) throws ApplicationException {
		validateAddress(address);
		List<Customer> customersByAddress = customerDao.findCustomersByAddress(address);
		return customersByAddress;
	}

	public List<Customer> getAllCustomers() throws ApplicationException {
		validateTable();
		List<Customer> customers = (List<Customer>) customerDao.findAll();
		return customers;
	}

	public void updateCustomer(Customer updatedCustomer) throws ApplicationException {
		validateTable();
		validateCustomer(updatedCustomer);
		validateCustomerId(updatedCustomer.getId(), true);
		validateCustomerExist(updatedCustomer.getId());
		validateUpdateCustomer(updatedCustomer);
		customerDao.save(updatedCustomer);
	}

	public void deleteCustomer(Long customerId) throws ApplicationException {
		validateTable();
		validateCustomerExist(customerId);
		customerDao.deleteById(customerId);
	}

	private void validateCustomerId(Long customerId, boolean isRequired) throws ApplicationException {
		if (isRequired) {
			if (customerId == null) {
				throw new ApplicationException(ErrorType.NULL_DATA,
						DateUtils.getCurrentDateAndTime() + ": Customer id is not supplied.");
			}
		} else {
			if (customerId != null) {
				throw new ApplicationException(ErrorType.REDUNDANT_DATA,
						DateUtils.getCurrentDateAndTime() + ": Id is redundant.");
			}
		}
	}

	private void validateCustomerExist(Long customerId) throws ApplicationException {
		if (!customerDao.findById(customerId).isPresent()) {
			throw new ApplicationException(ErrorType.CUSTOMER_DOES_NOT_EXIST,
					DateUtils.getCurrentDateAndTime() + ": Customer doesn't exist.");
		}
	}

	private void validateCustomerDoesNotExist(Customer customer) throws ApplicationException {
		if (customer != null) {
			throw new ApplicationException(ErrorType.CUSTOMER_ALREADY_EXIST,
					DateUtils.getCurrentDateAndTime() + ": Customer already exist.");
		}
	}

	private void validateUpdateCustomer(Customer updatedCustomer) throws ApplicationException {
		Customer currentCustomer = customerDao.findById(updatedCustomer.getId()).get();
		if (currentCustomer.equals(updatedCustomer)) {
			throw new ApplicationException(ErrorType.UPDATE_FAILED,
					DateUtils.getCurrentDateAndTime() + ": No difference found between old and new data.");
		}
	}

	private void validateFirtsName(String firstName) throws ApplicationException {
		if (customerDao.findCustomersByFirstName(firstName) == null) {
			throw new ApplicationException(ErrorType.FIRST_NAME_DOES_NOT_EXIST,
					DateUtils.getCurrentDateAndTime() + ": First name doesn't exist.");
		}
	}

	private void validateLastName(String lastName) throws ApplicationException {
		if (customerDao.findCustomersByLastName(lastName) == null) {
			throw new ApplicationException(ErrorType.LAST_NAME_DOES_NOT_EXIST,
					DateUtils.getCurrentDateAndTime() + ": Last name doesn't exist.");
		}
	}

	private void validateAddress(String address) throws ApplicationException {
		if (customerDao.findCustomersByAddress(address) == null) {
			throw new ApplicationException(ErrorType.ADDRESS_DOES_NOT_EXIST,
					DateUtils.getCurrentDateAndTime() + ": Address doesn't exist.");
		}
	}

//	private void validateEmail(String email) throws ApplicationException {
//		if (customerDao.findCustomerByEmail(email) == null) {
//			throw new ApplicationException(ErrorType.EMAIL_DOES_NOT_EXIST,
//					DateUtils.getCurrentDateAndTime() + ": Email doesn't exist.");
//		}
//	}

	private void validateCustomer(Customer customer) throws ApplicationException {
		List<Customer> customersList = (List<Customer>) customerDao.findAll();
		if (customer == null) {
			throw new ApplicationException(ErrorType.NULL_DATA,
					DateUtils.getCurrentDateAndTime() + ": Customer is null.");
		}
		if (customer.getFirstName() == null) {
			throw new ApplicationException(ErrorType.NULL_DATA,
					DateUtils.getCurrentDateAndTime() + ": First name is null.");
		}
		if (customer.getFirstName().isEmpty()) {
			throw new ApplicationException(ErrorType.EMPTY_DATA,
					DateUtils.getCurrentDateAndTime() + ": First name is empty.");
		}
		if (customer.getLastName() == null) {
			throw new ApplicationException(ErrorType.NULL_DATA,
					DateUtils.getCurrentDateAndTime() + ": Last name is null.");
		}
		if (customer.getLastName().isEmpty()) {
			throw new ApplicationException(ErrorType.EMPTY_DATA,
					DateUtils.getCurrentDateAndTime() + ": Last name is empty.");
		}
		if (customer.getAddress() == null) {
			throw new ApplicationException(ErrorType.NULL_DATA,
					DateUtils.getCurrentDateAndTime() + ": Address is null.");
		}
		if (customer.getAddress().isEmpty()) {
			throw new ApplicationException(ErrorType.EMPTY_DATA,
					DateUtils.getCurrentDateAndTime() + ": Address is empty.");
		}
		if (customer.getEmail() == null) {
			throw new ApplicationException(ErrorType.NULL_DATA, DateUtils.getCurrentDateAndTime() + ": Email is null.");
		}
		if (customer.getEmail().isEmpty()) {
			throw new ApplicationException(ErrorType.EMPTY_DATA,
					DateUtils.getCurrentDateAndTime() + ": Email is empty.");
		}
		if (customersList.contains(customer)) {
			throw new ApplicationException(ErrorType.CUSTOMER_ALREADY_EXIST,
					DateUtils.getCurrentDateAndTime() + ": Customer " + customer.getUser() + " already exist.");
		}

	}

	private void validateTable() throws ApplicationException {
		if (customerDao.findAll() == null) {
			throw new ApplicationException(ErrorType.EMPTY_TABLE,
					DateUtils.getCurrentDateAndTime() + ": Table is not exist.");
		}

	}
}
