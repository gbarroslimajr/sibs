package com.sibs.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author geraldobarrosjr
 */

@AllArgsConstructor
@Getter @Setter
public class BusinessException extends RuntimeException {

    private final String message;
    private final String errorReason;

    public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String message1, String errorReason) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.message = message1;
        this.errorReason = errorReason;
    }


}
