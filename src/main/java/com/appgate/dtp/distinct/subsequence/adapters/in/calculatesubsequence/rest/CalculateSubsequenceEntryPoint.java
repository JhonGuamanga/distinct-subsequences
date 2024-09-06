package com.appgate.dtp.distinct.subsequence.adapters.in.calculatesubsequence.rest;


import com.appgate.dtp.distinct.subsequence.adapters.in.shared.CalculateSubsequenceResource;
import com.appgate.dtp.distinct.subsequence.adapters.in.shared.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@SuppressWarnings("unused")
public interface CalculateSubsequenceEntryPoint {
    @Operation(summary = "Search a subsequence in a sequence", description = "Allows to know how many times a subsequence is in a sequence")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "201", description = "Success Operation"),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(responseCode = "409", description = "Conflict", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(responseCode = "500", description = "If fail server", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
        })
    ResponseEntity<CalculateSubsequenceResource> calculatesSubsequences(
        @RequestBody CalculateSubsequenceRequest request
    );
}
