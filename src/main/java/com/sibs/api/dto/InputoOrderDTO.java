package com.sibs.api.dto;

import lombok.Data;


/**
 * @author geraldobarrosjr
 */

@Data
public class InputoOrderDTO {

    private Double quantity;
    private Integer itemId;
    private Integer userId;
}
