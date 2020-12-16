package com.sushildangi.resource;

import com.sushildangi.payload.LoginPayload;
import com.sushildangi.payload.RegisterPayload;
import com.sushildangi.security.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
@CrossOrigin("http://localhost:3000")
public class AuthResource {
    private final AuthService authService;

    @PostMapping("sign-up")
    public ResponseEntity<?> signUp(@Valid @RequestBody RegisterPayload payload,
                                    BindingResult result) {
        return authService.signUp(payload, result);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginPayload payload) {
        return authService.login(payload);
    }

}
