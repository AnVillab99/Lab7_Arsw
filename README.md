# Lab6_ARSW

#

Parte I[](#parte-i)

1.  Integre al proyecto base suministrado los Beans desarrollados en el [Ejercicio Anterior](https://github.com/ARSW-ECI-beta/REST_API-SpringBoot-Cinema_Base). Sólo copie las clases, NO los archivos de configuración. Rectifique que se tenga correctamente configurado el esquema de inyección de dependencias con las anotaciones @Service y @Autowired.


2.  Modifique el bean de persistecia 'InMemoryCinemaPersistence' para que por defecto se inicialice con al menos otras 2 salas de cine, y al menos 2 funciones asociadas a cada una.
 ![Imagen 2 cinemas mas](/images/parte1_1.png)

3.  Configure su aplicación para que ofrezca el recurso "/cinema", de manera que cuando se le haga una petición GET, retorne -en formato jSON- el conjunto de todos los cines. Para esto:

    1.  Modifique la clase CinemaAPIController teniendo en cuenta el ejemplo de controlador REST hecho con SpringMVC/SpringBoot. **(ver code 1)**

    2.  Haga que en esta misma clase se inyecte el bean de tipo CinemaServices (al cual, a su vez, se le inyectarán sus dependencias de persistencia y de filtrado de películas).

    3.  De ser necesario modifique el método getAllCinemas(), de manera que utilice la persistencia previamente inyectada y retorne todos los cines registrados.



4.  Verifique el funcionamiento de a aplicación lanzando la aplicación con maven **(ver code 2).** Y luego enviando una petición GET a: [http://localhost:8080/cinemas](http://localhost:8080/cinemas). Rectifique que, como respuesta, se obtenga un objeto jSON con una lista que contenga el detalle de los cines suministados por defecto.
    ![Get /cinemas](parte1_2.png)
5.  Modifique el controlador para que ahora, acepte peticiones GET al recurso /cinemas/{name}, el cual retorne usando una representación jSON todas las funciones del cine cuyo nombre sea {name}. Si no existe dicho cine, se debe responder con el código de error HTTP 404. Para esto, revise en [la documentación de Spring](http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html), sección 22.3.2, el uso de @PathVariable. De nuevo, verifique que al hacer una petición GET -por ejemplo- a recurso [http://localhost:8080/cinemas/cinemaY](http://localhost:8080/cinemas/cinemaY) , se obtenga en formato jSON el conjunto de funciones asociadas al cine 'cinemaY' (ajuste esto a los nombres de cine usados en el punto 2).
  ![Get cinemas/name](parte1_3.png)

7.  Modifique el controlador para que ahora, acepte peticiones GET al recurso /cinemas/{name}/{date}, el cual retorne usando una representación jSON una lista de funciones asociadas al cine cuyo nombre es {name} y cuya fecha sea {date}, para mayor facilidad se seguirá manejando el formato "yyyy-MM-dd". De nuevo, si no existen dichas funciones, se debe responder con el código de error HTTP 404.
      ![Get cinemas/name/date](parte1_4.png)
8.  Modifique el controlador para que ahora, acepte peticiones GET al recurso /cinemas/{name}/{date}/{moviename}, el cual retorne usando una representación jSON sólo UNA función, en este caso es necesario detallar además de la fecha, la hora exacta de la función de la forma "yyyy-MM-dd HH:mm". Si no existe dicha función, se debe responder con el código de error HTTP 404.
    ![Get cinemas/name/date/mocie](parte1_5.png)

Code 1

>@RestController
@RequestMapping(value =  "/url-raiz-recurso")
public  class  XXController  {
@RequestMapping(method = RequestMethod.GET)
public ResponseEntity<?>  manejadorGetRecursoXX(){
 try  {
 //obtener datos que se enviarán a través del API
 return  new  ResponseEntity<>(data,HttpStatus.ACCEPTED);
 }  catch  (XXException ex)  {
 Logger.getLogger(XXController.class.getName()).log(Level.SEVERE, null, ex);
 return  new  ResponseEntity<>("Error bla bla bla",HttpStatus.NOT_FOUND);
 }
}

Code 2

>$ mvn clean compile
$ mvn spring-boot:run

#

Parte II[](#parte-ii)

1.  Agregue el manejo de peticiones POST (creación de nuevas funciones), de manera que un cliente http pueda registrar una nueva función a un determinado cine haciendo una petición POST al recurso ‘/cinemas/{name}’, y enviando como contenido de la petición todo el detalle de dicho recurso a través de un documento jSON. Para esto, tenga en cuenta el siguiente ejemplo, que considera -por consistencia con el protocolo HTTP- el manejo de códigos de estados HTTP (en caso de éxito o error): **(ver code 3)**
        ![POst method/name](parte2_1.png)
2.  Para probar que el recurso ‘cinemas’ acepta e interpreta correctamente las peticiones POST, use el comando curl de Unix. Este comando tiene como parámetro el tipo de contenido manejado (en este caso jSON), y el ‘cuerpo del mensaje’ que irá con la petición, lo cual en este caso debe ser un documento jSON equivalente a la clase Cliente (donde en lugar de {ObjetoJSON}, se usará un objeto jSON correspondiente a una nueva función: **(ver code 4)** Con lo anterior, registre un nueva función (para 'diseñar' un objeto jSON, puede usar [esta herramienta](https://jsoneditoronline.org/)): **Nota:** puede basarse en el formato jSON mostrado en el navegador al consultar una función con el método GET.

3.  Teniendo en cuenta el nombre del cine, la fecha y hora de la función y el nombre de la película, verifique que el mismo se pueda obtener mediante una petición GET al recurso '/cinemas/{name}/{date}/{moviename}' correspondiente.

4.  Agregue soporte al verbo PUT para los recursos de la forma '/cinemas/{name}', de manera que sea posible actualizar una función determinada, el servidor se encarga de encontrar la función correspondiente y actualizarla o crearla.
     ![POst method/name](parte2_2.png)

Code 3

>@RequestMapping(method = RequestMethod.POST)
public ResponseEntity<?>  manejadorPostRecursoXX(@RequestBody TipoXX o){
 try  {
 //registrar dato
 return  new  ResponseEntity<>(HttpStatus.CREATED);
 }  catch  (XXException ex)  {
 Logger.getLogger(XXController.class.getName()).log(Level.SEVERE, null, ex);
 return  new  ResponseEntity<>("Error bla bla bla",HttpStatus.FORBIDDEN);
 }
}

Code 4

>>curl -i -X POST -HContent-Type:application/json -HAccept:application/json http://localhost:8080/cinemas/cinemaX -d '{"movie":{"name":"Celular","genre":"Comedy"},"seats":[[true,true,true,true,true,true,true,true,true,true,true,true],[true,true,true,true,true,true,true,true,true,true,true,true],[true,true,true,true,true,true,true,true,true,true,true,true],[true,true,true,true,true,true,true,true,true,true,true,true],[true,true,true,true,true,true,true,true,true,true,true,true],[true,true,true,true,true,true,true,true,true,true,true,true],[true,true,true,true,true,true,true,true,true,true,true,true]],"date":"2018-3-18 15:30"}'

#

Parte III[](#parte-iii)

El componente CinemaRESTAPI funcionará en un entorno concurrente. Es decir, atederá múltiples peticiones simultáneamente (con el stack de aplicaciones usado, dichas peticiones se atenderán por defecto a través múltiples de hilos). Dado lo anterior, debe hacer una revisión de su API (una vez funcione), e identificar:

-   Qué condiciones de carrera se podrían presentar?

-   Cuales son las respectivas regiones críticas?


Ajuste el código para suprimir las condiciones de carrera. Tengan en cuenta que simplemente sincronizar el acceso a las operaciones de persistencia/consulta DEGRADARÁ SIGNIFICATIVAMENTE el desempeño de API, por lo cual se deben buscar estrategias alternativas.

Escriba su análisis y la solución aplicada en el archivo ANALISIS_CONCURRENCIA.txt
# Lab7_Arsw
