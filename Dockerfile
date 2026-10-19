# 1. Imagen base con Java (ajusta la versión 17 si usas otra como Java 21 o 11)
FROM eclipse-temurin:17-jdk-alpine

# 2. Carpeta de trabajo dentro del contenedor
WORKDIR /app

# 3. Copiamos el archivo .jar generado por Maven al contenedor
COPY target/*.jar app.jar

# 4. Puerto que expondrá la aplicación de Spring Boot
EXPOSE 8081

# 5. Comando para arrancar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]