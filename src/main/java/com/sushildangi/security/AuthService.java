package com.sushildangi.security;


import com.sushildangi.payload.LoginPayload;
import com.sushildangi.payload.RegisterPayload;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface AuthService {
    ResponseEntity<?> signUp(RegisterPayload payload, BindingResult result);

    ResponseEntity<?> login(LoginPayload payload);
}
