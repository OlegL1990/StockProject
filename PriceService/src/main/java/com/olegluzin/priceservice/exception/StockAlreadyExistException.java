package com.olegluzin.priceservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class StockAlreadyExistException extends RuntimeException {
    public StockAlreadyExistException(String message) {
        super(message);
    }
}
