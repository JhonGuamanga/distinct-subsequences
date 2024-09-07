package com.appgate.dtp.distinct.subsequence.adapters.in.shared;

import com.appgate.dtp.distinct.subsequence.adapters.in.calculatesubsequence.rest.CalculateSubsequenceRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class TestUtil {

    private TestUtil() {
    }

    public static String buildObjectAsJSON(Object request) {
        try {
            return new ObjectMapper().writeValueAsString(request);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "{}";
    }

    public static String buildRequestBodyAsJSON(
        String sequence,
        String subsequenceToSearch) {

        final var request = new CalculateSubsequenceRequest();
        request.setSequence(sequence);
        request.setSubsequenceToSearch(subsequenceToSearch);

        return buildObjectAsJSON(request);
    }
}
