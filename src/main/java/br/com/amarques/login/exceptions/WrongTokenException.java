package br.com.amarques.login.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class WrongTokenException extends RuntimeException {

    public WrongTokenException(String message) {
        super(message);
    }
}
