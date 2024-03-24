package com.placementcell.repository;

import java.util.HashMap;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.placementcell.entities.Course;

public interface CourseRepository extends JpaRepository<Course,Integer>{

	@Query(value="select course_id from course where course_name=:courseName",nativeQuery = true)
	Integer findByName(@Param("courseName") String courseName);
	
	@Query(value="select course_name from course",nativeQuery = true)
	List<String> getAllCourses();
}
