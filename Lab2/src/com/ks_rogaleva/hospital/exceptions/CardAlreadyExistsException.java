package com.ks_rogaleva.hospital.exceptions;

public class CardAlreadyExistsException extends Exception{
    private String objectName;
    public CardAlreadyExistsException(String message, String objectName) {
        super(message);
        this.objectName=objectName;
    }

    public String getObjectName() {
        return objectName;
    }
}
