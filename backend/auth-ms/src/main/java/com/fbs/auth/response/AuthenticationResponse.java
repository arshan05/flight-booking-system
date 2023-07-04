package com.fbs.auth.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
	private String name;
	private List<String> role;
	private String jwtToken;
	private boolean isValid;

}
