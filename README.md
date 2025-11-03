# 🚀 Redis-Service (Spring Boot 3 + Java 21 + Redis + Docker)

A production-ready **Spring Boot 3.2.11** application built with **Java 21**, integrated with **Redis** for caching and key-value storage.  
The project is **Docker-ready**, lightweight, and follows clean configuration principles.

---

## 📦 Features

- ✅ Spring Boot 3.2.x (latest stable)
- ✅ Java 21 (modern JVM performance)
- ✅ Redis with Lettuce client
- ✅ Spring Cache abstraction
- ✅ REST API for generic key-value operations
- ✅ Swagger UI (OpenAPI 3)
- ✅ Dockerfile for containerization
- ✅ Maven build with dependency caching

---

## 🧰 Tech Stack

> **Language:** Java 21  
> **Framework:** Spring Boot 3.2.11  
> **Cache Store:** Redis (Lettuce)  
> **Build Tool:** Maven 3.9+  
> **Containerization:** Docker  
> **Documentation:** SpringDoc OpenAPI / Swagger UI  

## ⚙️ Project Structure

```
redis-service/
│
├── src/
│   ├── main/
│   │   ├── java/com/smart/redis/
│   │   │   ├── RedisServiceApplication.java
│   │   │   ├── config/
│   │   │   │   └── RedisConfig.java
│   │   │   ├── controller/
│   │   │   │   └── GenericRedisController.java
│   │   │   └── service/
│   │   │       └── GenericRedisService.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/... (optional)
│
├── pom.xml
├── Dockerfile
└── README.md
```

---

## 🧩 Configuration

### `application.properties`

```properties
server.port=8080
spring.data.redis.host=redis
spring.data.redis.port=6379
spring.data.redis.password=SuperStrongPassw0rd!
spring.cache.type=redis
app.cache.ttl-seconds=600
app.cache.prefix=app:
```

---

## ⚡ RedisConfig.java

```java
package com.smart.redis.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    private static final Logger log = LoggerFactory.getLogger(RedisConfig.class);

    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Value("${spring.data.redis.port}")
    private int redisPort;

    @Value("${spring.data.redis.password:}")
    private String redisPassword;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder().build();
        return new LettuceConnectionFactory(redisHost, redisPort);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    public CommandLineRunner verifyRedis(RedisConnectionFactory factory) {
        return args -> {
            try (RedisConnection conn = factory.getConnection()) {
                String pong = conn.ping();
                if ("PONG".equalsIgnoreCase(pong)) {
                    log.info("Redis connected successfully at {}:{}", redisHost, redisPort);
                } else {
                    log.warn("Unexpected Redis ping response: {}", pong);
                }
            } catch (Exception e) {
                log.error("Redis connection failed: {}", e.getMessage());
            }
        };
    }
}
```
---

## 🐳 Dockerfile

```dockerfile
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /workspace
COPY pom.xml .
RUN mvn -q -DskipTests dependency:go-offline
COPY src ./src
RUN mvn -q -DskipTests clean package

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /workspace/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
```

---

## 🔥 Run Locally

```bash
mvn clean spring-boot:run
```

or

```bash
mvn clean package -DskipTests
java -jar target/redis-service-0.0.1.jar
```

---

## 🐳 Run in Docker

```bash
docker build -t redis-service .
docker run -d -p 8080:8080 redis-service
```

---

## 📘 Swagger UI

Open browser: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

## ❤️ Author

**Nehru** — Backend Developer (Java + Spring Boot + Redis)

