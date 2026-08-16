package com.northstar.crm.controller;

import com.northstar.crm.security.JwtService;
import com.northstar.crm.security.CrmUserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  private final JwtService jwtService;
  private final CrmUserDetailsService crmService;
  private final PasswordEncoder passwordEncoder;

  public AuthController(JwtService jwtService, CrmUserDetailsService crmService, PasswordEncoder passwordEncoder) {
    this.jwtService = jwtService;
    this.crmService = crmService;
    this.passwordEncoder = passwordEncoder;
  }

  @PostMapping("/login")
  public Map<String, String> login(@RequestBody Map<String, String> body) {
    String username = body.getOrDefault("username", "");
    UserDetails user = crmService.loadUserByUsername(body.get("username"));
    if (!passwordEncoder.matches(body.get("password"), user.getPassword())) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Bad credentials");
    }
    String token = jwtService.issueToken(username, username.startsWith("admin") ? "ADMIN" : "AGENT");
    return Map.of("accessToken", token, "tokenType", "Bearer");
  }
}
