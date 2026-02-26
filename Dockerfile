# 1단계: 빌드 환경 (Java 21)
FROM eclipse-temurin:21-jdk AS builder
WORKDIR /app

# Gradle 래퍼와 설정 파일 복사 (의존성 캐시 활용)
COPY gradlew .
COPY gradle gradle
COPY build.gradle settings.gradle ./

# 소스 코드 복사 및 빌드 (테스트 제외)
COPY src src
RUN ./gradlew clean build -x test --no-daemon

# 2단계: 실행 환경 (Java 21 JRE)
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# 빌드된 jar 파일 복사
COPY --from=builder /app/build/libs/*-SNAPSHOT.jar app.jar

# 포트 노출
EXPOSE 8080

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "app.jar"]