package com.appgate.dtp.distinct.subsequence.domain.calculatesubsequence;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@EqualsAndHashCode
@SuperBuilder(setterPrefix = "with")
@AllArgsConstructor
public class SubsequencesNumber {
    private Integer amount;
}
