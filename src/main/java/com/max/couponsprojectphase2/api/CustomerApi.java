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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.max.couponsprojectphase2.entities.Customer;
//import com.max.couponsprojectphase2.entities.Registration;
import com.max.couponsprojectphase2.exceptions.ApplicationException;
import com.max.couponsprojectphase2.logic.CustomerController;

@RestController
@RequestMapping("/customer")
public class CustomerApi {

	@Autowired
	private CustomerController customerController;

//	// method=POST URL=http://localhost:8080/customer/register
//	@PostMapping("/register")
//	public void createCustomer(@RequestBody Registration registration) throws ApplicationException {
//		System.out.println(registration);
//		this.customerController.createCustomer(registration);
//	}

	// method=POST URL=http://localhost:8080/customer
    @PostMapping
    public long createCustomer(@RequestBody Customer customer) throws ApplicationException {
        return this.customerController.createCustomer(customer);
    }
	
	// method=GET URL=http://localhost:8080/customer/111
	@GetMapping("/{customerId}")
	public Customer getCustomerById(@PathVariable("customerId") Long customerId) throws ApplicationException {
		System.out.println("Requested customer id is: " + customerId);
		return this.customerController.getCustomerByCustomerId(customerId);
	}

	// method=GET URL=http://localhost:8080/customer/byFirstName?firstName=fn
	@GetMapping("/byFirstName")
	public List<Customer> getCustomersByFirstName(@RequestParam("firstName") String firstName)
			throws ApplicationException {
		System.out.println("Requested customers by firts name: " + firstName);
		return this.customerController.getCustomerByFirstName(firstName);

	}

	// method=GET URL=http://localhost:8080/customer/byLastName?lastName=ln
	@GetMapping("/byLastName")
	public List<Customer> getCustomersByLastName(@RequestParam("lastName") String lastName)
			throws ApplicationException {
		System.out.println("Requested customers by last name: " + lastName);
		return this.customerController.getCustomerByLastName(lastName);
	}

	// method=GET URL=http://localhost:8080/customer/byAddress?address=add
	@GetMapping("/byAddress")
	public List<Customer> getCustomersByAddress(@RequestParam("address") String address) throws ApplicationException {
		System.out.println("Requested customers by address: " + address);
		return this.customerController.getCustomerByAddress(address);
	}

	// method=GET URL=http://localhost:8080/customer
	@GetMapping
	public List<Customer> getAllCustomers() throws ApplicationException {
		System.out.println("This is a customers list: ");
		return this.customerController.getAllCustomers();
	}

	// method=PUT URL=http://localhost:8080/customer
	@PutMapping
	public void updateCustomer(@RequestBody Customer customer) throws ApplicationException {
		this.customerController.updateCustomer(customer);
		System.out.println("Updated customer is: " + customer);
	}

	// method=DELETE URL=http://localhost:8080/customer/111
	@DeleteMapping("/{customerId}")
	public void deleteCustomer(@PathVariable("customerId") Long customerId) throws ApplicationException {
		this.customerController.deleteCustomer(customerId);
		System.out.println("Delete customer by id " + customerId + " succeed.");
	}
	
	
}
