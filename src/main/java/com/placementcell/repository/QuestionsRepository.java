package com.placementcell.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.placementcell.entities.Questions;

@Repository
public interface QuestionsRepository extends JpaRepository<Questions,Integer> {

}
