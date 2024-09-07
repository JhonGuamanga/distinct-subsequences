package com.appgate.dtp.distinct.subsequence.domain.calculatesubsequence;

import com.appgate.dtp.shared.utils.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class DistinctSubsequences implements SelfValidating {
    private final Sequence sequence;
    private final Sequence subsequenceToSearch;

    public DistinctSubsequences(Sequence sequence, Sequence subsequenceToSearch) {
        this.sequence = sequence;
        this.subsequenceToSearch = subsequenceToSearch;
        validateSequenceLength();
        validateSubSequenceToSearchLength();
        this.validateSelf(this);
    }

    private void validateSequenceLength(){
        if(sequence.getValue().isEmpty()){
            throw new InvalidSequenceLengthException(sequence.getValue());
        }
    }

    private void validateSubSequenceToSearchLength(){
        if(sequence.getValue().length()>1000){
            throw new InvalidSubsequenceToSearchLengthException(subsequenceToSearch.getValue());
        }
    }
}
