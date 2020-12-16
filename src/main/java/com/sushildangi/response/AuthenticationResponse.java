package com.sushildangi.response;

import lombok.Data;

@Data
public class AuthenticationResponse {
    private String authenticationToken;
    private String username;
    private String tokenType = "Bearer";

    public AuthenticationResponse(String authenticationToken, String username) {
        this.authenticationToken = authenticationToken;
        this.username = username;
    }
}