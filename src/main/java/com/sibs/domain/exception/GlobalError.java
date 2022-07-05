package com.sibs.domain.exception;

import lombok.Builder;
import lombok.Data;

/**
 * @author geraldobarrosjr
 */

@Data
@Builder
public class GlobalError {

    private String message;
    private String errorReason;
}
