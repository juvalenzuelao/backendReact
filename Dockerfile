FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copiar Maven wrapper y pom.xml
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

RUN chmod +x mvnw

# Descargar dependencias
RUN ./mvnw dependency:go-offline

# Copiar código fuente
COPY src ./src

# Compilar el proyecto
RUN ./mvnw clean package -DskipTests

EXPOSE 8080

# Ejecutar la aplicación con wildcard funcionando
CMD ["sh", "-c", "java -jar target/*.jar"]
