package com.lakala.ls;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalControllerExceptionHandler {


    @Autowired
    private MessageSource messageSource;

    private static Logger logger = LoggerFactory
        .getLogger(GlobalControllerExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Map processEValidationError(Exception e) {
    	
        String msg = e.getMessage();
        String code ="SP0001";
        if(e instanceof LsException){
        	msg = ((LsException)e).getDefaultMessage();
        	code =((LsException)e).getCode();
        }else if(e instanceof MethodArgumentNotValidException){
        	msg = ((MethodArgumentNotValidException)e).getBindingResult().getFieldError().getDefaultMessage();
        }
        logger.info("error", e);
        Map dto = new HashMap<>();
        msg = msg!=null?msg:messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
        if (msg.length() > 20) {
            msg = "internal_error";
        }

        Map<String,String> detail = new HashMap<>();
        detail.put("message", msg);
        detail.put("code", code);
        dto.put("error", detail);
        return dto;
    }

}
