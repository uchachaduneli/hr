/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.economy.hr.error;


import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * @author nino lomineishvili
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {


    private final static transient Logger logger = Logger.getLogger(GlobalDefaultExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public String defaultErrorHandler(HttpServletRequest request, HttpServletResponse response, Exception exception) throws UnsupportedEncodingException {
        logger.error("Caught exception in global handler", exception);
        response.setStatus(400); //bad request
        if (exception instanceof HrTypeException) {
            return exception.getMessage();
        } else {
            return "Unable to determine cause, Please contact Administrator";
        }
    }
}
