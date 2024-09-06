package com.appgate.dtp.distinct.subsequence.adapters.in.calculatesubsequence.rest;

import com.appgate.dtp.distinct.subsequence.adapters.in.shared.CommonExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = {CalculateSubsequenceController.class})
public class CalculateSubsequenceExceptionHandler extends CommonExceptionHandler {

}
