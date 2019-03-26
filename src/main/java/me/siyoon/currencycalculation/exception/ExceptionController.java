package me.siyoon.currencycalculation.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity handleAllExceptions(Exception e) {
        log.warn(e.getMessage());
        return ResponseEntity.ok(e.getLocalizedMessage());
    }

    @ExceptionHandler(RestClientException.class)
    public String handleRestClientException(RestClientException e) {
        log.warn(e.getMessage());
        return "index";
    }
}
