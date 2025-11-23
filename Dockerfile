# Usa Java 21
FROM eclipse-temurin:21-jdk-alpine

# Carpeta de trabajo dentro del contenedor
WORKDIR /app

# Copia Maven wrapper y pom.xml
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Permisos al wrapper
RUN chmod +x mvnw

# Descarga dependencias offline
RUN ./mvnw dependency:go-offline

# Copia el backend completo
COPY src ./src

# Construye el jar
RUN ./mvnw clean package -DskipTests

# Copia el jar generado a un nombre fijo
RUN cp target/*.jar app.jar

# Puerto expuesto
EXPOSE 8080

# Comando para ejecutar Spring Boot
CMD ["java", "-jar", "app.jar"]
