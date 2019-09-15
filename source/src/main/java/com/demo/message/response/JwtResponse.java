package com.demo.message.response;

public class JwtResponse{
    public final String headerKey = "Authorization";
    public final String tokenType = "Bearer";
    private String accessToken;
	public JwtResponse(String accessToken) {
		super();
		this.accessToken = accessToken;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
}