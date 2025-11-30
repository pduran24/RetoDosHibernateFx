# Gestión de Películas - JavaFX & Hibernate

Aplicación de escritorio desarrollada en Java para la gestión de colecciones de películas. Este proyecto implementa una arquitectura **MVC** (Modelo-Vista-Controlador) utilizando **JavaFX** para la interfaz gráfica y **Hibernate (JPA)** para la persistencia de datos en **MySQL**.

El sistema cuenta con un control de acceso basado en roles, diferenciando entre funcionalidades de Administrador y Usuario estándar.

## Características Principales

### Rol Usuario (Coleccionista)
* **Visualizar Colección:** Ver una lista de las copias de películas que posee.
* **Gestión de Copias:** Añadir nuevas copias a su colección personal a partir del catálogo global.
* **Eliminar Copias:** Dar de baja una copia específica sin afectar a la película original ni a otros usuarios.
* **Detalle:** Consultar la ficha técnica de sus películas.

### 🛡Rol Administrador (Gestor)
* **Gestión del Catálogo:** Visualizar todas las películas registradas en la base de datos.
* **Añadir Películas:** Registrar nuevos títulos en el sistema global para que los usuarios puedan adquirir copias.
* **Eliminar Películas:** Borrar películas del sistema (incluyendo eliminación en cascada de todas las copias asociadas).