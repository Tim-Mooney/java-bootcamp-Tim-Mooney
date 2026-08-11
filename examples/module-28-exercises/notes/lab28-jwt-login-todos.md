# Lab 28 — JWT Login TODOs

## Login path + body
POST /api/auth/login {username,password} → {accessToken, tokenType}

## Token response
JwtService issueToken / parseSubject / parseRole (lab stub OK)

## Bearer header form
Authorization: Bearer <accessToken>

## Lab users/roles
agent1 (AGENT), admin1 (ADMIN)

## Secret handling
env JWT_SECRET → northstar.security.jwt-secret (placeholder in .env.example)

## Scope
Pre-lab only. No real secrets.