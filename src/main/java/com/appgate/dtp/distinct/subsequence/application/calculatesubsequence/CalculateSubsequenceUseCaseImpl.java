package com.appgate.dtp.distinct.subsequence.application.calculatesubsequence;

import com.appgate.dtp.distinct.subsequence.domain.calculatesubsequence.SubsequencesNumber;
import com.appgate.dtp.shared.utils.AppgateLogger;
import org.springframework.stereotype.Service;

@Service
public class CalculateSubsequenceUseCaseImpl implements CalculateSubsequenceUseCase {
    private static final AppgateLogger log = AppgateLogger.getLogger(CalculateSubsequenceUseCaseImpl.class.getName());


    public SubsequencesNumber execute(CalculateSubsequenceCommand command) {
        log.info("Calculating subsequences use case: [{}] ", command.toString());
        return calculateSubsequencesNumber(command.getDistinctSubsequences().getSequence().getValue(),
            command.getDistinctSubsequences().getSubsequenceToSearch().getValue());
    }

    private SubsequencesNumber calculateSubsequencesNumber(String sequence, String subsequenceToSearch){
        final var m = sequence.length();
        final var n = subsequenceToSearch.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (sequence.charAt(i - 1) == subsequenceToSearch.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return new SubsequencesNumber(dp[m][n]);
    }
}
