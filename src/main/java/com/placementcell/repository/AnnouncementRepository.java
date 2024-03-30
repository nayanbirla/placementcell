package com.placementcell.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.placementcell.entities.Announcement;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement,Integer> {

	@Query(value="SELECT title,content\r\n"
			+ "FROM announcement\r\n"
			+ "WHERE CURRENT_DATE < date_to_remove order by date_posted DESC",nativeQuery=true)
	List<Object[]> findAllActiveAnnouncement(); 
}
