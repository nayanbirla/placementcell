package com.placementcell.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.placementcell.dto.UserInfoDetailsList;
import com.placementcell.entities.Users;

@Repository
public interface UserRepository extends JpaRepository<Users,Integer>{
   
	@Query(value="select * from users_info where email =:email ",nativeQuery=true)
	Optional<Users> FindByEmail(@Param("email") String email);
	
	@Query(value="select email,first_name,middle_name,last_name,personal_email,gender,contact,course_name from users_info left join\r\n"
			+ "users_details on users_details.user_id = users_info.id left join course on users_details.course_course_id = course.course_id\r\n"
			+ "where role!='ROLE_SUPERADMIN';",nativeQuery = true)
	List<Object[]> findAllStudentsList();
	
	
}
