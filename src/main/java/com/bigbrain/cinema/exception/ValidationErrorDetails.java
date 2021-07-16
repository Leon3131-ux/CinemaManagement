package com.bigbrain.cinema.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationErrorDetails extends ErrorDetails{

    private Long timestamp;
    private String message;
    private HashMap<String, String> validationErrors;
    private Class<? extends Exception> exceptionClass;
    private String details;

}
