package com.appgate.dtp.distinct.subsequence.adapters.in.calculatesubsequence.rest;

import com.appgate.dtp.distinct.subsequence.domain.calculatesubsequence.DistinctSubsequences;
import com.appgate.dtp.distinct.subsequence.domain.calculatesubsequence.Sequence;
import com.appgate.dtp.shared.utils.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CalculateSubsequenceRequest implements SelfValidating {
    public static final String SEQUENCE_NULL_MESSAGE = "'sequence' field must not be null";
    public static final String SUBSEQUENCE_TO_SEARCH_NULL_MESSAGE = "'subsequenceToSearch' field must not be null";

    @NotNull(message = SEQUENCE_NULL_MESSAGE)
    private String sequence;

    @NotNull(message = SUBSEQUENCE_TO_SEARCH_NULL_MESSAGE)
    private String subsequenceToSearch;

    public void validateSelf() {
        this.validateSelf(this);
    }

    protected DistinctSubsequences toDistinctSubsequences(){
        return new DistinctSubsequences(new Sequence(sequence), new Sequence(subsequenceToSearch));
    }

}
