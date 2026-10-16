package com.dcl.controller;


import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dcl.dto.ProfileDto;
import com.dcl.request.UpdateProfileRequest;
import com.dcl.response.ApiResponse;
import com.dcl.service.ProfileService;

@RestController
public class ProfileController {

	@Autowired
	private ProfileService pservice;
	
	@PutMapping(value="/update/{profileId}", consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> updateProfile(
			@PathVariable Integer profileId,
			@RequestParam String firstName,
			@RequestParam String lastName,
			@RequestParam String phone,
			@RequestParam LocalDate dob,
			@RequestPart("image") MultipartFile image
			){
		
		UpdateProfileRequest request = new UpdateProfileRequest();
		request.setFirstName(firstName);
		request.setLastName(lastName);
		request.setPhone(phone);
		request.setDob(dob);
		ProfileDto pdto = pservice.updateProfile(profileId, request, image);
		return ResponseEntity.ok(new ApiResponse<>("Profile updated successfully!",pdto,HttpStatus.OK));
		
	}
	
}
