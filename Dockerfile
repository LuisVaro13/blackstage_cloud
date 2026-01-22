# Usamos como punto de partida una máquina virtual con JDK 21 y Maven instalados
FROM maven:3.9.8-eclipse-temurin-21

# Creamos una carpeta para el código fuente
RUN mkdir -p /usr/src/proyecto-final-asi

# Establecemos la nueva carpeta como directorio de trabajo
WORKDIR /usr/src/proyecto-final-asi

# Copiamos el código fuente del proyecto dentro de la máquina virtual
COPY . .

# Ejecutamos Maven para compilar el proyecto y generar un fichero jar ejecutable
RUN mvn clean package

# Inicia el proyecto ejecutando el fichero jar compilado
# REEMPLAZAR NOMBRE_ARTIFACTID por el nombre del Artifactory en el pom.xml
CMD java -jar target/blackstage-0.0.1-SNAPSHOT-jar-with-dependencies.jar
