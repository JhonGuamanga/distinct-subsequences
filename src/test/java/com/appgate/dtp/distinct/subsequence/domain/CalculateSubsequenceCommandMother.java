package com.appgate.dtp.distinct.subsequence.domain;


import com.appgate.dtp.distinct.subsequence.application.calculatesubsequence.CalculateSubsequenceCommand;

public class CalculateSubsequenceCommandMother {
    public static CalculateSubsequenceCommand validCommand(String sequence, String subsequenceToSearch) {
        return new CalculateSubsequenceCommand(
            DistinctSubsequencesMother.newDistinctSubsequences(sequence, subsequenceToSearch)
        );
    }

    public static CalculateSubsequenceCommand invalidCommand() {
        return new CalculateSubsequenceCommand(
            DistinctSubsequencesMother.newDistinctSubsequences("", "bag")
        );
    }
}
