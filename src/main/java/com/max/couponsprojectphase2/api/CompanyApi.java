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

import com.max.couponsprojectphase2.entities.Company;
import com.max.couponsprojectphase2.exceptions.ApplicationException;
import com.max.couponsprojectphase2.logic.CompanyController;

@RestController
@RequestMapping("/company")
public class CompanyApi {

	@Autowired
	private CompanyController companyController;

	// method=POST URL=http://localhost:8080/company
	@PostMapping
	public void createCompany(@RequestBody Company company) throws ApplicationException {
		this.companyController.createCompany(company);
		System.out.println("Company " + company + " has been successfuly created.");
	}

	// method=GET URL=http://localhost:8080/company/111
	@GetMapping("/{companyId}")
	public Company getCompanyByCompanyId(@PathVariable("companyId") Long companyId) throws ApplicationException {
		System.out.println("Requested company id is: " + companyId);
		return this.companyController.getCompanyByCompanyId(companyId);
	}

	// method=GET URL=http://localhost:8080/company
	@GetMapping
	public List<Company> getAllCompanies() throws ApplicationException {
		System.out.println("This is a companies list:");
		return this.companyController.getAllCompanies();
	}

	// method=PUT URL=http://localhost:8080/company
	@PutMapping
	public void updateComnpany(@RequestBody Company company) throws ApplicationException {
		this.companyController.updateComnpany(company);
		System.out.println("Updated company is: " + company);
	}

	// Delete company from companies (COMPANY_ID is a FK in users & coupons)
	// method=DELETE URL=http://localhost:8080/company/111
	@DeleteMapping("/{companyId}")
	public void deleteCompany(@PathVariable("companyId") Long companyId) throws ApplicationException {
		this.companyController.deleteCompany(companyId);
		System.out.println("Delete company by id " + companyId + " succeed.");
	}
}
