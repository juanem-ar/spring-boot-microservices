# spring-boot-microservices

TRUST YOURSELF 🚀✨

# Technologies
- Java 17
- Spring Boot
- Spring IoC
- Spring Data e Hibernate
- H2
- Spring Cloud
- Servidor Eureka Netflix
- Eureka Client
- LoadBalancer
- RestTemplate (sync)
- Feign (sync)
- Gateway Zuul
- Spring Cloud Server Config
- Spring Security OAuth2
- JWT
- Lombok
- MapStruct
- Docker, Docker File, Docker Compose
- AWS

# Architecture

![img.png](assets/img.png)

# Postman

# config server

GET - http://localhost:8888/service-items/default / dev / prod

# Actualizar valores de configuracion

POST - http://localhost:{PUERTO A ACTUALIZAR}/actuator/refresh
GET - http://localhost:8080/api/items/get-config para validar los cambios

# 