package com.example.factoritecommerce.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private Long id;
    @NotBlank(message = "can't be null or empty")
    private String userName;
    @NotBlank(message = "can't be null or empty")
    private String email;

    @NotBlank(message = "can't be null or empty")
    private String dni;

}
