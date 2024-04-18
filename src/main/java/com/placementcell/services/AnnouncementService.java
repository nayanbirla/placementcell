package com.placementcell.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.placementcell.dto.AnnouncementDTO;
import com.placementcell.dto.AnnouncementResponse;
import com.placementcell.entities.Announcement;
import com.placementcell.entities.Users;
import com.placementcell.exceptions.InvalidExpiryDateException;
import com.placementcell.repository.AnnouncementRepository;
import com.placementcell.repository.UserRepository;
import com.placementcell.request.AnnouncementRequest;
import com.placementcell.utility.Message;

@Service
public class AnnouncementService {

	@Autowired
	private AnnouncementRepository announcementRepository;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private UserRepository userRepository;

	public AnnouncementResponse addAnnouncement(AnnouncementRequest announcementRequest, String token)
			throws InvalidExpiryDateException {
		if (announcementRequest.getExpiryDate().isBefore(LocalDate.now())) {
			throw new InvalidExpiryDateException("Invalid Expiry Date");
		}
		Announcement announcement = new Announcement();
		announcement.setTitle(announcementRequest.getTitle());
		announcement.setContent(announcementRequest.getContent());
		announcement.setDatePosted(LocalDate.now());
		announcement.setDateToRemove(announcementRequest.getExpiryDate());
		announcement.setTimeToRemove(announcementRequest.getExpiryTime());
		String username = jwtService.extractUserName(token);
		Users user = userRepository.FindByEmail(username).get();
		announcement.setUsers(user);
		Announcement announcement2 = announcementRepository.save(announcement);
		AnnouncementResponse announcementResponse = new AnnouncementResponse();
		announcementResponse.setContent(announcement2.getContent());
		announcementResponse.setTitle(announcement2.getTitle());
		return announcementResponse;
	}

	public AnnouncementResponse updateAnnouncement(AnnouncementRequest announcementRequest)
			throws InvalidExpiryDateException {
		if (announcementRequest.getExpiryDate().isBefore(LocalDate.now())) {
			throw new InvalidExpiryDateException("Invalid Expiry Date");
		}
		Announcement announcement = announcementRepository.findById(announcementRequest.getId()).get();
		announcement.setTitle(announcementRequest.getTitle());
		announcement.setContent(announcementRequest.getContent());
		announcement.setDatePosted(announcement.getDatePosted());
		announcement.setDateToRemove(announcementRequest.getExpiryDate());
        announcement.setTimeToRemove(announcementRequest.getExpiryTime());   
		Announcement announcement2 = announcementRepository.save(announcement);

		AnnouncementResponse announcementResponse = new AnnouncementResponse();
		announcementResponse.setTitle(announcement2.getTitle());
		announcementResponse.setContent(announcement2.getContent());

		return announcementResponse;
	}

	public List<AnnouncementDTO> getAllActiveAnnouncement() {
		List<AnnouncementDTO> announcements = announcementRepository.findAllActiveAnnouncement();
		return announcements;
	}

	public List<Announcement> getAllAnnouncement() {
		List<Announcement> announcements = announcementRepository.findAll();
		return announcements;
	}

	public Message delete(int id) {
		try {
			announcementRepository.deleteById(id);
			return new Message("Announcement Deleted Successfully");
		} catch (Exception exception) {
			return new Message("Announcement Not Exist");
		}

	}

}
