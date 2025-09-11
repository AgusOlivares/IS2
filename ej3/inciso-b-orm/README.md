# Minimarket

## Preparación del entorno

Para que la app funcione, debe estar configurada correctamente la conexión a la base de datos. Para ello, debe estar ejecutándose PostgreSQL, y debe existir una base da datos con el nombre "minimarket".

Luego hay que configurar las credenciales de la base de datos. Las mismas se configuran mediante las siguientes variables de entorno. Hay que setearlas en el IDE, o en la terminal, dependiendo de desde dónde queramos ejecutar la app.

```
SPRING_DATASOURCE_USERNAME=usuario
SPRING_DATASOURCE_PASSWORD=contraseña
```

### a) Ejemplo en IntelliJ

1) Editar la configuración de ejecución.

    ![](./images/edit-config.png)

2) Setear las variables de entorno mencionadas. La sección de "Variables de entorno" puede que esté oculta, pero se puede añadir desde ese mismo menú.

    ![](./images/env-variables.png)


### b) Ejemplo en la terminal

1) Setear las variables de entorno (solo hace falta una vez hasta que se cierre la terminal).

    ```bash
    export SPRING_DATASOURCE_USERNAME=usuario
    export SPRING_DATASOURCE_PASSWORD=contraseña
    ```

3) Ejecutar el siguiente comando dentro del directorio `backend/` para compilar y ejecutar la app.

    ```bash
    ./mvnw spring-boot:run
    ```


## Empaquetado

Si queremos empaquetar la app en un .jar, solo hay que ejecutar el siguiente comando dentro del directorio `backend/`:

```bash
./mvnw package
```

El .jar se creará en el directorio `target/`. Se puede ejecutar con `java -jar nombre_del_archivo.jar`. Para que se ejecute correctamente, hay que setear las variables de entorno ya mencionadas (en este caso, en la terminal).