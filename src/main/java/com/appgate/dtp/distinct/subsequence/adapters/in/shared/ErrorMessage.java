package com.appgate.dtp.distinct.subsequence.adapters.in.shared;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@ResponseBody
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorMessage(String timestamp, String message, List<ErrorDetail> errors) {

    public ErrorMessage(String message, List<ErrorDetail> errors) {
        this(new Date().toInstant().toString(), message, errors);
    }

    public ErrorMessage(String message) {
        this(new Date().toInstant().toString(), message, null);
    }
}
