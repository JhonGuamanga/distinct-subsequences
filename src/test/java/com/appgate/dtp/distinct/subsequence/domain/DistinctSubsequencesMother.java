package com.appgate.dtp.distinct.subsequence.domain;

import com.appgate.dtp.distinct.subsequence.domain.calculatesubsequence.DistinctSubsequences;
import com.appgate.dtp.distinct.subsequence.domain.calculatesubsequence.Sequence;

public class DistinctSubsequencesMother {

    public static DistinctSubsequences newDistinctSubsequences() {
        return new DistinctSubsequences (new Sequence("rabbbit"), new Sequence("rabbit"));
    }

    public static DistinctSubsequences newDistinctSubsequences(String sequence, String subsequenceToSearch) {
        return new DistinctSubsequences (new Sequence(sequence), new Sequence(subsequenceToSearch));
    }
};
