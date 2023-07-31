package br.com.amarques.login.exceptions;

import br.com.amarques.login.dto.view.ErrorView;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
class AppExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ErrorView handleNotFound(NotFoundException exception, HttpServletRequest request) {
        return new ErrorView(
                NOT_FOUND.value(),
                NOT_FOUND.name(),
                exception.getMessage(),
                request.getServletPath());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ErrorView handleException(Exception exception, HttpServletRequest request) {
        return new ErrorView(
                INTERNAL_SERVER_ERROR.value(),
                INTERNAL_SERVER_ERROR.name(),
                exception.getMessage(),
                request.getServletPath());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorView handleValidationError(MethodArgumentNotValidException exception, HttpServletRequest request) {
        var errorMessage = new HashMap<String, String>();

        exception.getBindingResult().getFieldErrors()
                .forEach(fieldError -> errorMessage.put(fieldError.getField(), fieldError.getDefaultMessage()));

        return new ErrorView(
                BAD_REQUEST.value(),
                BAD_REQUEST.name(),
                errorMessage.toString(),
                request.getServletPath());
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorView handleBadCredentialsException(BadCredentialsException exception, HttpServletRequest request) {
        return new ErrorView(
                BAD_REQUEST.value(),
                BAD_REQUEST.name(),
                exception.getMessage(),
                request.getServletPath());
    }


}