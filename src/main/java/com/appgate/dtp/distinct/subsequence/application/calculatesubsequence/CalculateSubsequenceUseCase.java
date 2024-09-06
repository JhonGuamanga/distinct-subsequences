package com.appgate.dtp.distinct.subsequence.application.calculatesubsequence;


import com.appgate.dtp.distinct.subsequence.domain.calculatesubsequence.SubsequencesNumber;

public interface CalculateSubsequenceUseCase {
    SubsequencesNumber execute(CalculateSubsequenceCommand any);
}
