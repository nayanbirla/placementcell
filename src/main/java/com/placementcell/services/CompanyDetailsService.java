package com.placementcell.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.placementcell.entities.CompanyDetails;
import com.placementcell.entities.Users;
import com.placementcell.exceptions.CompanyNotFoundException;
import com.placementcell.repository.CompanyDetailsRepository;
import com.placementcell.repository.UserRepository;

@Service
public class CompanyDetailsService {

	@Autowired
	private CompanyDetailsRepository companyDetailsRepository;
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private JwtService jwtService;
	
	public CompanyDetails add(CompanyDetails companyDetails,String token)
	{
		String username=jwtService.extractUserName(token);
		Users user=userRepository.FindByEmail(username).get();
		companyDetails.setAdminId(user);
		return companyDetailsRepository.save(companyDetails);
	}
	
	public List<CompanyDetails> getAll() {
		return companyDetailsRepository.findAll();
	}
	
	public CompanyDetails get(int id) {
		return companyDetailsRepository.findById(id).orElseThrow();
	}
	
	public CompanyDetails update(CompanyDetails companyDetails) throws CompanyNotFoundException
	{
		try {
		int id=companyDetailsRepository.findCompanyByName(companyDetails.getCompanyName());
		companyDetails.setCompanyId(id);
		return companyDetailsRepository.save(companyDetails);
		}catch(Exception ee)
		{
			throw new CompanyNotFoundException();
		}
		
	}
	
}
