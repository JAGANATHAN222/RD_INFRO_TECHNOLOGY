#
# Multi-stage build: build jar with Maven, run on slim JRE.
#

FROM eclipse-temurin:25-jdk AS build
WORKDIR /workspace

# Copy Maven wrapper + pom first for dependency caching
COPY mvnw mvnw
COPY mvnw.cmd mvnw.cmd
COPY .mvn .mvn
COPY pom.xml pom.xml

# Windows checkouts may include CRLF and/or drop the executable bit
RUN sed -i 's/\r$//' mvnw && chmod +x mvnw

RUN ./mvnw -q -DskipTests dependency:go-offline

# Now copy sources and build
COPY src src
RUN ./mvnw -q -DskipTests package


FROM eclipse-temurin:25-jre
WORKDIR /app

# Put logs on a writable path (can be bind-mounted)
RUN mkdir -p /app/logs

COPY --from=build /workspace/target/*.jar /app/app.jar

EXPOSE 8080

ENV SPRING_PROFILES_ACTIVE=default

ENTRYPOINT ["java","-jar","/app/app.jar"]

