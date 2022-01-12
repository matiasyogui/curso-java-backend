package com.myoguicoderhouse.controlleradvice.handle;

public class ApiRestException extends Exception {
    private String message;

    public ApiRestException(String message) {
        super(message);
    }
}
