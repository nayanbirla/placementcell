package com.placementcell.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.placementcell.dto.StudyMaterialResponse;
import com.placementcell.entities.Links;
import com.placementcell.entities.StudyMaterial;
import com.placementcell.entities.Users;
import com.placementcell.repository.StudyMaterialRepository;
import com.placementcell.repository.UserRepository;
import com.placementcell.request.StudyMaterialRequest;
import com.placementcell.utility.Message;

@Service
public class StudyMaterialService {

	@Autowired
	private StudyMaterialRepository studyMaterialRepository;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private UserRepository userRepository;

	public StudyMaterialResponse addStudyMaterial(StudyMaterialRequest studyMaterialRequest, String token) {
		String username = jwtService.extractUserName(token);
		Users user = userRepository.FindByEmail(username).get();
		StudyMaterial studyMaterial = new StudyMaterial();
		studyMaterial.setSubjectName(studyMaterialRequest.getSubjectName());
		studyMaterial.setTopicName(studyMaterialRequest.getTopicName());
		studyMaterial.setDescription(studyMaterialRequest.getDescription());
//		studyMaterial.setLinks(studyMaterialRequest.getLinks());
		List<Links> links = new ArrayList<>();
		List<String> studyMaterialLinks = studyMaterialRequest.getLinks();
		for (String link : studyMaterialLinks) {
			Links newLink = new Links();
			newLink.setLink(link);
		}
		studyMaterial.setLinks(links);
		studyMaterial.setSuperAdminId(user);
		studyMaterial = studyMaterialRepository.save(studyMaterial);

		StudyMaterialResponse studyMaterialResponse = new StudyMaterialResponse();

		studyMaterialResponse.setTopicName(studyMaterial.getTopicName());
		studyMaterialResponse.setSubjectName(studyMaterial.getSubjectName());
		studyMaterialResponse.setDescription(studyMaterial.getDescription());
		studyMaterialResponse.setLinks(studyMaterialLinks);
		String name = "" + user.getUsersDetails().getFirstName() + " " + user.getUsersDetails().getLastName();
		studyMaterialResponse.setAdderName(name);

		return studyMaterialResponse;
	}

	public StudyMaterialResponse updateStudyMaterial(StudyMaterialRequest studyMaterialRequest) {
		StudyMaterial studyMaterial = studyMaterialRepository.findById(studyMaterialRequest.getId()).get();
		studyMaterial.setSubjectName(studyMaterialRequest.getSubjectName());
		studyMaterial.setTopicName(studyMaterialRequest.getTopicName());
		studyMaterial.setDescription(studyMaterialRequest.getDescription());
		List<Links> links = new ArrayList<>();
		List<String> studyMaterialLinks = studyMaterialRequest.getLinks();
		for (String link : studyMaterialLinks) {
			Links newLink = new Links();
			newLink.setLink(link);
		}
		studyMaterial.setLinks(links);
		studyMaterial = studyMaterialRepository.save(studyMaterial);
		StudyMaterialResponse studyMaterialResponse = new StudyMaterialResponse();

		studyMaterialResponse.setSubjectName(studyMaterial.getSubjectName());
		studyMaterialResponse.setTopicName(studyMaterial.getTopicName());
		studyMaterialResponse.setDescription(studyMaterial.getDescription());
		studyMaterialResponse.setLinks(studyMaterialLinks);
		String name = "" + studyMaterial.getSuperAdminId().getUsersDetails().getFirstName() + " "
				+ studyMaterial.getSuperAdminId().getUsersDetails().getLastName();
		studyMaterialResponse.setAdderName(name);
		return studyMaterialResponse;

	}

	public List<StudyMaterialResponse> getAll() {
		List<StudyMaterial> studyMaterials = studyMaterialRepository.findAll();

		List<StudyMaterialResponse> studyMaterialResponses = new ArrayList<>();
		for (StudyMaterial studyMaterial : studyMaterials) {
			StudyMaterialResponse studyMaterialResponse = new StudyMaterialResponse();
			studyMaterialResponse.setSubjectName(studyMaterial.getSubjectName());
			studyMaterialResponse.setTopicName(studyMaterial.getTopicName());
			studyMaterialResponse.setDescription(studyMaterial.getDescription());
			List<Links> linksList = studyMaterial.getLinks(); // Assuming studyMaterial.getLinks() returns a List<Links>

			List<String> linkStrings = linksList.stream().map(Links::getLink).collect(Collectors.toList());
			studyMaterialResponse.setLinks(linkStrings);
		}
		return studyMaterialResponses;
	}
	
	public Message delete(int id)
	{
		try {
			studyMaterialRepository.deleteById(id);
			return new Message("Study Material has been deleted");
		}catch(Exception ee)
		{
			return new Message("Study Material does not exist");
		}
	}
}
