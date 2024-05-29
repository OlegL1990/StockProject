package com.olegluzin.moexservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {
    @ExceptionHandler({BondParsingException.class})
    public ResponseEntity<ErrorDto> handleExceptionFromPriceService(Exception ex) {
        return new ResponseEntity<ErrorDto>(new ErrorDto(ex.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
    }
}
