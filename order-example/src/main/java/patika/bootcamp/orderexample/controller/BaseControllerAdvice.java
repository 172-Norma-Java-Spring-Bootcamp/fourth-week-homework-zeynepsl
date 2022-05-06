package patika.bootcamp.orderexample.controller;

import org.hibernate.PropertyValueException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import patika.bootcamp.orderexample.exception.ApiError;
import patika.bootcamp.orderexample.exception.BaseException;

@RestControllerAdvice
public class BaseControllerAdvice {


    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<?> onBaseExceptionHandled(BaseException ex) {
        return ResponseEntity.badRequest().body(new ApiError(ex.getLocalizedMessage()));
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> onRuntimeExceptionHandled(RuntimeException ex) {
        return ResponseEntity.badRequest().body(new ApiError(ex.getLocalizedMessage()));
    }
    
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> onConstraintViolationException(ConstraintViolationException ex){
    	return ResponseEntity.badRequest().body(new ApiError(ex.getLocalizedMessage()));
    }
    
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PropertyValueException.class)
    public ResponseEntity<?> onPropertyValueException(PropertyValueException ex){
    	return ResponseEntity.badRequest().body(new ApiError(ex.getLocalizedMessage()));
    }
    
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> onIllegalArgumentException(IllegalArgumentException ex) {
    	return ResponseEntity.badRequest().body(new ApiError(ex.getLocalizedMessage()));
    }
}
