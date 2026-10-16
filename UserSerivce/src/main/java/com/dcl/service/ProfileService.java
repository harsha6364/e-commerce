package com.dcl.service;

import org.springframework.web.multipart.MultipartFile;

import com.dcl.dto.ProfileDto;
import com.dcl.entity.Profile;
import com.dcl.request.UpdateProfileRequest;

public interface ProfileService {

	public ProfileDto addProfile(Profile profile);
	
	public ProfileDto getProfileById(Integer profileId);
	
	public ProfileDto updateProfile(Integer profileId, UpdateProfileRequest request, MultipartFile image);
}
