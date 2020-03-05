package com.max.couponsprojectphase2.logic;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.max.couponsprojectphase2.dao.ICompanyDao;
import com.max.couponsprojectphase2.entities.Company;
import com.max.couponsprojectphase2.enums.ErrorType;
import com.max.couponsprojectphase2.exceptions.ApplicationException;
import com.max.couponsprojectphase2.utils.DateUtils;

@Controller
public class CompanyController {

	@Autowired
	private ICompanyDao companyDao;

	public void createCompany(Company company) throws ApplicationException {
        validateCompany(company);
        validateCompanyId(company.getId(), false);
        validateCompanyDoesNotExist(companyDao.findCompanyByName(company.getName()));
		companyDao.save(company);
	}

	public Company getCompanyByCompanyId(Long companyId) throws ApplicationException {
        validateTable();
        validateCompanyExist(companyId);
		return companyDao.findById(companyId).get();
	}

	public List<Company>getAllCompanies() throws ApplicationException {
		List<Company> companies = (List<Company>) companyDao.findAll();
		return companies;
	}

	public void updateComnpany(Company company) throws ApplicationException {
        validateTable();
        validateCompany(company);
        validateCompanyId(company.getId(), true);
		validateCompanyExist(company.getId());
		validateUpdateCompany(company);
		companyDao.save(company);
	}

	public void deleteCompany(Long companyId) throws ApplicationException {
        validateTable();
        validateCompanyExist(companyId);
		Company company = getCompanyByCompanyId(companyId);
		companyDao.delete(company);
	}
	
	
	boolean isCompanyExist(Long companyId) {
		return companyDao.findById(companyId).isPresent();
	}

    private void validateTable() throws ApplicationException {
        if (companyDao.findTableSize() == 0) {
            throw new ApplicationException(ErrorType.EMPTY_TABLE,
                    DateUtils.getCurrentDateAndTime() + ": Empty company table");
        }
    }

	private void validateCompanyId(Long companyId, boolean isRequired) throws ApplicationException {
		if (isRequired) {
			if (companyId == null) {
				throw new ApplicationException(ErrorType.NULL_DATA,
						DateUtils.getCurrentDateAndTime() + ": customerId is not supplied");
			}
		} else {
			if (companyId != null) {
				throw new ApplicationException(ErrorType.REDUNDANT_DATA,
						DateUtils.getCurrentDateAndTime() + ": companyId is redundant");
			}
		}
	}

    private void validateCompanyExist(Long companyId) throws ApplicationException {
        if (!companyDao.findById(companyId).isPresent()) {
            throw new ApplicationException(ErrorType.COMPANY_DOES_NOT_EXIST,
                    DateUtils.getCurrentDateAndTime() + ": Company doesn't exist");
        }
    }

    private void validateCompanyDoesNotExist(Company company) throws ApplicationException {
        if (company != null) {
            throw new ApplicationException(ErrorType.COMPANY_ALREADY_EXIST,
                    DateUtils.getCurrentDateAndTime() + ": Company already exist");
        }
    }

    private void validateUpdateCompany(Company updatedCompany) throws ApplicationException {
        Company currentCompany = companyDao.findById(updatedCompany.getId()).get();
        if (currentCompany.equals(updatedCompany)) {
            throw new ApplicationException(ErrorType.UPDATE_FAILED,
                    DateUtils.getCurrentDateAndTime() + ": No difference found between old and new data");
        }
    }

    private void validateCompany(Company company) throws ApplicationException {
        if (company == null) {
            throw new ApplicationException(ErrorType.NULL_DATA,
                    DateUtils.getCurrentDateAndTime() + "Null company");
        }
        if (company.getName() == null) {
            throw new ApplicationException(ErrorType.NULL_DATA,
                    DateUtils.getCurrentDateAndTime() + "Null company name");
        }
        if (company.getName().isEmpty()) {
            throw new ApplicationException(ErrorType.EMPTY_DATA,
                    DateUtils.getCurrentDateAndTime() + "Empty company name");
        }
        if (company.getAddress() == null) {
            throw new ApplicationException(ErrorType.NULL_DATA,
                    DateUtils.getCurrentDateAndTime() + "Null company email");
        }
        if (company.getAddress().isEmpty()) {
            throw new ApplicationException(ErrorType.EMPTY_DATA,
                    DateUtils.getCurrentDateAndTime() + "Empty company email");
        }
    }

}
