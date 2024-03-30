package com.placementcell.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.placementcell.entities.StudyMaterial;

@Repository
public interface StudyMaterialRepository extends JpaRepository<StudyMaterial,Integer> {

}
