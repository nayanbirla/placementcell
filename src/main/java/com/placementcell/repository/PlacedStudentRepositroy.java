package com.placementcell.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.placementcell.entities.PlacedStudent;

public interface PlacedStudentRepositroy extends JpaRepository<PlacedStudent,Integer> {

	
}
