# Gestión de Películas - JavaFX & Hibernate


Pantalla de Login: Permitir a los usuarios iniciar sesión con un nombre
de usuario y contraseña.
* Visualizar Copias de Películas: Una vez que el usuario inicie sesión,
mostrar un listado de copias de películas asociadas a ese usuario. Un
usuario no puede ver las copias de películas del resto de usuarios.
* Permitir Múltiples Copias: Un usuario puede tener varias copias de la
misma película. Cada copia debe ser visible en el listado.
* La base de datos de las películas es común para todos los usuarios (no
las copias).
* Al pulsar sobre una copia, se muestra una pantalla con el detalle de la 
copia y película asociada.



Historias de Usuario Adicionales:
* Como usuario, quiero poder eliminar una copia de una película en
la base de datos, para mantener actualizada la disponibilidad de
las copias. No se debe permitir eliminar la película completa, solo
una copia específica.
* Como usuario, quiero poder añadir una nueva copia de una
película que ya existe en la base de datos, para reflejar
correctamente mi colección.
* Como usuario administrador, quiero poder añadir una nueva
película a la base de datos, para que esté disponible para otros
usuarios de la aplicación, facilitando así el crecimiento del
catálogo.
* Como usuario, quiero poder modificar los datos de una copia
(estado, cantidad), para mantener actualizada mi colección en la
base de datos.


## Corrección de Errores y Mejoras de Usabilidad (Informe 2025)

Basado en el "Informe de Problemas de Usabilidad" , se han implementado las siguientes correcciones para mejorar la estabilidad y experiencia de usuario (UX) de la aplicación:
| ID Problema | Descripción del Fallo | Solución Implementada |
| :--- | :--- | :--- |
| **#5** | Eliminación de copias inmediata sin confirmación . | Se ha añadido un diálogo de confirmación (`Alert.AlertType.CONFIRMATION`) antes de borrar registros. |
| **#3** | Validación de años irreales (ej: 2777 o negativos) . | Implementada lógica de validación: el año debe estar entre 1888 y el año actual. |
| **#6** | Cursor de texto en campos de solo lectura (sinopsis) . | Se reemplazó el componente `TextArea` por un `Label` con `wrapText`, mostrando el cursor de flecha estándar. |
| **#7** | Mensajes de error genéricos al faltar campos . | El sistema ahora lista específicamente qué campos faltan (Título, Director, etc.) en lugar de un mensaje genérico. |
| **#2** | Cierre inesperado (Crash) al introducir texto excesivo . | Se añadieron `Listeners` a los campos de texto para limitar la longitud máxima de caracteres y prevenir desbordamientos en BD. |