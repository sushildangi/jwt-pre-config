package com.sushildangi.payload;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@ToString
public class RegisterPayload {
    @NotBlank(message = "Name is required!")
    private String name;
    @NotBlank(message = "Name is required!")
    private String username;
    @NotBlank(message = "Name is required!")
    private String email;
    @NotBlank(message = "Name is required!")
    private String password;
    @NotEmpty(message = "Name is required!")
    private Set<String> roles = new HashSet<>();
}
