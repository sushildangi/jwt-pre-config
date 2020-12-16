package com.sushildangi.security;


import com.sushildangi.exception.GeneralException;
import com.sushildangi.model.Role;
import com.sushildangi.model.User;
import com.sushildangi.payload.LoginPayload;
import com.sushildangi.payload.RegisterPayload;
import com.sushildangi.repository.RoleRepository;
import com.sushildangi.repository.UserRepository;
import com.sushildangi.response.AuthenticationResponse;
import com.sushildangi.service.ErrorCollector;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Set;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final ErrorCollector errorCollector;

    @Override
    public ResponseEntity<?> signUp(RegisterPayload payload, BindingResult result) {
        ResponseEntity<?> responseEntity;
        if (result.hasErrors()) {
            responseEntity = errorCollector.getErrorResponsesEntity(result);
        } else {

            User user = new User();
            Set<Role> roles = user.getRoles();
            user.setUsername(payload.getUsername());
            user.setEmail(payload.getEmail());
            user.setPassword(encodePassword(payload.getPassword()));
            user.setName(payload.getName());
            for (String roleId : payload.getRoles()) {
                Role role = roleRepository.findById(roleId).orElseThrow(() -> new GeneralException("Invalid Role Id :" + roleId));
                roles.add(role);
            }

            userRepository.save(user);
            responseEntity = new ResponseEntity<>("User Created", HttpStatus.CREATED);
        }
        return responseEntity;
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public ResponseEntity<?> login(LoginPayload payload) {
        Authentication authenticate = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(payload.getUsername(),
                        payload.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String authenticationToken = jwtProvider.generateToken(authenticate);
        AuthenticationResponse response = new AuthenticationResponse(authenticationToken, payload.getUsername());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
