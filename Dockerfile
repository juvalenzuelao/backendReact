FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copiar el wrapper de Maven y pom.xml desde la carpeta 'backend'
COPY backend/.mvn/ .mvn
COPY backend/mvnw backend/pom.xml ./

RUN chmod +x mvnw

RUN ./mvnw dependency:go-offline

# Copiar el código fuente
COPY backend/src ./src

RUN ./mvnw clean package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/backend-0.0.1-SNAPSHOT.jar"]
