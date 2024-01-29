package com.mitocode.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

// TODO: 29/01/24 @ControllerAdvice -> Interceptador con todos los paquetes de spring. disponible desde spring 4.3, esta botara para excepciones de tipo Handler
// TODO: 29/01/24 ResponseExceptionHandler -> sirve para no hacer try catch a cada rato en el controller
@ControllerAdvice
@RestController
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    // TODO: 29/01/24 manejarTodasExcepciones _ es pra q si cae en cualquier otra excepcion no controlada este lo maneje
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> manejarTodasExcepciones(Exception ex, WebRequest request){
        ExceptionResponse er = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<ExceptionResponse>(er, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    // TODO: 29/01/24 manejarModeloNotFoundException -> Va a recepcionar los errores q esten ejecutandose o gatillandose de tipo "ModeloNotFoundException". Ahora devuelve not_found ya q donde se implemento es en los errores donde no hay data, revisar el controler y ver donde hay un excepcion de este tipo implementado, (ID NO ENCONTRADO " +  id)
    @ExceptionHandler(ModeloNotFoundException.class)
    public ResponseEntity<ExceptionResponse> manejarModeloNotFoundException(ModeloNotFoundException ex, WebRequest request){
        ExceptionResponse er = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<ExceptionResponse>(er, HttpStatus.NOT_FOUND);
    }



    // TODO: 29/01/24 handleMethodArgumentNotValid -> sirve para botar una excepcion cuando no se respeta el @Valid y este maneje los mensajes de error y status, etc. Sin esto si q validara lo del Valid pero mostrar un error de badrEQUEST PERO NO TENDREMODS EL DETALLE DEL MISMO
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        // TODO: 29/01/24 ex.getBindingResult().getAllErrors() -> aqui recupero una lista de errores q recupero
        String mensaje = ex.getBindingResult().getAllErrors().stream().map(e -> {
            return e.getDefaultMessage().toString().concat(", ");
        }).collect(Collectors.joining());

        // TODO: 29/01/24 Aqui devolveremos el exception 
        ExceptionResponse er = new ExceptionResponse(LocalDateTime.now(), mensaje, request.getDescription(false));
        // TODO: 29/01/24 Aqui enviamos todo l objeto con el status y el detalle del error cuando gatille el error VALID
        return new ResponseEntity<Object>(er, HttpStatus.BAD_REQUEST);
    }


}
