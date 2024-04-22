package com.placementcell.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.placementcell.dto.UsersInfoDetails;
import com.placementcell.dto.UserInfoDetailsList;
import com.placementcell.entities.Users;

@Repository
public interface UserRepository extends JpaRepository<Users,Integer>{
   
	@Query(value="select * from users_info where email =:email ",nativeQuery=true)
	Optional<Users> FindByEmail(@Param("email") String email);
	
	 @Query(value = "SELECT email, first_name as firstName, middle_name as middleName, last_name as lastName, personal_email as personalEmail, gender , contact, course_name as courseName,image,role " +
	            "FROM users_info " +
	            "LEFT JOIN users_details ON users_details.user_id = users_info.id " +
	            "LEFT JOIN course ON users_details.course_course_id = course.course_id " +
	            "WHERE role != 'ROLE_SUPERADMIN'", nativeQuery = true) 
	    List<UsersInfoDetails> findAllUserInfoDetails();
	
	@Query(value="SELECT COUNT(*) AS user_count\r\n"
			+ "FROM users_info\r\n"
			+ "WHERE email =:email",nativeQuery=true)
	int checkEmailExistance(@Param("email") String email);
	
	@Query(value="SELECT email from users_info",nativeQuery = true)
	Set<String> findAllEmails();
	
}
