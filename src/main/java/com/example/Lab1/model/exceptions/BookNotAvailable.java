

package com.example.Lab1.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class BookNotAvailable extends RuntimeException{

    public BookNotAvailable(Long id){
        super(String.format("Book with id: %d is not available", id));
    }

}
