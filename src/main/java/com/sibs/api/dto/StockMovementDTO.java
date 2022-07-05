package com.sibs.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

/**
 * @author geraldobarrosjr
 */

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class StockMovementDTO {

    @EqualsAndHashCode.Include
    @JsonIgnore
    private Long stockMovementId;

    private OffsetDateTime creationDate;
    private Double quantity;
    private Integer itemId;
    @JsonIgnore
    private String itemName;
}
