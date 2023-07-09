package org.teachmeskills.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.teachmeskills.exceptions.IncorrectUrlException;

import java.util.Map;

@ControllerAdvice
@Slf4j
public class ErrorControllerAdvice {

    @ExceptionHandler(IncorrectUrlException.class)
    public ModelAndView onIncorrectUrlException(IncorrectUrlException e) {
        log.warn("Incorrect url: {}", e.toString());
        return new ModelAndView(
                "error_pages/ErrorPage",
                Map.of("errorMessage", e.getMessage())
        );
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView onException(Exception e) {
        log.warn("Some exception: " + e.toString());
        return new ModelAndView(
                "error_pages/ErrorPage",
                Map.of("errorMessage", e.getMessage())
        );
    }
}
