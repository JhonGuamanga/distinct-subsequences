package com.appgate.dtp.distinct.subsequence.adapters.in.shared;

import com.appgate.dtp.distinct.subsequence.domain.calculatesubsequence.SubsequencesNumber;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@Builder(setterPrefix = "with")
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CalculateSubsequenceResource {

    private Integer subsequencesNumber;

    public static CalculateSubsequenceResource from(SubsequencesNumber subsequencesNumber) {
        var builder = CalculateSubsequenceResource
            .builder()
            .withSubsequencesNumber(subsequencesNumber.getAmount());

        return builder.build();
    }
}
