package com.mitocode.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// TODO: 29/01/24 @ResponseStatus(HttpStatus.NOT_FOUND)  -> para q cada vez q sea invocado y caiga en esta excepcion devuelva 404 not_found
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ModeloNotFoundException  extends  RuntimeException{

    public ModeloNotFoundException(String message) {
        super(message);
    }
}
