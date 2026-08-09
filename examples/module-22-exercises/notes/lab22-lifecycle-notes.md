# Lab 22 — Bean Lifecycle Callbacks

## Lifecycle order
Create → Inject → @PostConstruct → Use → @PreDestroy

## @PostConstruct purpose
log init once

## @PreDestroy purpose
log destroy on context close

## What not to do in init
Do not create CUS-1001 inside @PostConstruct for every request.

## Singleton scope
one shared service instance.

## Scope
Pre-lab only.