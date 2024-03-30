package com.placementcell.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.placementcell.dto.AnnouncementResponse;
import com.placementcell.entities.Announcement;
import com.placementcell.entities.Users;
import com.placementcell.repository.AnnouncementRepository;
import com.placementcell.repository.UserRepository;
import com.placementcell.request.AnnouncementRequest;

@Service
public class AnnouncementService {

	@Autowired
	private AnnouncementRepository announcementRepository;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private UserRepository userRepository;

	public AnnouncementResponse addAnnouncement(AnnouncementRequest announcementRequest, String token) {
		Announcement announcement = new Announcement();
		announcement.setTitle(announcementRequest.getTitle());
		announcement.setContent(announcementRequest.getContent());
		announcement.setDatePosted(announcementRequest.getPostedDate());
		announcement.setDateToRemove(announcementRequest.getExpiryDate());
		String username = jwtService.extractUserName(token);
		Users user = userRepository.FindByEmail(username).get();
		announcement.setUsers(user);
		Announcement announcement2 = announcementRepository.save(announcement);
		AnnouncementResponse announcementResponse = new AnnouncementResponse();
		announcementResponse.setContent(announcement2.getContent());
		announcementResponse.setTitle(announcement2.getTitle());
		return announcementResponse;
	}

	public AnnouncementResponse updateAnnouncement(AnnouncementRequest announcementRequest) {
		Announcement announcement = announcementRepository.findById(announcementRequest.getId()).get();
		announcement.setTitle(announcementRequest.getTitle());
		announcement.setContent(announcementRequest.getContent());
		announcement.setDatePosted(announcementRequest.getPostedDate());
		announcement.setDateToRemove(announcementRequest.getExpiryDate());

		Announcement announcement2 = announcementRepository.save(announcement);

		AnnouncementResponse announcementResponse = new AnnouncementResponse();
		announcementResponse.setTitle(announcement2.getTitle());
		announcementResponse.setContent(announcement2.getContent());

		return announcementResponse;
	}
	
	public List<AnnouncementResponse> getAllActiveAnnouncement()
	{
		List<Object[]> announcements=announcementRepository.findAllActiveAnnouncement();
		List<AnnouncementResponse> announcementResponses=new ArrayList<>();
		for(Object[] announcement: announcements)
		{
			AnnouncementResponse announcementResponse=new AnnouncementResponse();
			announcementResponse.setTitle(announcement[0].toString());
			announcementResponse.setContent(announcement[1].toString());
			announcementResponses.add(announcementResponse);
		}
		return announcementResponses;
	}
	
	public List<Announcement> getAllAnnouncement()
	{
		List<Announcement> announcements=announcementRepository.findAll();
		return announcements;
	}

}
