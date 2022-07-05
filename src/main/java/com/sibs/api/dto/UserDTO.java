package com.sibs.api.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author geraldobarrosjr
 */

@Data
public class UserDTO {

    @NotBlank
    @Size(max = 200)
    private String name;
    @NotBlank
    @Size(max = 100)
    private String email;
}
