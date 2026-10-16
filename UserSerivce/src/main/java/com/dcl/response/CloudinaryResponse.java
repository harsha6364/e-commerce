package com.dcl.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class CloudinaryResponse {

	private String imageUrl;
	
	private String publicId;
}
