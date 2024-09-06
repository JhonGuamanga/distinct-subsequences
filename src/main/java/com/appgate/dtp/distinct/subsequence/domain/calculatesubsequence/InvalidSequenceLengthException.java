package com.appgate.dtp.distinct.subsequence.domain.calculatesubsequence;

public class InvalidSequenceLengthException extends RuntimeException {

    public static final String MESSAGE = "The length of sequence '%s' is invalid";

    public InvalidSequenceLengthException(String value) {
        super(String.format(MESSAGE, value));
    }
}
