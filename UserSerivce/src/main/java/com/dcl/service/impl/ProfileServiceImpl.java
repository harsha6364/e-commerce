package com.dcl.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dcl.dto.ProfileDto;
import com.dcl.entity.Profile;
import com.dcl.exception.AppException;
import com.dcl.repo.ProfileRepo;
import com.dcl.request.UpdateProfileRequest;
import com.dcl.response.CloudinaryResponse;
import com.dcl.service.CloudinaryService;
import com.dcl.service.ProfileService;

@Service
public class ProfileServiceImpl implements ProfileService {
	
	@Autowired
	private ProfileRepo prepo;

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private CloudinaryService cservice;
	
	@Override
	public ProfileDto addProfile(Profile profile) {
		profile = prepo.save(profile);
		return mapper.map(profile, ProfileDto.class);
	}

	@Override
	public ProfileDto getProfileById(Integer profileId) {
		Profile profile = prepo.findByProfileId(profileId).orElseThrow(()->new AppException("Profile not found!", HttpStatus.NOT_FOUND));
		return  mapper.map(profile, ProfileDto.class);
	}

	@Override
	public ProfileDto updateProfile(Integer profileId, UpdateProfileRequest request, MultipartFile image) {
		Profile existingProfile = prepo.findByProfileId(profileId).orElseThrow(()-> new AppException("Profile not found!", HttpStatus.CONFLICT));
		mapper.map(request, existingProfile);
		if(image != null && !image.isEmpty()) {
			
			if(existingProfile.getImagePublicId() != null) {
				cservice.deleteImage(existingProfile.getImagePublicId());
			}
			CloudinaryResponse response = cservice.uploadImage(image);
			existingProfile.setImagePublicId(response.getPublicId());
			existingProfile.setImageUrl(response.getImageUrl());
		}
		existingProfile = prepo.save(existingProfile);
		return mapper.map(existingProfile, ProfileDto.class);
	}

}
