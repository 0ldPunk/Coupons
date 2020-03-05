package com.max.couponsprojectphase2.dao;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.max.couponsprojectphase2.entities.Company;

public interface ICompanyDao extends CrudRepository<Company, Long> {

//	@Query("SELECT u FROM Company u WHERE u.companyId=:companyId")
//	Company findCompanyByCompanyId(@Param("companyId") Long companyId);

	@Query("SELECT u FROM Company u WHERE u.name=:name")
	Company findCompanyByName(@Param("name") String name);
	
	@Query("SELECT u FROM Company u WHERE u.address=:address")
	List<Company> findCompanyByAddress(@Param("address") String address);

    @Query("SELECT COUNT(*) FROM Company c")
    Long findTableSize();
}
