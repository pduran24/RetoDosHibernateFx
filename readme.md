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
