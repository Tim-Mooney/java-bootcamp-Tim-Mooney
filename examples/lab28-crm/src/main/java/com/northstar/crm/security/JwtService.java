package com.northstar.crm.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
  private final String secret;

  public JwtService(@Value("${northstar.security.jwt-secret}") String secret) {
    this.secret = secret;
  }

  public String issueToken(String subject, String role) {
    String sig = Integer.toHexString(secret.hashCode());
    String token = "lab." + subject + "." + role + "." + sig;
    return token;
  }

  public String parseSubject(String token) {
    if (token == null || !token.startsWith("lab.")) {
      throw new IllegalArgumentException("Invalid token");
    }

    String[] parts = token.split("\\.");
    if (parts.length != 4) {
      throw new IllegalArgumentException("Invalid token");
    }

    String subject = parts[1];
    String role = parts[2];
    String sig = parts[3];

    String expectedSig = Integer.toHexString(secret.hashCode());
    if (!expectedSig.equals(sig)) {
      throw new IllegalArgumentException("Invalid token signature");
    }

    return subject;
  }

  public String parseRole(String token) {
    if (token == null || !token.startsWith("lab.")) {
      throw new IllegalArgumentException("Invalid token");
    }

    String[] parts = token.split("\\.");
    if (parts.length != 4) {
      throw new IllegalArgumentException("Invalid token");
    }

    String subject = parts[1];
    String role = parts[2];
    String sig = parts[3];

    String expectedSig = Integer.toHexString(secret.hashCode());
    if (!expectedSig.equals(sig)) {
      throw new IllegalArgumentException("Invalid token signature");
    }
    if(!role.equals("AGENT") && !role.equals("ADMIN")){
      throw new IllegalArgumentException("Invalid role");
    }
    return role;
  }
}
