package com.example.demo.controller;

import com.example.demo.entity.User; import com.example.demo.repository.UserRepository; import com.example.demo.security.JwtService; import jakarta.validation.Valid; import lombok.*; import org.springframework.http.*; import org.springframework.security.authentication.*; import org.springframework.security.core.userdetails.UserDetails; import org.springframework.security.crypto.password.PasswordEncoder; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/auth") public class AuthController {
 private final UserRepository users; private final PasswordEncoder encoder; private final AuthenticationManager auth; private final JwtService jwt;
 public AuthController(UserRepository users,PasswordEncoder encoder,AuthenticationManager auth,JwtService jwt){this.users=users;this.encoder=encoder;this.auth=auth;this.jwt=jwt;}
 @PostMapping("/register") public ResponseEntity<TokenResponse> register(@Valid @RequestBody RegisterRequest r){if(users.existsByEmail(r.email())) return ResponseEntity.status(409).build(); User u=new User();u.setEmail(r.email());u.setPassword(encoder.encode(r.password()));users.save(u);return ResponseEntity.status(201).body(token(u.getEmail(),u.getPassword()));}
 @PostMapping("/login") public TokenResponse login(@RequestBody RegisterRequest r){auth.authenticate(new UsernamePasswordAuthenticationToken(r.email(),r.password()));var u=users.findByEmail(r.email()).orElseThrow();return token(u.getEmail(),u.getPassword());}
 private TokenResponse token(String email,String password){return new TokenResponse(jwt.generate(org.springframework.security.core.userdetails.User.withUsername(email).password(password).roles("USER").build()));}
 public record RegisterRequest(@jakarta.validation.constraints.Email @jakarta.validation.constraints.NotBlank String email,@jakarta.validation.constraints.Size(min=8) String password){}
 public record TokenResponse(String token){}
}
