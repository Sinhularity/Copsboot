# Copsboot - Práctica 2

Se trata de un proyecto sencillo que se basa en la tecnología de Spring Boot y sus características más importantes son:
* Spring Boot como el framework principal para aproverchar las herramientas de desarrollo
* Utilizamos Maven (pom.xml) para gestionar las dependencias y la construcción del proyecto
* **Keycloak** para proteger las rutas, es decir sirve como la autenticación y su autorización
* **Docker** para contenerizar la aplicación y permitir su ejecución desde cualquier lugar

## ¿Cómo debemos ejecutar este proyecto?

### 1. Clonamos el repositorio
git clone https://github.com/Sinhularity/Copsboot.git

cd Copsboot

### 2. Añadimos las depencias con Maven
mvn clean install

### 3. Ejecuta el dockerfile ```(Necesario tener el docker desktop ejecutandose)```
docker-compose up

### 4. ¡¡¡Listo!!!
Ahora se encuentra ejecutandose Keycloak en el puerto 8180 y el servidor en 8080 pero no es posible ver nada ya que Keycloak no envia por defecto
por defecto el token que necesita JWT.io para permitir el acceso al sistema, no obstante debería ser posible utilizar Postman para seguir
ejecutando las pruebas.
