package com.appgate.dtp.distinct.subsequence.domain.calculatesubsequence;

public class InvalidSubsequenceToSearchLengthException extends RuntimeException {

    public static final String MESSAGE = "The length of subsequence to search '%s' is invalid";

    public InvalidSubsequenceToSearchLengthException(String value) {
        super(String.format(MESSAGE, value));
    }
}
