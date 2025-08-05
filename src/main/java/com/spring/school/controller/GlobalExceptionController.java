package com.spring.school.controller;

import com.spring.school.exception.UserNotFoundException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice(annotations = Controller.class)
@Order(2)
public class GlobalExceptionController {

    @ExceptionHandler(TransactionSystemException.class)
    public String handleTransactionException(TransactionSystemException ex, Model model) {
        Throwable rootCause = ex.getMostSpecificCause();
        String errorMessage = rootCause.getMessage();
        model.addAttribute("errorMsg", "Transaction error: " + errorMessage);
        return "error";
    }

    @ExceptionHandler(UserNotFoundException.class)
    public String handleUserNotFoundException(UserNotFoundException ex, Model model) {
        model.addAttribute("errorMsg", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(Exception exception) {
        ModelAndView errorPage = new ModelAndView();
        errorPage.setViewName("error");
        errorPage.addObject("errorMsg", exception.getMessage());
        return errorPage;
    }
}
