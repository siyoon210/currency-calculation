package me.siyoon.currencycalculation.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientException;
import org.springframework.validation.BindException;

@ControllerAdvice
@Slf4j
public class ExceptionController {
    @ExceptionHandler(BindException.class)
    public ResponseEntity handleBindExceptionException(BindException e) {
        log.warn("BindException : " + e.getMessage());
        return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON_UTF8)
                .body("{\"message\":" + e.getMessage() + "}");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity handleIllegalArgumentException(IllegalArgumentException e) {
        log.warn("IllegalArgumentException : " + e.getMessage());
        return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON_UTF8)
                .body("{\"message\":" + e.getMessage() + "}");
    }

    @ExceptionHandler(RestClientException.class)
    public ResponseEntity handleRestClientException(RestClientException e) {
        log.warn("RestClientException : " + e.getMessage());
        return ResponseEntity.status(500).contentType(MediaType.APPLICATION_JSON_UTF8)
                .body("{\"message\":" + e.getMessage() + "}");
    }

    @ExceptionHandler(Error.class)
    public String handleError(Error error, Model model) {
        log.warn(error.getMessage());
        model.addAttribute("errMessage", error.getMessage());
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleAllExceptions(Exception e) {
        log.warn("예상치 못한 예외 발생 : " + e.getMessage() + "-" + e.getClass().getName());
        for (StackTraceElement stackTraceElement : e.getStackTrace()) {
            log.warn(stackTraceElement.toString());
        }
        return ResponseEntity.status(500).contentType(MediaType.APPLICATION_JSON_UTF8)
                .body("{\"message\":" + e.getMessage() + "}");
    }
}
