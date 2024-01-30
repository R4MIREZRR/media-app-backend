# media-app-backend

### Ultimo Commit
##
> Aqui se implemento el nivel de madurez 3 de Richardson al registrar : http://localhost:8080/pacientes/hateoas/1. Cuando se habla de HATEOAS (Hypermedia As The Engine Of Application State) en el contexto de servicios web y REST, se está refiriendo a la práctica de utilizar enlaces hipermedia para guiar y dirigir la interacción entre aplicaciones cliente y servidor. En este caso, "hipermedia" significa que los recursos expuestos por el servicio no solo proporcionan datos, sino también información sobre cómo interactuar con esos recursos a través de enlaces dinámicos. Esto permite una mayor autonomía y descubrimiento por parte del cliente.

> Aqui se agrego BASICA de la impl de Medico y todas sus rutas y se probo y funciona

### Anteriores Commit
> Resumen de Práctica en Richardson Maturity Level 2
En este código, se utiliza ServletUriComponentsBuilder.fromCurrentRequest() para obtener el PATH actual y luego se construye y expande con el ID del paciente. Esta práctica está alineada con el nivel de madurez 2 de Richardson. Según esta teoría, se adopta la práctica de enviar una ruta en el encabezado (header) que permita consultar el registro recién creado.
> En términos sencillos, después de registrar un elemento, se agrega una ruta de consulta en el encabezado para que pueda consultarse posteriormente. Sin embargo, el registro en sí ya no se devuelve en el cuerpo (body) de la respuesta. Esto se hace para mejorar la eficiencia y cumplir con principios RESTful.
> Es crucial tener en cuenta que esta implementación se diseñó específicamente para respuestas con el código 201, que indica registros exitosos. Se recomienda evaluar la necesidad de esta práctica según los requisitos específicos del sistema en el que se está trabajando.

>Pruebas de estres con "RESTFUL STRESS" que es una extension de google para validar el tiempo de respuesta del servicio, deberias tener un primer estado de cuanto demora y luego pasarla con los cambio y comparar las metricas.

> En este último commit, se incorporó la documentación utilizando Swagger. Para revisar los detalles, puedes consultar los comentarios en los cambios realizados en el commit.

> En los dos commits anteriores, nos centramos en la gestión de validaciones para asegurar el tamaño adecuado de las entradas. Posteriormente, manejamos las excepciones, utilizando específicamente el cambio de idiomas al no cumplirse las validaciones y capturamos dichas excepciones. Además, implementamos un manejador (Handler) para validar todas las excepciones, evitando así el uso excesivo de bloques try-catch.

> Se realizo un crud simple con Paciente, ademas se estructuro los package. Se probo desde postman y se registro, elimino, actualizo, etc. Se uso postgresql para el registro.

> Se creo la estructura de las entidades mapeados con JPA, tambien se ejecuto y se estructuro en la base de datos de postresql, solo se han creado las tablas y las relaciones entre ellas.


##### Consideraciones:
- Debes tener postman
- Debes tener conf. postgresql o cualquier otras bd
- Para ver el swagger json: http://localhost:8080/v3/api-docs
- Para ver swagger visual : http://localhost:8080/swagger-ui/index.html
- Para Pasar pruebas de estres: https://chromewebstore.google.com/detail/restful-stress/lljgneahfmgjmpglpbhmkangancgdgeb

##### Configuracion:
##

| Plugin | README |
| ------ | ------ |
| Spring Boot | 2.4.2 |
| Maven | 3.3.9 |
| Spring Framework | 5.3.3 |
| Tomcat 9.0 | Servlet 4.0 |
| Java | 11 |
