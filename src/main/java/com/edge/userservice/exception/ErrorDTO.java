package com.edge.userservice.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorDTO {

    private final String message;

    public ErrorDTO(String message) {
        this.message = message;
    }

}
