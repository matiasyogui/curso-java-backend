package com.myogui.aplicandospringboot.handle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorHandler {
    Logger logger = LogManager.getLogger(ErrorHandler.class);

    @ResponseBody
    @ExceptionHandler(FirstApplicationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Error errorHandler(FirstApplicationException exception){
        logger.error(exception);
        return new Error(exception.getMessage());
    }
}
