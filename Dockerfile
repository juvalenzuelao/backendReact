FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copiar Maven wrapper y pom.xml desde la raíz del proyecto
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

RUN chmod +x mvnw

RUN ./mvnw dependency:go-offline

# Copiar el código fuente
COPY src ./src

RUN ./mvnw clean package -DskipTests

EXPOSE 8080

# IMPORTANTE: Render usará el artefacto generado en target/
CMD ["java", "-jar", "target/*.jar"]
