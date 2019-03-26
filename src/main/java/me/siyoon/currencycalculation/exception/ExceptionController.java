package me.siyoon.currencycalculation.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientException;

@ControllerAdvice
@Slf4j
public class ExceptionController {
    @ExceptionHandler(RestClientException.class)
    public ResponseEntity handleRestClientException(RestClientException e) {
        log.warn("RestClientException : " + e.getMessage());
        return ResponseEntity.status(500).contentType(MediaType.APPLICATION_JSON_UTF8)
                .body("{\"message\":"+e.getMessage()+"}");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity handleApiException(IllegalArgumentException e) {
        log.warn("IllegalArgumentException : " + e.getMessage());
        return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON_UTF8)
                .body("{\"message\":"+e.getMessage()+"}");
    }

    @ExceptionHandler(Error.class)
    public String handleError(Error error, Model model) {
        log.warn(error.getMessage());
        model.addAttribute("errMessage", error.getMessage());
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handleAllExceptions(Exception e, Model model) {
        log.warn("예상치 못한 예외 발생 : " + e.getMessage());
        model.addAttribute("errMessage", e.getMessage());
        return "error";
    }
}
