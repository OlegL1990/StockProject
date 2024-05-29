package com.olegluzin.moexservice.exception;

public class BondParsingException extends RuntimeException {
    public BondParsingException(Exception ex) {
        super(ex);
    }
}
