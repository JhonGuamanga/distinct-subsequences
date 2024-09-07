package com.appgate.dtp.distinct.subsequence.application.calculatesubsequence;

import com.appgate.dtp.distinct.subsequence.domain.calculatesubsequence.DistinctSubsequences;
import com.appgate.dtp.shared.utils.SelfValidating;
import lombok.Builder;
import lombok.Getter;

@Builder(setterPrefix = "with")
@Getter
public class CalculateSubsequenceCommand implements SelfValidating {

    private DistinctSubsequences distinctSubsequences;
    public CalculateSubsequenceCommand(DistinctSubsequences distinctSubsequences) {
        this.distinctSubsequences = distinctSubsequences;
        this.validateSelf(this);
    }
}
