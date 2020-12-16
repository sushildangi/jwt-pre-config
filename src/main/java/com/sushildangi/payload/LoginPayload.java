package com.sushildangi.payload;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@ToString
public class LoginPayload {
    @NotBlank(message = "Username is required!")
    private String username;
    @NotBlank(message = "Password is required!")
    private String password;
}
