package com.example.techiteasy.exception;

public class RecordNotFoundException extends RuntimeException {

    //Constructors
    public RecordNotFoundException() {
        super();
    }


    public RecordNotFoundException(String message) {
        super(message);
    }


}
