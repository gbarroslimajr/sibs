package com.sibs.api.dto;


import lombok.*;

import java.time.OffsetDateTime;

/**
 * @author geraldobarrosjr
 */

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OrderDTO {

    @EqualsAndHashCode.Include
    private Long orderId;

    private OffsetDateTime creationDate;
    private Double fulfilled;
    private Double pending;
    private Double quantity;
    private Integer itemId;
    private Integer userId;
    private String itemName;
    private String userName;




}
