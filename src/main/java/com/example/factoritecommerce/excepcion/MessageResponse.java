package com.example.factoritecommerce.excepcion;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MessageResponse {
    @JsonProperty("message")
    private String message;
    @JsonProperty("status code")
    private Integer statusCode;
    @JsonProperty("path")
    private String path;
}
