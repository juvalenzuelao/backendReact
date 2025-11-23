# Usar Java 21
FROM eclipse-temurin:21-jdk-jammy

# Carpeta de trabajo dentro del contenedor
WORKDIR /app

# Copiar todos los archivos del proyecto al contenedor
COPY . .

# Compilar el proyecto usando Maven Wrapper (sin tests para acelerar)
RUN ./mvnw clean package -DskipTests

# Exponer el puerto que usa Spring Boot
EXPOSE 8080

# Ejecutar la app con el JAR generado
CMD ["java", "-jar", "target/backend-0.0.1-SNAPSHOT.jar"]
