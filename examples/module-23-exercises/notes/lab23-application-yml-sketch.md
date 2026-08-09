# Lab 23 — application.yml Sketch

## Base keys
application name: lab23-crm
server.port: 8080
management exposure:  include: health

## dev teaser
spring.profiles.active: dev

## prod teaser
spring.profiles.active: prod

## Scope
Pre-lab only. No real passwords.

```yaml
spring:
  application:
    name: lab23-crm
server:
  port: 8080
management:
  endpoints:
    web:
      exposure:
        include: health
```