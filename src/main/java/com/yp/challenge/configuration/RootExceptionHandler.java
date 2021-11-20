package com.yp.challenge.configuration;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Log4j2
public class RootExceptionHandler {



    /**
     * Handle if the resource is already present.
     *
     * @param ex Exception
     * @return Exception converted
     */
    @ExceptionHandler({Exception.class})
    public ResponseEntity resourceAlreadyPresent(Exception ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }


}