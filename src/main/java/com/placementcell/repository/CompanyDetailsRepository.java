package com.placementcell.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.placementcell.entities.CompanyDetails;

@Repository
public interface CompanyDetailsRepository extends JpaRepository<CompanyDetails,Integer> {

	@Query(value="select company_id from company_details where company_name=:name",nativeQuery = true)
	public int findCompanyByName(@Param("name") String name);
	
	@Query(value="select * from company_details where company_name=:name",nativeQuery = true)
	public CompanyDetails findCompanyObjectByName(@Param("name") String name);
}
