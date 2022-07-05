package com.sibs.api.exceptionhandler;

import com.sibs.domain.exception.BusinessException;
import com.sibs.domain.exception.GlobalError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author geraldobarrosjr
 */

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    //TODO: implement business exception handling
    @ExceptionHandler(BusinessException.class)
    private ResponseEntity<Object> handleaNotFound(BusinessException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        GlobalError globalError = GlobalError.builder()
                .errorReason(ex.getErrorReason())
                .message(ex.getMessage())
                .build();

        return handleExceptionInternal(ex, globalError, new HttpHeaders(), status, request);

    }

}
