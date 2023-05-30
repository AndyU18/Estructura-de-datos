# Estructura-de-datos
El programa es un generador de contraseñas que te permite personalizar las opciones de generación. Aquí te explico paso a paso qué hace el programa modificado:

1. Primero, se le preguntará al usuario sobre las preferencias de generación de contraseñas, como si se deben incluir números, caracteres especiales y las longitudes mínima y máxima de la contraseña.

2. Luego, se le preguntará al usuario si desea agregar caracteres personalizados a las contraseñas.

3. Después de recopilar las opciones del usuario, el programa generará y guardará las contraseñas en un archivo llamado "passwords.txt".

4. Durante la generación de las contraseñas, se utiliza una técnica recursiva para generar todas las combinaciones posibles de caracteres. Se utiliza un método llamado `generatePasswordsRecursive` que recibe los caracteres posibles, las longitudes mínima y máxima de la contraseña, los caracteres personalizados y un objeto `FileWriter` para escribir en el archivo.

5. Cada vez que se genera una contraseña válida, se escribe en el archivo "passwords.txt" utilizando el objeto `FileWriter`. Esto mejora la eficiencia y el rendimiento del programa, ya que no se almacenan todas las contraseñas en memoria antes de guardarlas.

6. Al finalizar la generación de contraseñas, se muestra en pantalla la cantidad de contraseñas generadas.

El programa se encarga de generar las contraseñas y guardarlas en un archivo, sin almacenar todas las contraseñas en memoria al mismo tiempo, lo que lo hace más eficiente en términos de uso de recursos.
