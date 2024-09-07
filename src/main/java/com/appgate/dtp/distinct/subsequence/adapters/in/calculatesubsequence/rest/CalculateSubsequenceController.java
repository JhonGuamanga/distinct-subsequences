package com.appgate.dtp.distinct.subsequence.adapters.in.calculatesubsequence.rest;

import com.appgate.dtp.distinct.subsequence.adapters.in.shared.CalculateSubsequenceResource;
import com.appgate.dtp.distinct.subsequence.application.calculatesubsequence.CalculateSubsequenceCommand;
import com.appgate.dtp.distinct.subsequence.application.calculatesubsequence.CalculateSubsequenceUseCase;
import com.appgate.dtp.shared.utils.AppgateLogger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/v1"})
public class CalculateSubsequenceController implements CalculateSubsequenceEntryPoint {
    private static final AppgateLogger log = AppgateLogger.getLogger(CalculateSubsequenceController.class.getName());
    private final CalculateSubsequenceUseCase calculateSubsequenceUseCase;

    public CalculateSubsequenceController(CalculateSubsequenceUseCase calculateSubsequenceUseCase) {
        this.calculateSubsequenceUseCase = calculateSubsequenceUseCase;
    }

    @PostMapping("/calculate-subsequence")
    public ResponseEntity<CalculateSubsequenceResource> calculatesSubsequences(@RequestBody CalculateSubsequenceRequest calculateSubsequenceRequest) {
        log.info("Calculating subsequences from sequence: [{}] and subsequence: [{}]", calculateSubsequenceRequest.getSequence(),
            calculateSubsequenceRequest.getSubsequenceToSearch());
        calculateSubsequenceRequest.validateSelf();

        final var command = new CalculateSubsequenceCommand(
            calculateSubsequenceRequest.toDistinctSubsequences()
        );

        final var result = calculateSubsequenceUseCase.execute(command);

        return new ResponseEntity<>(CalculateSubsequenceResource.from(result), HttpStatus.CREATED);
    }
}
