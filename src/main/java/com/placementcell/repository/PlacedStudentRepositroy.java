package com.placementcell.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.placementcell.dto.PlacedStudentDTO;
import com.placementcell.entities.PlacedStudent;

public interface PlacedStudentRepositroy extends JpaRepository<PlacedStudent,Integer> {

	@Query(value="select id,date as placedDate,email,first_name as firstName,image,last_name as lastName,linkedin,company_name as companyName,ctc,offer_letter as offerLetter from placed_student\r\n"
			+ "inner join company_details ON company_details.company_id = placed_student.company_details_placed_company_id order by date desc",nativeQuery = true)
	List<PlacedStudentDTO> findAllWithCompanyName();
}
