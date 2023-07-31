FROM maven:3.9.3-eclipse-temurin-17-alpine@sha256:38e7fe344f85a25e88fb15c5588360e439dab00cd4abdb72c11670543075965b AS builder
COPY pom.xml .
RUN mvn dependency:go-offline

COPY ./src ./src
RUN mvn -B clean package

RUN cp target/*.jar application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM eclipse-temurin:17.0.7_7-jre-alpine@sha256:dd8238c151293ae6a7c22898ef2f0df2af8a05786aef73ccd3248e73765969ed AS jre-builder
COPY --from=builder ./dependencies/ ./
COPY --from=builder ./spring-boot-loader/ ./
COPY --from=builder ./snapshot-dependencies/ ./
COPY --from=builder ./application/ ./
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "org.springframework.boot.loader.JarLauncher"]